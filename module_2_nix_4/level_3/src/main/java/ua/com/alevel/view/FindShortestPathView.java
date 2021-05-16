package ua.com.alevel.view;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import ua.com.alevel.controller.FindShortestPathController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FindShortestPathView {

    public void run() {

        FindShortestPathController controller = new FindShortestPathController();

        File inputFile = new File("level_3\\input.json");
        String outputFile = "output.txt";
        try {
            String json = FileUtils.readFileToString(inputFile, "UTF-8");
            JsonArray jsonElements = new Gson().fromJson(json, JsonArray.class);
            System.out.println("File input.json has data:");
            for (JsonElement obj: jsonElements) {
                System.out.println(obj);
            }
            controller.run(jsonElements);
            System.out.println("Output is in file output.txt:");
            List<String> results = Files.readAllLines(Paths.get(outputFile), StandardCharsets.UTF_8);
            for (String res: results) {
                System.out.println(res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
