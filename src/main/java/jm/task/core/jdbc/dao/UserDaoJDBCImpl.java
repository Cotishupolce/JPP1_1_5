package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static final UserDaoJDBCImpl INSTANCE = new UserDaoJDBCImpl();
    private static final String CREATE_USERS_TABDLE_SQL = """
            CREATE TABLE `user` (
              `id` int NOT NULL AUTO_INCREMENT,
              `name` varchar(45) NOT NULL,
              `lastName` varchar(45) NOT NULL,
              `age` int DEFAULT NULL,
              PRIMARY KEY (`id`)
            ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;""";
    private static final String DROP_USERS_TABLE_SQL = "delete from user;";
    private static final String SAVE_USER_SQL = "insert into user (name, lastName, age) value(?, ?, ?);";
    private static final String REMOVAE_USER_BY_ID_SQL = "delete from user where id =?;";

    private static final String GET_ALL_USERS_SQL = "select id, name, lastName, age from user;";
    private static final String CLEAN_USERS_TABLE_SQL = "truncate user;";

    public UserDaoJDBCImpl() {

    }

    public static UserDaoJDBCImpl getInstance() {
        return INSTANCE;
    }

    public void createUsersTable() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(CREATE_USERS_TABDLE_SQL)) {
            preparedStatement.setLong(1, User.getId());
            preparedStatement.setString(2, User.getName());
            preparedStatement.setString(3, User.getLastName());
            preparedStatement.setByte(4, User.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(DROP_USERS_TABLE_SQL)) {
            preparedStatement.setLong(1, User.getId());
            preparedStatement.setString(2, User.getName());
            preparedStatement.setString(3, User.getLastName());
            preparedStatement.setByte(4, User.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(SAVE_USER_SQL)) {
            preparedStatement.setString(1, User.getName());
            preparedStatement.setString(2, User.getLastName());
            preparedStatement.setByte(3, User.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(REMOVAE_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(GET_ALL_USERS_SQL)) {
             var resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(buldUser(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private User buldUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getString("name"),
                resultSet.getString("lastName"),
                resultSet.getByte("age")
        );
    }

    public void cleanUsersTable() {

        try (var connection = Util.getOpen();
             var preparedStatement = connection.prepareStatement(CLEAN_USERS_TABLE_SQL)) {
            preparedStatement.setLong(1, User.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}