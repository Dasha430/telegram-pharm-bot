package ua.com.alevel.entity;

import lombok.Getter;
import ua.com.alevel.annotation.CsvCell;

@Getter
public class User {

    @CsvCell("id")
    private int id;

    @CsvCell("name")
    private String name;

    @CsvCell("age")
    private int age;

    @CsvCell("role")
    private Role role;

    public User() { }

    public User(int id, String name, int age, Role role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
    }
}
