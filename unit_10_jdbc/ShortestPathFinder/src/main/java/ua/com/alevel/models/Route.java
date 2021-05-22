package ua.com.alevel.models;

import lombok.Value;

@Value
public class Route {
    public int id;
    public int from_id;
    public int to_id;
    public int cost;

    public Route(int id, int from_id, int to_id, int cost) {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.cost = cost;
    }
}
