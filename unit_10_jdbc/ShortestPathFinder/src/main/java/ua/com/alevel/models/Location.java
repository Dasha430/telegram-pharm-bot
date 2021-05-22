package ua.com.alevel.models;

import lombok.Value;


@Value
public class Location {
    public int id;
    public String name;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
