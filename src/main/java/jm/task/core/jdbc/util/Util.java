package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mysqljpp1.1.4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "cfqvjy1NhfQk44";
    private static Connection connection = null;
    public static Connection getOpen() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void getClose() throws SQLException {
        connection.close();
    }


}

