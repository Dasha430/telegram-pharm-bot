package ua.com.alevel.models;

import lombok.Value;

@Value
public class Solution {
    private int problem_id;
    private int cost;

    public Solution(int problem_id, int cost) {
        this.problem_id = problem_id;
        this.cost = cost;
    }
}
