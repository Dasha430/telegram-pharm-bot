package ua.com.alevel.methods;

import org.junit.Test;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.AuthorService;
import ua.com.alevel.entity.Author;


public class FindBooksByAuthorTest {

    private static final LibraryService<Author> authorService = new AuthorService();
    private CreateTest ct = new CreateTest();

    @Test
    public void findBooksByAuthor() {
        ct.createEntity();
        Author a = authorService.findAll().get(0);
        authorService.findByAuthor(a);
    }
}
