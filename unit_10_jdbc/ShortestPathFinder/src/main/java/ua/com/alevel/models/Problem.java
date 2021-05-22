package ua.com.alevel.models;

import lombok.Value;

@Value
public class Problem {

    public int id;
    public int from_id;
    public int to_id;

    public Problem(int id, int from_id, int to_id) {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
    }
}
