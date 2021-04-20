package ua.com.alevel;

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

public class BookStoreCommonTest {

    private static final BookStoreService<Book> bookService = new BookBookStoreService();
    private static final BookStoreService<Author> authorService = new AuthorBookStoreService();

    @Test
    public void test() {
        List<Author> authors = null;
        List<Book> books = null;
        try {
            authors = authorService.findAll();

            books = bookService.findAll();
            int aPrevious = authors.size();
            int bPrevious = books.size();

            List<Integer> bookList = new ArrayList<>();
            List<Integer> authorList = new ArrayList();

            Author a1 = createAuthor("William Shakespeare");
            Book b1 = createBook("Book1");
            Book b2 = createBook("Book2");
            bookList.add(b1.getId());
            bookList.add(b2.getId());
            authorList.add(a1.getId());
            a1.setBooks(bookList);
            b1.setAuthors(authorList);
            b2.setAuthors(authorList);
            bookService.create(b1);
            bookService.create(b2);
            authorService.create(a1);
            Author a2 = createAuthor("John Smith");
            bookList.clear();
            bookList.add(b1.getId());
            authorList.add(a2.getId());
            b1.setAuthors(authorList);
            a2.setBooks(bookList);
            authorService.create(a2);
            bookService.update(b1);


            authors = authorService.findAll();
            books = bookService.findAll();
            int aLast = authors.size();
            int bLast = books.size();
            //Assert.assertEquals(aPrevious + 3, aLast);
            //Assert.assertEquals(bPrevious + 2, bLast);



            deleteAll();

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() throws ParseException, IOException, CsvException {
        int size = bookService.findAll().size();
        while (size != 0) {
            int end = bookService.findAll().size() - 1;
            bookService.delete(bookService.findAll().get(end));
            size = bookService.findAll().size();
        }

        size = authorService.findAll().size();
        while (size != 0) {
            int end = authorService.findAll().size() - 1;
            authorService.delete(authorService.findAll().get(end));
            size = authorService.findAll().size();
        }
    }

    public Book createBook(String name) throws ParseException, IOException, CsvException {
        Book b = new Book();
        b.setName(name);
        return b;
    }

    public Author createAuthor(String name) throws ParseException, IOException, CsvException {
        Author a = new Author();
        a.setFirstName(name.split(" ")[0]);
        a.setLastName(name.split(" ")[1]);
        return a;
    }
}
