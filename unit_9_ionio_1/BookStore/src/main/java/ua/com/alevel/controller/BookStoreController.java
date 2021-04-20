package ua.com.alevel.controller;

import com.opencsv.exceptions.CsvException;
import ua.com.alevel.dao.BookStoreService;
import ua.com.alevel.dao.impl.AuthorBookStoreService;
import ua.com.alevel.dao.impl.BookBookStoreService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookStoreController {

    private static final BookStoreService<Book> bookService = new BookBookStoreService();
    private static final BookStoreService<Author> authorService = new AuthorBookStoreService();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run(String option) throws IOException, ParseException, CsvException {
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
                    update(Integer.parseInt(reader.readLine()));
                    break;
                case "4":
                    System.out.println("Enter id of a book or an author to delete");
                    delete(Integer.parseInt(reader.readLine()));
                    break;
                default:
                    System.out.println("Wrong index");

            }
            System.out.println("Enter 0 to exit or choose another CRUD operation");
            option = reader.readLine();
        }
    }


    public void create(String option) throws IOException, ParseException, CsvException {

        List<Integer> authors = new ArrayList<>();
        List<Integer> books = new ArrayList<>();
        switch(option) {
            case "0":
                return;
            case "1":
                authors.clear();
                books.clear();
                System.out.println("Enter name:");
                String name = reader.readLine();
                Book b = createBook(name);
                books.add(b.getId());
                System.out.println("Enter number of authors:");
                int len = Integer.parseInt(reader.readLine());

                for (int i = 0; i < len; i++) {
                    System.out.print("Enter author #" + (i + 1) + ": ");
                    String[] authorName = reader.readLine().split(" ");
                    Author authorCheck = authorService.findAll().stream()
                            .filter(a -> a.getFirstName().equals(authorName[0])
                                    && a.getLastName().equals(authorName[1]))
                            .findFirst().orElse(null);
                    if (authorCheck != null) {
                        List<Integer> authorBooks = authorCheck.getBooks();
                        authorBooks.addAll(books);
                        authorCheck.setBooks(authorBooks);
                        authorService.update(authorCheck);
                        authors.add(authorCheck.getId());
                    }
                    System.out.println();
                }
                b.setAuthors(authors);
                bookService.create(b);
                break;
            case "2":
                System.out.println("Enter first and last name:");
                String aName = reader.readLine();
                Author a = createAuthor(aName);
                authorService.create(a);
                break;
            default:
                System.out.println("Wrong index");
        }
    }

    public Book createBook(String name)  {
        Book b = new Book();
        b.setName(name);
        return b;

    }
    public Author createAuthor(String name) {
        Author a = new Author();
        a.setFirstName(name.split(" ")[0]);
        a.setLastName(name.split(" ")[1]);
        return a;

    }


    public void read(String option) throws ParseException, IOException, CsvException {
        switch(option){
            case "1":
                for (Book book: bookService.findAll()) {
                        readBook(book);
                        System.out.println("Author(s): ");
                        for (Author a : bookService.findByBook(book)) {
                            if (a.isVisible()) {
                                System.out.println(a.getId() + " " + a.getFirstName() + " " + a.getLastName());
                            }

                        }
                }
                break;
            case "2":
                for (Author author: authorService.findAll() ){

                    readAuthor(author);
                    System.out.println("Books: ");
                    for(Book b:authorService.findByAuthor(author)) {
                        if (b.isVisible()) {
                            System.out.println(b.getId() + " " + b.getName() );
                        }
                    }
                }
                break;
            case "3":
                System.out.println("Enter book's id:");
                int id = Integer.parseInt(reader.readLine());
                if (bookExistsById(id)) {
                    Book book = getBookById(id);
                    for (Author a: authorService.findByBook(book)) {
                        if (a.isVisible()) {
                            readAuthor(a);
                        }
                    }
                } else {
                    System.out.println("Wrong id");
                }
                break;
            case "4":
                System.out.println("Enter author's id:");
                int aId = Integer.parseInt(reader.readLine());
                if (authorExistsById(aId)) {
                    Author author = getAuthorById(aId);
                    for (Book b:bookService.findByAuthor(author)) {
                        if (b.isVisible()) {
                            readBook(b);
                        }
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

            System.out.println(book.getId() + " " + book.getName() + " " + book.getCreated());

    }

    public boolean authorExistsById(int id) throws ParseException, IOException, CsvException {
        return authorService.findAll().stream().anyMatch(a -> id == a.getId());
    }
    public Author getAuthorById(Integer id) throws ParseException, IOException, CsvException {
        return authorService.findAll().stream().filter(a -> id == a.getId())
                .findFirst().orElse(null);
    }
    public boolean bookExistsById(int id) throws ParseException, IOException, CsvException {
        return bookService.findAll().stream().anyMatch(a -> id == a.getId());
    }
    public Book getBookById(int id) throws ParseException, IOException, CsvException {
        return bookService.findAll().stream().filter(a -> id == a.getId()).findFirst().orElse(null);
    }

    public void update(int id) throws ParseException, IOException, CsvException {

        if (authorExistsById(id)) {
            Author toUpdate = getAuthorById(id);
            System.out.println("Enter new first name: ");
            String firstName = reader.readLine();
            toUpdate.setFirstName(firstName);

            System.out.println("Enter new last name: ");
            String lastName = reader.readLine();
            toUpdate.setLastName(lastName);

            List<Integer> books = new ArrayList<>();
            List<Integer> authors = new ArrayList<>();

            System.out.println("Enter number of books: ");
            int number = Integer.parseInt(reader.readLine());
            for (int i = 0; i < number; i++) {
                authors.clear();
                authors.add(toUpdate.getId());
                System.out.println("Enter book #" + (i + 1) + " id:");
                int bookId = Integer.parseInt(reader.readLine());
                if (bookExistsById(bookId)) {
                    Book b = getBookById(bookId);
                    if (!b.getAuthors().contains(toUpdate.getId())) {
                        authors.addAll(b.getAuthors());
                        b.setAuthors(authors);
                        bookService.update(b);
                    }
                    books.add(bookId);
                }
            }

            toUpdate.setBooks(books);
            authorService.update(toUpdate);


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

            List<Integer> books = new ArrayList<>();
            List<Integer> authors = new ArrayList<>();

            System.out.println("Enter number of authors:");
            int len = Integer.parseInt(reader.readLine());
            for (int i = 0; i < len; i++) {
                books.clear();
                books.add(toUpdate.getId());
                System.out.print("Enter author #" + (i + 1) + " id: ");
                int authorId = Integer.parseInt(reader.readLine());
                if (authorExistsById(authorId)) {
                    Author a = new Author();
                    if (!a.getBooks().contains(toUpdate.getId())) {
                        books.addAll(a.getBooks());
                        a.setBooks(books);
                        authorService.update(a);
                    }
                    authors.add(authorId);
                }
            }
            toUpdate.setAuthors(authors);
            bookService.update(toUpdate);

        } else {
            System.out.println("Wrong id");
        }
    }

    public void delete(int id) throws ParseException, IOException, CsvException {
        if( authorExistsById(id)) {
            Author toDelete = getAuthorById(id);
            List<Book> books = authorService.findByAuthor(toDelete);
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getAuthors().size() == 1) {

                    bookService.delete(books.get(i));
                }
            }
            authorService.delete(toDelete);
        } else if (bookExistsById(id)) {
            Book toDelete = getBookById(id);
            List<Author> authors = bookService.findByBook(toDelete);
            for (int i = 0; i < authors.size(); i++) {
                Author a = authors.get(i);
                List<Integer> books = a.getBooks();
                books.remove((Integer) toDelete.getId());
                a.setBooks(books);
                authorService.update(a);
            }
            bookService.delete(toDelete);
        } else {
            System.out.println("Wrong id");
        }
    }

}
