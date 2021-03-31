package ua.com.alevel;

import org.junit.Assert;
import org.junit.Test;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.AuthorService;
import ua.com.alevel.dao.impl.BookService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryCommonTest {

    private static final LibraryService<Author> authorService = new AuthorService();
    private static final LibraryService<Book> bookService = new BookService();



    @Test
    public void test() {

        List<Author> authors = authorService.findAll();
        List<Book> books = bookService.findAll();
        int aPrevious = authors.size();
        int bPrevious = books.size();
        createBook("Book1");
        createBook("Book2");
        createAuthor("William Shakespeare");
        createAuthor("John Smith");
        createAuthor("Jane Austen");


        authors = authorService.findAll();
        books = bookService.findAll();
        int aLast = authors.size();
        int bLast = books.size();
        Assert.assertEquals(aPrevious + 3, aLast);
        Assert.assertEquals(bPrevious + 2, bLast);



        deleteAll();

    }




    public void createBook(String name) {
        Book b = new Book();
        b.setName(name);
        bookService.create(b);
    }

    public void createAuthor(String name) {
        Author a = new Author();
        a.setFirstName(name.split(" ")[0]);
        a.setLastName(name.split(" ")[1]);
        authorService.create(a);
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

        authorToUpdate.setBooks(books);
        authorService.update(authorToUpdate);
        setAuthorToBook(bookName, authorFirstName, authorLastName);
    }*/

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

    public void deleteAll() {
        int size = bookService.findAll().size();
        while (size != 0) {
            int end = bookService.findAll().size() - 1;
            bookService.delete(bookService.findAll().get(end));
            size = bookService.findAll().size();
        }

        size = authorService.findAll().size();
        while (size != 0) {
            int end = authorService.findAll().size() - 1;
            authorService.delete(authorService.findAll().get(end));
            size = authorService.findAll().size();
        }
    }



}
