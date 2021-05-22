package ua.com.alevel.dao;

import ua.com.alevel.models.*;

import java.util.List;

public interface PathFinderDao {

    List<Location> readLocations();
    List<Problem> readProblems();
    List<Route> readRoutes();
    List<Solution> readSolutions();
    int countLocations();
    void createSolutions(List<Solution> solutions);

}
