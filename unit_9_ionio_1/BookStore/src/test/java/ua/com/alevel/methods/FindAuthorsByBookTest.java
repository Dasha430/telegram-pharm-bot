package ua.com.alevel.methods;

import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import ua.com.alevel.dao.BookStoreService;
import ua.com.alevel.dao.impl.BookBookStoreService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FindAuthorsByBookTest {

    public static final BookStoreService<Book> bookService = new BookBookStoreService();
    private CreateTest ct = new CreateTest();

    @Test
    public void findAuthorsByBook () {
        ct.createEntity();
        Book b = null;
        List<Author> authors = null;
        try {
            b = bookService.findAll().get(0);
            authors = bookService.findByBook(b);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }
}
