package ua.com.alevel.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ua.com.alevel.service.FindShortestPathUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindShortestPathController {
    private final String outFile = "output.txt";

    public void run(JsonArray jsonElements) {
        Map<String, Integer> cities = new HashMap<>();
        List<Integer> results = new ArrayList<>();

        JsonElement e = jsonElements.get(0);
        JsonObject o = new Gson().fromJson(e, JsonObject.class);
        int citiesNumber = o.get("number").getAsInt();

        int[][] sumMatrix = createEmptySumMatrix(citiesNumber);

        for (int i = 1; i < citiesNumber + 1; i++) {
            e = jsonElements.get(i);
            o = new Gson().fromJson(e, JsonObject.class);
            String cityName = o.get("name").getAsString();
            cities.put(cityName, i);
            int neighboursNumber = o.get("neighbours number").getAsInt();
            JsonArray indexAndCost = o.getAsJsonArray("index cost");
            for (int j = 0; j < neighboursNumber; j++) {
                String[] stringIndexAndCost = indexAndCost.get(j).getAsString().split(" ");
                sumMatrix[i - 1][Integer.parseInt(stringIndexAndCost[0]) - 1] = Integer.parseInt(stringIndexAndCost[1]);
            }
        }
        e = jsonElements.get(jsonElements.size() - 1);
        o = new Gson().fromJson(e, JsonObject.class);
        int routesNumber = o.get("routes_count").getAsInt();
        JsonArray routesArray = o.getAsJsonArray("routes");
        for (int i = 0; i < routesNumber; i++) {
            String[] route = routesArray.get(i).getAsString().split(" ");
            int res = FindShortestPathUtil.find(citiesNumber, cities.get(route[0]), cities.get(route[1]), sumMatrix);
            results.add(res);
        }

        writeResultsToFile(results);
    }

    public int[][] createEmptySumMatrix(int number) {
        int[][] sumMatrix = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                sumMatrix[i][j] = 0;
            }
        }

        return sumMatrix;
    }

    public void writeResultsToFile(List<Integer> results) {
        Gson gson = new Gson();
        String toOutputFile = results.toString();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(toOutputFile);
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
