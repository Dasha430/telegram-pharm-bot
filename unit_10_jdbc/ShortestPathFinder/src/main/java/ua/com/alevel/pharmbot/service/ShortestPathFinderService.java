package ua.com.alevel.service;

import ua.com.alevel.dao.PathFinderDao;
import ua.com.alevel.dao.impl.PathFinderDaoImpl;
import ua.com.alevel.models.Problem;
import ua.com.alevel.models.Route;
import ua.com.alevel.models.Solution;
import ua.com.alevel.util.ShortestPathFinderUtil;

import java.util.ArrayList;
import java.util.List;

public class ShortestPathFinderService {
    private final PathFinderDao pathFinder = new PathFinderDaoImpl();

    public List<Solution> findShortestPath() {

        List<Problem> problems = pathFinder.readProblems();
        List<Route> routes = pathFinder.readRoutes();
        int citiesNumber = pathFinder.countLocations();
        int[][] sumMatrix = createEmptySumMatrix(citiesNumber);
        List<Solution> solutions = new ArrayList<>();

        for (Route r: routes) {
            int from = r.getFrom_id();
            int to = r.getTo_id();
            int cost = r.getCost();
            sumMatrix[from - 1][to - 1] = cost;
            sumMatrix[to - 1][from - 1] = cost;
        }

        for (Problem p: problems) {
            int from = p.getFrom_id();
            int to = p.getTo_id();
            int problemId = p.getId();
            int solutionCost = ShortestPathFinderUtil.find(citiesNumber, from, to, sumMatrix);
            Solution s = new Solution(problemId, solutionCost);
            solutions.add(s);
        }

        return solutions;
    }

    private int[][] createEmptySumMatrix(int number) {
        int[][] sumMatrix = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                sumMatrix[i][j] = 0;
            }
        }

        return sumMatrix;
    }
}
