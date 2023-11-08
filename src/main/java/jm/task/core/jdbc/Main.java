package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

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
        String url = "jdbc:mysql://localhost:3306/my_db";
        String username = "bestuser";
        String password = "bestuser";

        try (Connection connection = Util.openConnection()) {
            System.out.println(connection.getTransactionIsolation());
        }

        // реализуйте алгоритм здесь
    }
}
