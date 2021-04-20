package ua.com.alevel.methods;

import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import ua.com.alevel.dao.BookStoreService;
import ua.com.alevel.dao.impl.AuthorBookStoreService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FindBooksByAuthorTest {

    public static final BookStoreService<Author> authorService = new AuthorBookStoreService();
    private CreateTest ct = new CreateTest();

    @Test
    public void findBooksByAuthor() {
        ct.createEntity();
        Author a = null;
        List<Book> books = null;
        try {
            a = authorService.findAll().get(0);
            books = authorService.findByAuthor(a);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }
}
