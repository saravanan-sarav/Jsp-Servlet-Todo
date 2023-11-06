package com.codewithsarav.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/todo?useSSL=false";
    private static final String connectionUsername = "root";
    private static final String connectionPassword = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, connectionUsername, connectionPassword);
            System.out.println("Done" + !connection.isClosed());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
