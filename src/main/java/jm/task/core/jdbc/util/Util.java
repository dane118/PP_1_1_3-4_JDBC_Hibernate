package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private static Connection connection;

    private static SessionFactory sessionFactory;

    static {
        loadDriver();
    }

    private Util() {
    }

    public static Connection openConnection() {
        try {
            return connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties() {{
            put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            put(Environment.URL, "jdbc:mysql://localhost:3306/my_db?useSSL=false&amp");
            put(Environment.USER, "bestuser");
            put(Environment.PASS, "bestuser");
            put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            put(Environment.SHOW_SQL, "true");
            put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        }};

        return sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
