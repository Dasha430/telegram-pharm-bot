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
import java.util.List;

public class DeleteTest {

    private static final BookStoreService<Book> bookService = new BookBookStoreService();
    private static final BookStoreService<Author> authorService = new AuthorBookStoreService();

    private CreateTest ct = new CreateTest();

    @Test
    public void deleteEntity() {

        List<Author> authors = null;
        List<Book> books = null;
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

        int aPrevious = authors.size();
        int bPrevious = books.size();

        ct.createEntity();
        Author a = null;
        Book b = null;
        try {
            a = authorService.findAll().get(0);
            b = bookService.findAll().get(0);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }


        try {
            authorService.delete(a);
            bookService.delete(b);
            authors = authorService.findAll();
            books = bookService.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }




    }
}
