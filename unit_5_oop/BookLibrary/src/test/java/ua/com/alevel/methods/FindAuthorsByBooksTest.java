package ua.com.alevel.methods;

import org.junit.Test;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.BookService;
import ua.com.alevel.entity.Book;

public class FindAuthorsByBooksTest {

    private static final LibraryService<Book> bookService = new BookService();
    private CreateTest ct = new CreateTest();

    @Test
    public void findAuthorsByBook () {
        ct.createEntity();
        Book b = bookService.findAll().get(0);
        bookService.findByBook(b);
    }
}
