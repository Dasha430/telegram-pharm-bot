package ua.com.alevel.models;

import lombok.Getter;

@Getter
public class Route extends AbstractModel{

    int from_id;
    int to_id;
    int cost;

    public Route(int id, int from_id, int to_id, int cost) {
        super(id);
        this.from_id = from_id;
        this.to_id = to_id;
        this.cost = cost;
    }
}
