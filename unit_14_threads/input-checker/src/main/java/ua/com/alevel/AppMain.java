package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class AppMain {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        Checker checker = new Checker("");
        checker.setToCheck("");
        new Thread(checker).start();

        while (true) {
            try {
                input = reader.readLine();
                checker.setToCheck(input);

                if ("quit".equalsIgnoreCase(input)) {
                    return;
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

        }

    }
}
