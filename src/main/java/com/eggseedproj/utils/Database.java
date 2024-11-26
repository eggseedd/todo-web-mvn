package com.eggseedproj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://root:hMXLcSvuJUlDnpOCFOxvSIVKSDxuCNct@autorack.proxy.rlwy.net:53152/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "hMXLcSvuJUlDnpOCFOxvSIVKSDxuCNct";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC driver");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
