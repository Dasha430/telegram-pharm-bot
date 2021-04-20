package ua.com.alevel.view;

import com.opencsv.exceptions.CsvException;
import ua.com.alevel.controller.BookStoreController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class BookStoreView {

    private final BookStoreController bsc = new BookStoreController();
    public void run() throws IOException, ParseException, CsvException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Hello! Please choose what you would like to do.");
        System.out.println("1 - create ");
        System.out.println("2 - read");
        System.out.println("3 - update");
        System.out.println("4 - delete");
        System.out.println("0 - exit");
        bsc.run(reader.readLine());
    }
}
