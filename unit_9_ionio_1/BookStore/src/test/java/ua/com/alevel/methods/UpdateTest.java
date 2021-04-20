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

public class UpdateTest {

    private static final BookStoreService<Book> bookService = new BookBookStoreService();
    private static final BookStoreService<Author> authorService = new AuthorBookStoreService();
    private static final String authorFirstName = "Jane";
    private static final String authorLastName = "Austen";
    private static final String bookStr = "Emma";

    private CreateTest ct = new CreateTest();

    @Test
    public void updateEntity() {
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

        a.setFirstName(authorFirstName);
        a.setLastName(authorLastName);
        b.setName(bookStr);

        try {
            authorService.update(a);
            bookService.update(b);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }


        Assert.assertEquals(authorLastName, a.getLastName());
        Assert.assertEquals(authorFirstName, a.getFirstName());
        Assert.assertEquals(bookStr, b.getName());
    }


}
