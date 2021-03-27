package ua.com.alevel.methods;

import org.junit.Assert;
import org.junit.Test;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.AuthorService;
import ua.com.alevel.dao.impl.BookService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;


public class DeleteTest {
    private static final LibraryService<Author> authorService = new AuthorService();
    private static final LibraryService<Book> bookService = new BookService();
    private CreateTest ct = new CreateTest();

    private static final String authorStr = "Author";
    private static final String bookStr = "Book";

    @Test
    public void deleteEntity() {

        List<Author> authors = authorService.findAll();
        List<Book> books = bookService.findAll();
        int aPrevious = authors.size();
        int bPrevious = books.size();

        ct.createEntity();
        Author a = authorService.findAll().get(0);
        Book b = bookService.findAll().get(0);

        authorService.delete(a);
        bookService.delete(b);

        authors = authorService.findAll();
        books = bookService.findAll();
        int aLast = authors.size();
        int bLast = books.size();

        Assert.assertEquals(aPrevious, aLast);
        Assert.assertEquals(bPrevious, bLast);
    }
}
