package ua.com.alevel;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Checker implements Runnable{

    private String input;

    private volatile String toCheck;

    private static final String FILENAME = "io/output.txt";

    public void setToCheck(String toCheck) {
        this.toCheck = toCheck;
    }

    public Checker(String input) {
        this.input = input;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(500);
                if (!check() && !"quit".equalsIgnoreCase(this.toCheck)) {
                    this.input = this.toCheck;

                    writeToFile(this.input);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean check() {
        return this.input.equals(this.toCheck);
    }

    private void writeToFile(String content) {
        Path path = Paths.get(FILENAME);

        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
           throw new UncheckedIOException(e);
        }
    }
}
