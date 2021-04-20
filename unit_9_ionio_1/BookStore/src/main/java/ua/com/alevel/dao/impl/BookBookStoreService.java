package ua.com.alevel.dao.impl;

import com.opencsv.exceptions.CsvException;
import org.apache.log4j.Logger;
import ua.com.alevel.csv.CSVInMemory;
import ua.com.alevel.dao.BookStoreService;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BookBookStoreService implements BookStoreService<Book> {

    private final Logger logger = Logger.getLogger(BookBookStoreService.class);
    private final CSVInMemory csv = CSVInMemory.getInstance();

    @Override
    public void create(Book entity) throws ParseException, IOException, CsvException {
        logger.info("start creating book");
        if (csv.existById(entity.getId(), Book.class)) {
            logger.error("book already exists");
            return;
        }
        csv.createBook(entity);
        logger.info("finish creating book");
    }

    @Override
    public void update(Book entity) throws ParseException, IOException, CsvException {
        logger.info("start updating book");
        if (!csv.existById(entity.getId(), Book.class)) {
            logger.error("book doesn't exist, nothing to update");
            throw new RuntimeException("book doesn't exist, nothing to update");
        }
        csv.updateBook(entity);
        logger.info("finish updating book");
    }

    @Override
    public void delete(Book entity) throws ParseException, IOException, CsvException {
        logger.info("start deleting book");
        if (!csv.existById(entity.getId(), Book.class)) {
            logger.error("book doesn't exist, nothing to delete");
            throw new RuntimeException("book doesn't exist, nothing to delete");
        }
        csv.deleteBook(entity.getId());
        logger.info("finish deleting book");

    }

    @Override
    public List<Book> findAll() throws ParseException, IOException, CsvException {
        logger.info("getting all books");
        if (csv.getAuthors() == null) {
            logger.error("no books found");
            throw  new RuntimeException("no books found");
        }
        return csv.getBooks();
    }

    @Override
    public List<Author> findByBook(Book book) throws ParseException, IOException, CsvException {
        logger.info("finding authors by a book");
        return csv.findAuthorsByBook(book);
    }

    @Override
    public List<Book> findByAuthor(Author author) throws ParseException, IOException, CsvException {
        logger.info("finding books by an author");
        return csv.findBooksByAuthor(author);
    }
}
