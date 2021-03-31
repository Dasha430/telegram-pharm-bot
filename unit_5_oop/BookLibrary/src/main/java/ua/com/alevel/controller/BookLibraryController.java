package ua.com.alevel.controller;

import lombok.SneakyThrows;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.AuthorService;
import ua.com.alevel.dao.impl.BookService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookLibraryController {
    private static final LibraryService<Author> authorService = new AuthorService();
    private static final LibraryService<Book> bookService = new BookService();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public void run(String option) {
        while(true) {
            switch(option) {
                case "0":
                    System.exit(0);
                case "1":
                    System.out.println("1 - create book");
                    System.out.println("2 - create author");
                    System.out.println("0 - exit");
                    create(reader.readLine());
                    break;
                case "2":
                    System.out.println("1 - read all books");
                    System.out.println("2 - read all authors");
                    System.out.println("3 - read all authors by book id");
                    System.out.println("4 - read all books by author id");
                    System.out.println("0 - exit");
                    read(reader.readLine());
                    break;
                case "3":
                    System.out.println("Enter id of a book or an author to update");
                    update(reader.readLine());
                    break;
                case "4":
                    System.out.println("Enter id of a book or an author to delete");
                    delete(reader.readLine());
                    break;
                default:
                    System.out.println("Wrong index");

            }
            System.out.println("Enter 0 to exit or choose another CRUD operation");
            option = reader.readLine();
        }
    }

    @SneakyThrows
    public void create(String option) {
        switch(option) {
            case "0":
                return;
            case "1":
                System.out.println("Enter name:");
                String name = reader.readLine();
                createBook(name);
                System.out.println("Enter number of authors:");
                int len = Integer.parseInt(reader.readLine());
                for (int i = 0; i < len; i++) {
                    System.out.print("Enter author #" + (i + 1) + ": ");
                    String authorName = reader.readLine();
                    setAuthorToBook(name,
                            authorName.trim().split(" ")[0], authorName.trim().split(" ")[1]);
                    System.out.println();
                }
                break;
            case "2":
                System.out.println("Enter first and last name:");
                String aName = reader.readLine();
                 createAuthor(aName);
               /* System.out.println("Enter number of their books:");
                int books = Integer.parseInt(reader.readLine());
                for (int i = 0; i < books; i++) {
                    System.out.print("Enter book #" + (i + 1) + ": ");
                    setBookToAuthor(aName.trim().split(" ")[0],
                            aName.trim().split(" ")[1], reader.readLine());
                    System.out.println();
                }*/
                break;
            default:
                System.out.println("Wrong index");
        }
    }
    public void createBook(String name) {
        Book b = new Book();
        b.setName(name);
        try{
            bookService.create(b);
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
    public void createAuthor(String name) {
        Author a = new Author();
        a.setFirstName(name.split(" ")[0]);
        a.setLastName(name.split(" ")[1]);
        try {
            authorService.create(a);
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
    public void setAuthorToBook(String bookName, String authorFirstName, String authorLastName) {
        List<Author> authors = new ArrayList<>();

        Book bookToUpdate = bookService.findAll().stream()
                .filter(b -> b.getName().equals(bookName)).findFirst().get();
        authors.addAll(bookService.findByBook(bookToUpdate));

        if (authorService.findAll().stream()
                .anyMatch(a -> a.getFirstName().equals(authorFirstName) && a.getLastName().equals(authorLastName))) {
            authors.add(authorService.findAll().stream()
                    .filter(a -> a.getFirstName().equals(authorFirstName) && a.getLastName().equals(authorLastName))
                    .findFirst().get());

        } else {
            createAuthor(authorFirstName + " " + authorLastName);
            authors.add(authorService.findAll().stream()
                    .filter(a -> a.getFirstName().equals(authorFirstName) && a.getLastName().equals(authorLastName))
                    .findFirst().get());
        }

        List<Author> unique = authors.stream().distinct().collect(Collectors.toList());

        bookToUpdate.setAuthors(unique);
        bookService.update(bookToUpdate);

    }
    /*public void setBookToAuthor(String authorFirstName, String authorLastName, String bookName) {
        List<Book> books = new ArrayList<>();

        Author authorToUpdate =  authorService.findAll().stream()
                .filter(a -> (a.getFirstName().equals(authorFirstName) && a.getLastName().equals(authorLastName))  )
                .findFirst().get();
        books.addAll(authorService.findByAuthor(authorToUpdate));

        if (bookService.findAll().stream().anyMatch(b -> b.getName().equalsIgnoreCase(bookName))){
            books.add( bookService.findAll().stream()
                    .filter(b -> b.getName().equalsIgnoreCase(bookName) ).findFirst().get());
        } else {
            createBook(bookName);
            books.add( bookService.findAll().stream()
                    .filter(b -> b.getName().equalsIgnoreCase(bookName) ).findFirst().get());
        }

        List<Book> unique = books.stream().distinct().collect(Collectors.toList());

        authorToUpdate.setBooks(unique);
        authorService.update(authorToUpdate);
        setAuthorToBook(bookName, authorFirstName, authorLastName);
    }*/

    @SneakyThrows
    public void read(String option) {
        switch(option){
            case "1":
                for (Book book: bookService.findAll()) {
                    if (!book.isDeleted()) {
                        readBook(book);
                        System.out.println("Author(s): ");
                        for (Author a : book.getAuthors()) {
                            System.out.println(a.getFirstName() + " " + a.getLastName());
                        }
                    }
                }
                break;
            case "2":
                for (Author author: authorService.findAll() ){

                    readAuthor(author);
                    System.out.println("Books: ");
                    for(Book b:authorService.findByAuthor(author)) {
                        System.out.println(b.getId() + " " + b.getName() + " " + b.getCreated());
                    }
            }
                break;
            case "3":
                System.out.println("Enter book's id:");
                String id = reader.readLine();
                if (bookExistsById(id)) {
                    Book book = getBookById(id);
                    for (Author a:authorService.findByBook(book)) {
                        readAuthor(a);
                    }
                } else {
                    System.out.println("Wrong id");
                }
                break;
            case "4":
                System.out.println("Enter author's id:");
                String authorId = reader.readLine();
                if (authorExistsById(authorId)) {
                    Author author = getAuthorById(authorId);
                    for (Book b:bookService.findByAuthor(author)) {
                        readBook(b);
                    }
                }else {
                    System.out.println("Wrong id");
                }
                break;
            case "0":
                return;
            default:
                System.out.println("Wrong index");
        }

    }

    public void readAuthor(Author author){
        System.out.println(author.getId() + " " + author.getFirstName() + " " + author.getLastName() + " "
                + author.getCreated());
    }
    public void readBook(Book book) {
        if (!book.isDeleted()) {
            System.out.println(book.getId() + " " + book.getName() + " " + book.getCreated());
        }
    }

    public boolean authorExistsById(String id) {
        return authorService.findAll().stream().anyMatch(a -> id.equals(a.getId()));
    }
    public Author getAuthorById(String id){
       return authorService.findAll().stream().filter(a -> id.equals(a.getId()))
                .findFirst().get();
    }
    public boolean bookExistsById(String id){
        return bookService.findAll().stream().anyMatch(a -> id.equals(a.getId()));
    }
    public Book getBookById(String id) {
        return bookService.findAll().stream().filter(a -> id.equals(a.getId())).findFirst().get();
    }

    @SneakyThrows
    public void update(String id) {

        if (authorExistsById(id)) {
            Author toUpdate = getAuthorById(id);
            System.out.println("Enter new first name: ");
            String firstName = reader.readLine();
            toUpdate.setFirstName(firstName);
            System.out.println("Enter new last name: ");
            String lastName = reader.readLine();
            toUpdate.setLastName(lastName);
            try {
                authorService.update(toUpdate);
            } catch(RuntimeException e) {
                System.out.println(e.getMessage());
            }



        } else if (bookExistsById(id)){
            Book toUpdate = getBookById(id);
            System.out.println("Enter new book name:");
            String name = reader.readLine();
            toUpdate.setName(name);
            try {
                bookService.update(toUpdate);
            } catch(RuntimeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter number of authors:");
            int len = Integer.parseInt(reader.readLine());
            for (int i = 0; i < len; i++) {
                System.out.print("Enter author #" + (i + 1) + ": ");
                String authorName = reader.readLine();
                setAuthorToBook(name,
                        authorName.trim().split(" ")[0], authorName.trim().split(" ")[1]);
                System.out.println();
            }

        } else {
            System.out.println("Wrong id");
        }
    }

    public void delete(String id) {
       if( authorExistsById(id)) {
           Author toDelete = getAuthorById(id);
           authorService.delete(toDelete);
       } else if (bookExistsById(id)) {
           Book toDelete = getBookById(id);
           bookService.delete(toDelete);
       } else {
           System.out.println("Wrong id");
       }
    }

}
