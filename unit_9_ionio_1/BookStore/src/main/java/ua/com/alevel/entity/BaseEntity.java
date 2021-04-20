package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {
    private static int idGenerator;
    private int id = 0;
    private Date created;
    private boolean visible;

    public BaseEntity() {
        this.created = new Date();
        id = ++idGenerator;
        visible = true;
    }
}
