package ua.com.alevel.models;

import lombok.Getter;

@Getter
public abstract class AbstractModel {
    int id;

    public AbstractModel(int id) {
        this.id = id;
    }
}
