package ua.com.alevel.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class CSVInMemory {

    private static CSVInMemory csv;

    private final String booksFile = "books.csv";
    private final String authorsFile = "authors.csv";

    List<String[]> csvBooksData;
    List<String[]> csvAuthorsData;

    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    private CSVInMemory() {

        csvBooksData = new ArrayList<>();
        csvAuthorsData = new ArrayList<>();
        String[] headerBooks = { "id", "created", "name", "authors", "visible" };
        csvBooksData.add(headerBooks);
        String[] headerAuthors = { "id", "created", "first name", "last name", "books", "visible" };
        csvAuthorsData.add(headerAuthors);

        File fileB = new File(booksFile);
        try {
            fileB.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File fileA = new File(authorsFile);
        try {
            fileA.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CSVInMemory getInstance() {
        if (csv == null) {
            csv = new CSVInMemory();
        }
        return csv;
    }


    public void createBook (Book b) throws IOException {
        String[] book = new String[5];
        book[0] = String.valueOf(b.getId());
        book[1] = String.valueOf(b.getCreated());
        book[2] = b.getName();
        book[3] = "";
        for (Integer i: b.getAuthors()) {
            book[3] += (i + " ");

        }
        book[4] = String.valueOf( b.isVisible());
        csvBooksData.add(book);
        writeBooksToFile();

    }

    public void createAuthor (Author a) throws IOException {
        String[] author = new String[6];
        author[0] = String.valueOf(a.getId());
        author[1] = String.valueOf(a.getCreated());
        author[2] = a.getFirstName();
        author[3] = a.getLastName();
        author[4] = "";
        for (Integer i: a.getBooks()) {
            author[4] += (i + " ");

        }
        author[5] = String.valueOf( a.isVisible());
        csvAuthorsData.add(author);
        writeAuthorsToFile();

    }

    public List<Book> findBooksByAuthor(Author a) throws IOException, CsvException, ParseException {
        List<Book> allBooks = getBooks();
        return allBooks.stream()
                .filter(b -> b.getAuthors().stream().anyMatch(k -> k == a.getId()))
                .collect(Collectors.toList());
    }

    public List<Author> findAuthorsByBook(Book b) throws IOException, CsvException, ParseException {

        List<Author> allAuthors = getAuthors();
        return allAuthors.stream()
                .filter(a -> a.getBooks().stream().anyMatch(k -> k == b.getId()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooks() throws ParseException, IOException, CsvException {
        List<String[]> csvData;
        List<Book> books = new ArrayList<>();



        try (CSVReader reader = new CSVReader(new FileReader(booksFile))) {
            csvData = reader.readAll();
        }

        for (int i = 1; i < csvData.size();i++) {
            String[] book = csvData.get(i);
            String[] authorKeys = book[3].split(" ");
            Book b = new Book();
            b.setId(Integer.parseInt(book[0]));
            b.setCreated(format.parse(book[1]));
            b.setName(book[2]);
            List<Integer> authors = new ArrayList<>();
            for (String key: authorKeys) {
                if (!key.equals("")) {
                    authors.add(Integer.parseInt(key));
                }

            }
            b.setAuthors(authors);
            b.setVisible(Boolean.parseBoolean(book[4]));
            if (b.isVisible()) {
                books.add(b);
            }

        }
        return books;

    }

    public List<Author> getAuthors() throws ParseException, IOException, CsvException{
        List<Author> authors = new ArrayList<>();
        List<String[]> csvData;

        try (CSVReader reader = new CSVReader(new FileReader(authorsFile))) {
            csvData = reader.readAll();
        }

        for (int i = 1; i < csvData.size(); i++) {
            String[] author = csvData.get(i);
            String[] bookKeys = author[4].split(" ");

                Author a = new Author();
                a.setId(Integer.parseInt(author[0]));
                a.setCreated(format.parse(author[1]));
                a.setFirstName(author[2]);
                a.setLastName(author[3]);
                List<Integer> books = new ArrayList<>();
                for (String key: bookKeys) {
                    if (!key.equals("")) {
                        books.add(Integer.parseInt(key));
                    }
                }
                a.setBooks(books);
                a.setVisible(Boolean.parseBoolean(author[5]));
                if (a.isVisible()) {
                    authors.add(a);
                }
            }

        return authors;
    }

    public String[] findBookById(int id) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(booksFile))) {
            csvBooksData = reader.readAll();
        }
        String[] current = csvBooksData.stream()
                .filter(book -> book[0].equals(String.valueOf(id))).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("book not found");
        }
        return current;
    }

    public String[] findAuthorById(int id) throws IOException, CsvException {

        try (CSVReader reader = new CSVReader(new FileReader(authorsFile))) {
            csvAuthorsData = reader.readAll();
        }
        String[] current = csvAuthorsData.stream()
                .filter(author -> author[0].equals(String.valueOf(id))).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("author not found");
        }
        return current;
    }

    public void updateBook (Book book) throws IOException, CsvException {
        String[] current = findBookById(book.getId());
        current[2] = book.getName();
        current[3] = "";
        for (Integer i: book.getAuthors()) {
            current[3] += (i + " ");

        }
        current[4] = String.valueOf( book.isVisible());
        writeBooksToFile();

    }
    public void updateAuthor(Author author) throws IOException, CsvException {
        String[] current = findAuthorById(author.getId());
        current[2] = author.getFirstName();
        current[3] = author.getLastName();
        current[4] = "";
        for (Integer i: author.getBooks()) {
                current[4] += (i + " ");

        }
        current[5] = String.valueOf(author.isVisible());
        writeAuthorsToFile();

    }


    private void writeAuthorsToFile() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(authorsFile))) {
            writer.writeAll(csvAuthorsData);


        }
    }

    private void writeBooksToFile() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(booksFile))) {
            writer.writeAll(csvBooksData);

        }
    }

    public void deleteAuthor(int id) throws ParseException, IOException, CsvException {
        Author author = getAuthors().stream().filter(a -> a.getId() == id).findFirst().orElse(null);

        if (author == null) {
            throw new RuntimeException("author not found");
        }

        author.setVisible(false);
        updateAuthor(author);
    }

    public void deleteBook(int id) throws ParseException, IOException, CsvException {
        Book book = getBooks().stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        if (book == null) {
            throw new RuntimeException("book not found");
        }

        book.setVisible(false);
        updateBook(book);

    }

    public <C extends BaseEntity> boolean existById(int id, Class<C> aClass) throws ParseException, IOException, CsvException {
        if (aClass.isAssignableFrom(Book.class)) {
            return getBooks().stream().anyMatch(book -> book.getId() == id);
        }
        return getAuthors().stream().anyMatch(author -> author.getId() == id);
    }

}
