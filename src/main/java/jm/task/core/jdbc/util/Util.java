package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysqljpp1.1.4";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "cfqvjy1NhfQk44";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVE);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
