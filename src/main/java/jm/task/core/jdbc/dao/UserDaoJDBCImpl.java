package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl  implements UserDao {

    private static final String CREATE_USERS_TABDLE_SQL = """
            CREATE TABLE `user` (
              `id` int NOT NULL AUTO_INCREMENT,
              `name` varchar(45) NOT NULL,
              `lastName` varchar(45) NOT NULL,
              `age` int DEFAULT NULL,
              PRIMARY KEY (`id`)
            ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3""";
    private static final String DROP_USERS_TABLE_SQL = "DROP TABLE IF EXISTS user";
    private static final String SAVE_USER_SQL = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
    private static final String REMOVAE_USER_BY_ID_SQL = "DELETE FROM user WHERE id = ?";

    private static final String GET_ALL_USERS_SQL = "SELECT id, name, lastName, age FROM user";
    private static final String CLEAN_USERS_TABLE_SQL = "TRUNCATE TABLE user";

    public UserDaoJDBCImpl() {

    }



    public void createUsersTable() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(CREATE_USERS_TABDLE_SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(DROP_USERS_TABLE_SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(SAVE_USER_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(REMOVAE_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (var connection = Util.getOpen();
             var resultSet = connection.createStatement().executeQuery(GET_ALL_USERS_SQL)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(id, name, lastName, age);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(CLEAN_USERS_TABLE_SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}