package ua.com.alevel.models;

import lombok.Getter;


@Getter
public class Location extends AbstractModel{
    String name;

    public Location(int id, String name) {
        super(id);
        this.name = name;
    }
}
