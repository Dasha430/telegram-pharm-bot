package ua.com.alevel;

import com.opencsv.exceptions.CsvException;
import ua.com.alevel.view.BookStoreView;

import java.io.IOException;
import java.text.ParseException;

public class BookStoreMain {

    public static void main(String[] args) {
        BookStoreView bsv = new BookStoreView();
        try {
            bsv.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }

    }
}
