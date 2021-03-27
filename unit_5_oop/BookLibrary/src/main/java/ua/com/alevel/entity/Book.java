package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Book extends BaseEntity {

    private String name;
    private List<Author> authors;

    public Book() {
        super();
        authors = new ArrayList<>();
    }

}
