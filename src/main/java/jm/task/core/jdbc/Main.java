package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

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
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan","Smirnov",(byte) 28);
        userService.saveUser("Denis","Morozov",(byte) 31);
        userService.saveUser("Sveta","Ivanova",(byte) 25);
        userService.saveUser("Petr","Letov",(byte) 44);

        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

