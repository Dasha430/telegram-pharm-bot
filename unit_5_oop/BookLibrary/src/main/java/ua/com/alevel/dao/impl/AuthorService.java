package ua.com.alevel.dao.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public class AuthorService implements LibraryService<Author> {
    private final Logger logger = Logger.getLogger(AuthorService.class);

    private final DBInMemory db = DBInMemory.getInstance();


    @Override
    public void create(Author author) {
        logger.info("start creating author");
        if (db.existById(author.getId(), Author.class)) {
            logger.error("author already exists");
            throw new RuntimeException("author already exists");
        }
        db.createAuthor(author);

        /* List<Book> b = author.getBooks();
        for (Book book : b) {
            if (db.existById(book.getId(), Book.class)) {

            }
        }*/
        logger.info("finish creating author");
    }

    @Override
    public void update(Author author) {
        logger.info("start updating author");
        if (!db.existById(author.getId(), Author.class)) {
            logger.error("author doesn't exist, nothing to update");
            throw new RuntimeException("author doesn't exist, nothing to update");
        }
        db.updateAuthor(author);
        logger.info("finish updating author");
    }

    @Override
    public void delete(Author author) {
        logger.info("start deleting author");
        db.deleteAuthor(author.getId());
        logger.info("finish deleting author");
    }

    @Override
    public List<Author> findAll() {
        logger.info("getting all authors");
        if (db.getAuthors() == null) {
            logger.error("no authors found");
            throw  new RuntimeException("no authors found");
        }
        return db.getAuthors();
    }

    @Override
    public List<Author> findByBook(Book book) {
        logger.info("finding authors by a book");
        return db.findAuthorsByBook(book);
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        logger.info("finding books by an author");
        return db.findBooksByAuthor(author);
    }
}
