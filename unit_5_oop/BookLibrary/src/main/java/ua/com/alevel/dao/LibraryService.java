package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;

import java.util.List;

public interface LibraryService<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(E entity);
    List<E> findAll();
    List<Author> findByBook(Book book);
    List<Book> findByAuthor(Author author);
}
