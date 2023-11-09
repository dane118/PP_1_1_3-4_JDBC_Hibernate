package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.*;

/**
 * Алгоритм работы приложения:
 * В методе main класса Main должны происходить следующие операции:
 * Создание таблицы User(ов)
 * Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
 * должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
 * Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
 * Очистка таблицы User(ов)
 * Удаление таблицы
 */

public class Main {
    public static void main(String[] args) throws SQLException {




//        ***************************** Черновик *************************************

        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {
//            System.out.println(connection.getTransactionIsolation());

//            String createTable = """
//                    CREATE TABLE IF NOT EXISTS users
//                    (id INT,
//                    age INT,
//                    First_name VARCHAR(20)
//                    )""";
//            String deleteTable = """
//                    DROP TABLE users
//                    """;
//
//            String insertRows = """
//                    INSERT INTO users(id, age, First_name)
//                    VALUES (1, 25, 'Danil'),
//                    (2, 85, 'Ivan'),
//                    (3, 32, 'Petr'),
//                    (4, 54, 'Kolya')
//                    """;
//            String select = """
//                    SELECT * FROM users
//                    """;
//
//            int i = statement.executeUpdate(insertRows);
//
//            ResultSet resultSet = statement.executeQuery(select);
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getLong("id"));
//                System.out.println(resultSet.getString("First_name"));
//                System.out.println("-------");
//            }

            String sql = """
                    SELECT * FROM users WHERE age > ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, 80);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong(1) + " " +  resultSet.getString(3));
                System.out.println("----");
            }


        }
        // реализуйте алгоритм здесь
    }
}
