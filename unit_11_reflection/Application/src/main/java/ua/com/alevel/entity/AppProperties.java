package ua.com.alevel.entity;

import ua.com.alevel.annotation.PropertyKey;

public class AppProperties {

    @PropertyKey("main.url")
    public String mainPageUrl;

    @PropertyKey("datasource.url")
    public String datasourceUrl;

    @PropertyKey("user")
    public String username;

    @PropertyKey("password")
    public String password;

    @PropertyKey("connections.limit")
    public int maxConnections;

    public String appName;

    public AppProperties(String appName) {
        this.appName = appName;

    }

}
