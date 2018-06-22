package edu.northeastern.cs5200.cs5200spring2018liu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://cs5200.cwavskblaoud.us-east-2.rds.amazonaws.com/hw2_liu_guolin_fall_2017";
    private static final String USER = "glyn";
    private static final String PASSWORD = "qq452977491";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
