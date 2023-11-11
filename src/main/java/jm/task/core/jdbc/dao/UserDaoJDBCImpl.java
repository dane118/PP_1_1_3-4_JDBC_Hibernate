package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {

            String createTableUsersSQL = """
                    CREATE TABLE IF NOT EXISTS users
                    (id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(20),
                    last_name VARCHAR(20),
                    age INT);
                    """;

            statement.execute(createTableUsersSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {

            String dropTableUsersSQL = """
                    DROP TABLE IF EXISTS users;
                    """;

            statement.execute(dropTableUsersSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String addNewUserSQL = """
                INSERT INTO users (name, last_name, age)
                VALUES (?, ?, ?);
                """;
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addNewUserSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.printf("User c именем - %S добавлен в базу данных\n", name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String deleteUserByIdSQL = """
                DELETE FROM users WHERE id = ?;
                """;
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserByIdSQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String getAllUsersSQL = """
                SELECT id, name, last_name, age FROM users;
                """;

        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsersSQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String clearUsersTableSQL = """
                DELETE FROM users;
                """;
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(clearUsersTableSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
