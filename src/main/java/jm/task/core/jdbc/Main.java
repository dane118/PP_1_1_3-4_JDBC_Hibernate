package jm.task.core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/my_db";
        String username = "bestuser";
        String password = "bestuser";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println(connection.getTransactionIsolation());
        }

        // реализуйте алгоритм здесь
    }
}
