package ua.com.alevel.view;

import ua.com.alevel.controller.DateFormatChangeController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DateFormatChangeView {

    private final DateFormatChangeController controller = new DateFormatChangeController();
    public void run() throws IOException {
        List<String> allDates = Files.readAllLines(Paths.get("level_1\\dates.txt"),
                StandardCharsets.UTF_8);
        System.out.println("File dates.txt contains dates:");
        for (String date: allDates) {
            System.out.println(date);
        }
        System.out.println("These dates after changing the format" +
                " (dates with wrong format are ignored):");
        controller.run(allDates);
    }
}
