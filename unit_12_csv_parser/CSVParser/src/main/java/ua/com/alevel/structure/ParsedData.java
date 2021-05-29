package ua.com.alevel.structure;

import lombok.Getter;

import java.util.List;

@Getter
public class ParsedData {

    private String[] title;
    private List<String[]> data;

    public ParsedData(String[] title, List<String[]> data) {
        this.title = title;
        this.data = data;
    }

    public int rowCount() {
        return data.size();
    }

    public String get(int row, int column) {

        try {
            return data.get(row)[column];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Index out of bounds");
        }
    }

    public String get(int row, String columnName) {

        for (int i = 0; i < title.length; i++) {
            if(title[i].equals(columnName)) {
                return get(row, i);
            }
        }
        throw new RuntimeException("Index out of bounds");
    }
}
