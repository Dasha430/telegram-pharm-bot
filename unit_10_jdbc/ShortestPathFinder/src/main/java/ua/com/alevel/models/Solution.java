package ua.com.alevel.models;

import lombok.Getter;

@Getter
public class Solution extends AbstractModel{
    int cost;

    public Solution(int problem_id, int cost) {
        super(problem_id);
        this.cost = cost;
    }
}
