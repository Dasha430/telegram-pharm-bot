package ua.com.alevel.view;

import ua.com.alevel.controller.UniqueNameFinderController;
import ua.com.alevel.util.UniqueNameFinderUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UniqueNameFinderView {

    public void run() throws IOException {
        UniqueNameFinderController controller = new UniqueNameFinderController();
        System.out.println("File names.txt contains names:");
        List<String> allNames = Files.readAllLines(Paths.get("level_2\\names.txt"),
                StandardCharsets.UTF_8);
        for (String name: allNames) {
            System.out.println(name);
        }
        System.out.println("The first unique name is:");
        System.out.println(controller.run(allNames));

    }
}
