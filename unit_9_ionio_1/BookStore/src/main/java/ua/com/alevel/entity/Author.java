package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Author extends BaseEntity {
    private String firstName;
    private String lastName;
    private List<Integer> books;


    public Author() {
        super();
        books = new ArrayList<>();
    }
}
