package ua.com.alevel.view;

import lombok.SneakyThrows;
import ua.com.alevel.controller.BookLibraryController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BookLibraryView {
    private BookLibraryController bookLibraryController = new BookLibraryController();

    @SneakyThrows
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Hello! Please choose what you would like to do.");
        System.out.println("1 - create ");
        System.out.println("2 - read");
        System.out.println("3 - update");
        System.out.println("4 - delete");
        System.out.println("0 - exit");
        bookLibraryController.run(reader.readLine());
    }
}
