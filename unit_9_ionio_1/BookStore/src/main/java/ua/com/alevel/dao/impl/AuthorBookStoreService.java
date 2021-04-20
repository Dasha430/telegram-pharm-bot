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

public class AuthorBookStoreService implements BookStoreService<Author> {

    private final Logger logger = Logger.getLogger(AuthorBookStoreService.class);
    private final CSVInMemory csv = CSVInMemory.getInstance();

    @Override
    public void create(Author entity) throws ParseException, IOException, CsvException {
        logger.info("start creating author");
        if (csv.existById(entity.getId(), Author.class)) {
            logger.error("author already exists");
            return;
        }

        csv.createAuthor(entity);
        logger.info("finish creating author");
    }

    @Override
    public void update(Author entity) throws ParseException, IOException, CsvException {
        logger.info("start updating author");
        if (!csv.existById(entity.getId(), Author.class)) {
            logger.error("author doesn't exist, nothing to update");
            throw new RuntimeException("author doesn't exist, nothing to update");
        }
        csv.updateAuthor(entity);
        logger.info("finish updating author");
    }

    @Override
    public void delete(Author entity) throws ParseException, IOException, CsvException {
        logger.info("start deleting author");
        if (!csv.existById(entity.getId(), Author.class)) {
            logger.error("author doesn't exist, nothing to delete");
            throw new RuntimeException("author doesn't exist, nothing to delete");
        }
        csv.deleteAuthor(entity.getId());
        logger.info("finish deleting author");
    }

    @Override
    public List<Author> findAll() throws ParseException, IOException, CsvException {
        logger.info("getting all authors");
        if (csv.getAuthors() == null) {
            logger.error("no authors found");
            throw new RuntimeException("no authors found");
        }
        return csv.getAuthors();
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
