package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.PathFinderDao;
import ua.com.alevel.jdbc.JDBCRunner;
import ua.com.alevel.models.*;

import java.util.List;

public class PathFinderDaoImpl implements PathFinderDao {

    private final JDBCRunner runner = new JDBCRunner();


    @Override
    public List<Location> readLocations() {
        return runner.getAllLocations();
    }

    @Override
    public List<Problem> readProblems() {
        return runner.getAllProblems();
    }

    @Override
    public List<Route> readRoutes() {
        return runner.getAllRoutes();
    }

    @Override
    public List<Solution> readSolutions() {
        return runner.getAllSolutions();
    }

    @Override
    public int countLocations() {
        return runner.getLocationsNumber();
    }

    @Override
    public void createSolutions(List<Solution> solutions) {
        runner.createSolutions(solutions);
    }
}
