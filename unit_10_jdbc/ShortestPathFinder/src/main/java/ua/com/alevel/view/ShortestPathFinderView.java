package ua.com.alevel.view;

import ua.com.alevel.jdbc.JDBCRunner;

import java.sql.*;
import java.util.Properties;

public class ShortestPathFinderView {

    public void run() {
        JDBCRunner runner = new JDBCRunner();
        System.out.println("Homework # 10");
        System.out.println("Input data:");

        Properties properties = runner.loadProperties();
        String url = properties.getProperty("url");
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);

            try(Statement getAll = connection.createStatement()){
                ResultSet rs = getAll.executeQuery("SELECT * FROM location");

                System.out.println("Locations: ");
                while(rs.next()) {
                    System.out.println("id: " + rs.getInt("id"));
                    System.out.println("locationName: " + rs.getString("locationName"));
                    System.out.println();
                }

                rs = getAll.executeQuery("SELECT * FROM route");
                System.out.println("Routes: ");
                while(rs.next()) {
                    System.out.println("id: " + rs.getInt("id"));
                    System.out.println("from_id: " + rs.getInt("from_id"));
                    System.out.println("to_id: " + rs.getInt("to_id"));
                    System.out.println("cost: " + rs.getInt("cost"));
                    System.out.println();
                }

                rs = getAll.executeQuery("SELECT * FROM problem");
                System.out.println("Problems: ");
                while(rs.next()) {
                    System.out.println("id: " + rs.getInt("id"));
                    System.out.println("from_id: " + rs.getInt("from_id"));
                    System.out.println("to_id: " + rs.getInt("to_id"));
                    System.out.println();
                }
            }




            runner.run();

            System.out.println("Results:");
            try(Statement getAllSolutions = connection.createStatement()){
                ResultSet rs = getAllSolutions.executeQuery("SELECT * FROM solution");

                System.out.println("Solutions: ");
                while(rs.next()) {
                    System.out.println("problem_id: " + rs.getInt("problem_id"));
                    System.out.println("cost: " + rs.getInt("cost"));
                    System.out.println();
                }
            }
       } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
