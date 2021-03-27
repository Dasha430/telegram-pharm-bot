package ua.com.alevel.dao.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.LibraryService;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public class BookService implements LibraryService<Book> {

    private final Logger logger = Logger.getLogger(BookService.class);

    private final DBInMemory db = DBInMemory.getInstance();

    @Override
    public void create(Book book) {
        logger.info("start creating book");
        if (db.existById(book.getId(), Book.class)) {
            logger.error("book already exists");
            throw new RuntimeException("book already exists");
        }
        db.createBook(book);
        logger.info("finish creating book");
    }

    @Override
    public void update(Book book) {
        logger.info("start updating book");
        if (!db.existById(book.getId(), Book.class)) {
            logger.error("book doesn't exist, nothing to update");
            throw new RuntimeException("book doesn't exist, nothing to update");
        }
        db.updateBook(book);
        logger.info("finish updating book");
    }

    @Override
    public void delete(Book book) {
        logger.info("start deleting book");
        db.deleteBook(book.getId());
        logger.info("finish deleting book");
    }

    @Override
    public List<Book> findAll() {
        logger.info("getting all books");
        if (db.getAuthors() == null) {
            logger.error("no books found");
            throw  new RuntimeException("no books found");
        }
        return db.getBooks();
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        logger.info("finding books by an author");
        return db.findBooksByAuthor(author);
    }

    @Override
    public List<Author> findByBook(Book book) {
        logger.info("finding authors by a book");
        return db.findAuthorsByBook(book);
    }
}
