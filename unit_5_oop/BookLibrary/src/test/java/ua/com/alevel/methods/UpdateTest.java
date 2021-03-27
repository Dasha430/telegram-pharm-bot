package ua.com.alevel.methods;

import org.junit.Assert;
import org.junit.Test;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.AuthorService;
import ua.com.alevel.dao.impl.BookService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class UpdateTest {
    private static final LibraryService<Author> authorService = new AuthorService();
    private static final LibraryService<Book> bookService = new BookService();
    private CreateTest ct = new CreateTest();

    private static final String authorStr = "Author1";
    private static final String bookStr = "Book1";

    @Test
    public void updateEntity() {
        ct.createEntity();
        Author a = authorService.findAll().get(0);
        Book b = bookService.findAll().get(0);

        a.setLastName(authorStr);
        b.setName(bookStr);

        authorService.update(a);
        bookService.update(b);

        Assert.assertEquals(authorStr, a.getLastName());
        Assert.assertEquals(bookStr, b.getName());
    }
}
