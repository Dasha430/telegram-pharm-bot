package ua.com.alevel.models;

import lombok.Value;

@Value
public class Problem extends AbstractModel{

    int from_id;
    int to_id;

    public Problem(int id, int from_id, int to_id) {
        super(id);
        this.from_id = from_id;
        this.to_id = to_id;
    }
}
