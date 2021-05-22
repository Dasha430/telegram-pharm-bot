package ua.com.alevel.view;

import ua.com.alevel.dao.PathFinderDao;
import ua.com.alevel.dao.impl.PathFinderDaoImpl;
import ua.com.alevel.models.Location;
import ua.com.alevel.models.Problem;
import ua.com.alevel.models.Route;
import ua.com.alevel.models.Solution;
import ua.com.alevel.service.ShortestPathFinderService;

import java.util.List;


public class ShortestPathFinderView {

    private final ShortestPathFinderService service = new ShortestPathFinderService();
    private final PathFinderDao pathFinder = new PathFinderDaoImpl();

    public void run() {

        System.out.println("Homework # 10");
        System.out.println("Input data:");

        List<Location> locations = pathFinder.readLocations();

        System.out.println("Locations: ");
        for (Location l: locations) {
            System.out.println("id: " + l.getId());
            System.out.println("locationName: " + l.getName());
            System.out.println();
        }

        System.out.println("Routes: ");
        List<Route> routes = pathFinder.readRoutes();
        for (Route r: routes) {
            System.out.println("id: " + r.getId());
            System.out.println("from_id: " + r.getFrom_id());
            System.out.println("to_id: " + r.getTo_id());
            System.out.println("cost: " + r.getCost());
            System.out.println();
        }

        System.out.println("Problems: ");
        List<Problem> problems = pathFinder.readProblems();
        for (Problem p: problems) {
            System.out.println("id: " + p.getId());
            System.out.println("from_id: " + p.getFrom_id());
            System.out.println("to_id: " + p.getTo_id());
            System.out.println();
        }

        List<Solution> solutions = service.findShortestPath();
        System.out.println("Solutions: ");
        for(Solution s : solutions) {
            System.out.println("problem_id: " + s.getId());
            System.out.println("cost: " + s.getCost());
            System.out.println();
        }


    }
}
