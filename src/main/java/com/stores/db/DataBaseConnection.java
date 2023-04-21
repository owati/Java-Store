package com.stores.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataBaseConnection {
    private static Connection conn;
    final private static String connectionString = "jdbc:postgresql://localhost/Store";

    private DataBaseConnection () {
        // to prevent any initializations
    }

    public static synchronized Connection getConnection() 
    throws SQLException {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(connectionString, "postgres", "police");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
        }
        return conn;
    }
}
