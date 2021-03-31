package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DBInMemory {

    private static DBInMemory db;

    private final List<Book> books;
    private final List<Author> authors;

    private DBInMemory() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public static DBInMemory getInstance() {
        if (db == null) {
            db = new DBInMemory();
        }
        return db;
    }

    private <C extends BaseEntity> String generateId(String id, Class<C> aClass) {
        if (aClass.isAssignableFrom(Book.class)) {
            if (books.stream().anyMatch(book -> book.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), Book.class);
            }
        } else {
            if (authors.stream().anyMatch(author -> author.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), Author.class);
            }

        }
        return id;
    }


    public <C extends BaseEntity> boolean existById(String id, Class<C> aClass) {
        if (aClass.isAssignableFrom(Book.class)) {
            return books.stream().anyMatch(book -> book.getId().equals(id));
        }
        return authors.stream().anyMatch(author -> author.getId().equals(id));
    }


    public void createBook (Book b) {
        b.setId(generateId(UUID.randomUUID().toString(), Book.class));
        books.add(b);

    }
    public void createAuthor(Author a) {
        a.setId(generateId(UUID.randomUUID().toString(), Author.class));
        authors.add(a);
    }



    public List<Book> findBooksByAuthor(Author a) {

        return this.books.stream()
                .filter(book -> book.getAuthors().stream().anyMatch(author -> a.getId().equals(author.getId())))
                .collect(Collectors.toList());
    }
    public List<Author> findAuthorsByBook(Book b) {

        return b.getAuthors();

    }

    public Book findBookById(String id) {
        Book current = books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("book not found");
        }
        return current;
    }
    public Author findAuthorById(String id) {
        Author current = authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("author not found");
        }
        return current;
    }


    public void updateBook (Book book) {
        Book current = findBookById(book.getId());
        current.setName(book.getName());
        current.setAuthors(book.getAuthors());

    }
    public void updateAuthor(Author author) {
        Author current = findAuthorById(author.getId());
        current.setFirstName(author.getFirstName());
        current.setLastName(author.getLastName());
    }


    public void deleteBook(String id) {
        Book current = findBookById(id);
        current.setDeleted(true);
        //this.books.removeIf(book -> book.getId().equals(current.getId()));
    }
    public void deleteAuthor(String id) {
        Author current = findAuthorById(id);
        this.authors.removeIf(author -> author.getId().equals(current.getId()));
    }


    public List<Book> getBooks() {
        return books;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
