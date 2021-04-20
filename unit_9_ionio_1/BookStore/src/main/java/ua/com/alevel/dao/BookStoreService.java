package ua.com.alevel.dao;

import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookStoreService <E extends BaseEntity> {
    void create(E entity) throws ParseException, IOException, CsvException;
    void update(E entity) throws ParseException, IOException, CsvException;
    void delete(E entity) throws ParseException, IOException, CsvException;
    List<E> findAll() throws ParseException, IOException, CsvException;
    List<Author> findByBook(Book book) throws ParseException, IOException, CsvException;
    List<Book> findByAuthor(Author author) throws ParseException, IOException, CsvException;
}
