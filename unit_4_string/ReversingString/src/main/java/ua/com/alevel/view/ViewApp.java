package ua.com.alevel.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ua.com.alevel.controller.ConsoleController;

public class ViewApp {
    private final ConsoleController consoleController = new ConsoleController();


    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! Enter your string that you want to reverse");

        try {
            String src = bufferedReader.readLine();
            System.out.println("Please select how you would like to reverse your string");
            System.out.println("1 - usual reverse");
            System.out.println("2 - reverse a substring");
            System.out.println("3 - reverse from specified index to the other specified index");
            System.out.println("0 - exit a program");
            consoleController.run(src);
        } catch (IOException e) {
            e.getMessage();
        }

    }
}