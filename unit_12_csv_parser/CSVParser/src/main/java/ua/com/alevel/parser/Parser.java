package ua.com.alevel.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.structure.ParsedData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public ParsedData parse(String fileName) {
        List<String[]> data = readAll(fileName);

        String[] title = data.get(0);
        List<String[]> values = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {

            values.add(data.get(i));

        }

        return new ParsedData(title, values);
    }

    private List<String[]> readAll(String fileName) {
        try(CSVReader reader = new CSVReader(new FileReader(fileName))) {
            return reader.readAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return null;
    }
}
