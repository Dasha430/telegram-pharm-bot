package ua.com.alevel.jdbc;


import org.apache.log4j.Logger;
import ua.com.alevel.models.Solution;
import ua.com.alevel.util.ShortestPathFinderUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCRunner {

    private final Logger logger = Logger.getLogger(JDBCRunner.class);

    public void run() {

        Properties properties = loadProperties();

        String url = properties.getProperty("url");
        String username = properties.getProperty("user");
        String password = properties.getProperty("password");

        logger.info("Connecting to " + url);
        try (Connection connection = DriverManager.getConnection(url, username, password)){

            connection.setAutoCommit(false);
            logger.info("Getting number of locations from DB");
            int number = countCities(connection);
            int[][] sumMatrix = createEmptySumMatrix(number);

            logger.info("Getting all routes from DB");
            try (Statement getAllRoutes = connection.createStatement()) {
                ResultSet rs = getAllRoutes.executeQuery("SELECT * FROM route");
                while (rs.next()) {
                    int from = rs.getInt("from_id");
                    int to = rs.getInt("to_id");
                    int cost = rs.getInt("cost");
                    sumMatrix[from - 1][to - 1] = cost;
                    sumMatrix[to - 1][from - 1] = cost;
                }
            }

            logger.info("Getting all problems from DB");
            List<Solution> solutions = new ArrayList<>();
            try (Statement getAllProblems = connection.createStatement()) {
                ResultSet rs = getAllProblems.executeQuery("SELECT * FROM problem");
                while (rs.next()) {
                    int problemId = rs.getInt("id");
                    int from = rs.getInt("from_id");
                    int to = rs.getInt("to_id");

                    logger.info("Calculating result");
                    int solutionCost = ShortestPathFinderUtil.find(number, from, to, sumMatrix);
                    logger.info("Finish calculating result");
                    Solution solution = new Solution(problemId, solutionCost);
                    solutions.add(solution);

                }
            }

            logger.info("Inserting solutions into DB");
            try (PreparedStatement insertSolution = connection.prepareStatement(
                    "INSERT IGNORE INTO solution (problem_id, cost) VALUES (?, ?) ")) {

                for (Solution s : solutions) {
                    insertSolution.setInt(1, s.getProblem_id());
                    insertSolution.setInt(2, s.getCost());

                    insertSolution.addBatch();
                }

                insertSolution.executeBatch();
            } catch(SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }

            connection.commit();

        } catch (SQLException e) {
            logger.info("Connection failed");
            System.out.println(e.getMessage());

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

    private int countCities(Connection connection) {
        try (Statement getAllLocationsNumber = connection.createStatement()) {
            ResultSet rs = getAllLocationsNumber.executeQuery("SELECT COUNT(*) FROM location");
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int[][] createEmptySumMatrix(int number) {
        int[][] sumMatrix = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                sumMatrix[i][j] = 0;
            }
        }

        return sumMatrix;
    }
}
