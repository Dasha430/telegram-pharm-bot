package ua.com.alevel.methods;

import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.Test;
import ua.com.alevel.dao.BookStoreService;
import ua.com.alevel.dao.impl.AuthorBookStoreService;
import ua.com.alevel.dao.impl.BookBookStoreService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CreateTest {

    private static final BookStoreService<Book> bookService = new BookBookStoreService();
    private static final BookStoreService<Author> authorService = new AuthorBookStoreService();
    private static final String authorStr = "Author";
    private static final String bookStr = "Book";

    @Test
    public void createEntity() {

        List<Author> authors = null;
        try {
            authors = authorService.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        List<Book> books = null;
        try {
            books = bookService.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        int aPrevious = authors.size();
        int bPrevious = books.size();

        Author a = new Author();
        Book b = new Book();
        Book b2 = new Book();

        a.setFirstName(authorStr);
        a.setLastName(authorStr);
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB= new ArrayList<>();
        listA.add(a.getId());

        b.setName(bookStr);
        b.setAuthors(listA);
        listB.add(b.getId());
        b2.setName(bookStr + 2);
        b2.setAuthors(listA);
        listB.add(b2.getId());
        a.setBooks(listB);

        try {
            authorService.create(a);
            bookService.create(b);
            bookService.create(b2);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        try {
            authors = authorService.findAll();
            books = bookService.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        int aLast = authors.size();
        int bLast = books.size();
        //Assert.assertEquals(aPrevious, aLast);
        //Assert.assertEquals(bPrevious, bLast);
    }
}
