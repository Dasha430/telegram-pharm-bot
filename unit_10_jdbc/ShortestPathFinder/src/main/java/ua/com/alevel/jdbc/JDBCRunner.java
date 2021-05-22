package ua.com.alevel.jdbc;


import org.apache.log4j.Logger;
import ua.com.alevel.models.Location;
import ua.com.alevel.models.Problem;
import ua.com.alevel.models.Route;
import ua.com.alevel.models.Solution;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCRunner {

    private final Logger logger = Logger.getLogger(JDBCRunner.class);

    private final Properties properties = loadProperties();

    private final String url = properties.getProperty("url");
    private final String username = properties.getProperty("user");
    private final String password = properties.getProperty("password");

    public int getLocationsNumber() {
        logger.info("Connecting to " + url);
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            connection.setAutoCommit(false);
            logger.info("Getting number of locations from DB");

            try (Statement getAllLocationsNumber = connection.createStatement()) {
                ResultSet rs = getAllLocationsNumber.executeQuery("SELECT COUNT(*) FROM location");
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        logger.info("Connecting to " + url);
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);

            try (Statement getAll = connection.createStatement()) {
                logger.info("Getting all locations from DB");
                ResultSet rs = getAll.executeQuery("SELECT * FROM location");

                while (rs.next()) {
                    Location l = new Location(rs.getInt("id"),
                            rs.getString("locationName"));
                    locations.add(l);
                }
                return locations;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        logger.info("Connecting to " + url);
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);

            try (Statement getAll = connection.createStatement()) {
                logger.info("Getting all routes from DB");
                ResultSet rs = getAll.executeQuery("SELECT * FROM route");

                while (rs.next()) {
                    Route r = new Route(rs.getInt("id"),
                            rs.getInt("from_id"),
                            rs.getInt("to_id"),
                            rs.getInt("cost"));
                    routes.add(r);
                }
                return routes;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Problem> getAllProblems() {
        logger.info("Connecting to " + url);
        List<Problem> problems = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);

            try (Statement getAll = connection.createStatement()) {
                logger.info("Getting all problems from DB");
                ResultSet rs = getAll.executeQuery("SELECT * FROM problem");

                while (rs.next()) {
                    Problem p = new Problem(rs.getInt("id"),
                            rs.getInt("from_id"),
                            rs.getInt("to_id"));
                    problems.add(p);
                }
                return problems;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Solution> getAllSolutions() {
        logger.info("Connecting to " + url);
        List<Solution> solutions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);

            try (Statement getAll = connection.createStatement()) {
                logger.info("Getting all solutions from DB");
                ResultSet rs = getAll.executeQuery("SELECT * FROM solution");

                while (rs.next()) {
                    Solution s = new Solution(rs.getInt("problem_id"),
                            rs.getInt("cost"));
                    solutions.add(s);
                }
                return solutions;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void createSolutions(List<Solution> solutions) {
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);
            logger.info("Inserting solutions into DB");
            try (PreparedStatement insertSolution = connection.prepareStatement(
                    "INSERT IGNORE INTO solution (problem_id, cost) VALUES (?, ?) ")) {

                for (Solution s : solutions) {
                    insertSolution.setInt(1, s.getId());
                    insertSolution.setInt(2, s.getCost());

                    insertSolution.addBatch();
                }

                insertSolution.executeBatch();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }

            connection.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Properties loadProperties() {

        Properties properties = new Properties();

        try(InputStream input = JDBCRunner.class.getResourceAsStream("/jdbc.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return properties;
    }


}
