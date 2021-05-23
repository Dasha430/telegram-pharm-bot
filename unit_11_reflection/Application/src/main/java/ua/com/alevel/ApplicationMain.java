package ua.com.alevel;

import ua.com.alevel.annotation.analyzer.PropertyKeyAnalyzer;
import ua.com.alevel.entity.AppProperties;

public class ApplicationMain {

    private static final PropertyKeyAnalyzer analyzer = new PropertyKeyAnalyzer();

    public static void main(String[] args) {
        System.out.println("Homework # 11");
        AppProperties property = new AppProperties("My App");
        try {
            analyzer.analyze(property);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Results:");
        System.out.println("datasource url: " + property.datasourceUrl);
        System.out.println("main page url: " + property.mainPageUrl);
        System.out.println("user: " + property.username);
        System.out.println("password: " + property.password);
        System.out.println("maximum connections:" + property.maxConnections);
    }
}
