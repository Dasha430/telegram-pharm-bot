package ua.com.alevel.methods;

import org.junit.Assert;
import org.junit.Test;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.dao.impl.AuthorService;
import ua.com.alevel.dao.impl.BookService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class CreateTest {
    private static final LibraryService<Author> authorService = new AuthorService();
    private static final LibraryService<Book> bookService = new BookService();
    private  static final String authorStr = "Author";
    private  static final String bookStr = "Book";

    @Test
    public void createEntity() {

        List<Author> authors = authorService.findAll();
        List<Book> books = bookService.findAll();
        int aPrevious = authors.size();
        int bPrevious = books.size();

        Author a = new Author();
        Book b = new Book();

        a.setFirstName(authorStr);
        a.setLastName(authorStr);
        List<Author> listA = new ArrayList<>();
        listA.add(a);

        b.setName(bookStr);
        b.setAuthors(listA);
        List<Book> listB = new ArrayList<>();
        listB.add(b);

        a.setBooks(listB);
        authorService.create(a);
        bookService.create(b);

        authors = authorService.findAll();
        books = bookService.findAll();
        int aLast = authors.size();
        int bLast = books.size();
        Assert.assertEquals(aPrevious + 1, aLast);
        Assert.assertEquals(bPrevious + 1, bLast);
    }
}
