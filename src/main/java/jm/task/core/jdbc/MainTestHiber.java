package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class MainTestHiber {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vasya", "Pupkin", (byte) 50);


    }
}
