package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author extends BaseEntity{

    private String firstName;
    private String lastName;


    public Author() {
        super();
    }
}
