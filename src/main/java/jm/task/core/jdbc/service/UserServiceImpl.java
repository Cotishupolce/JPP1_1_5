package jm.task.core.jdbc.service;


import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserServiceImpl implements UserService {
    Connection connection = getConnection();

    public void createUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = """
                CREATE TABLE `users` (
                  `id` int NOT NULL AUTO_INCREMENT,
                  `name` varchar(45) NOT NULL,
                  `lastName` varchar(45) NOT NULL,
                  `age` int DEFAULT NULL,
                  PRIMARY KEY (`id`)
                ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3""";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, User.getId());
            preparedStatement.setString(2, User.getName());
            preparedStatement.setString(3, User.getLastName());
            preparedStatement.setByte(4, User.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, User.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "insert into users (name, lastName, age) value(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, User.getName());
            preparedStatement.setString(3, User.getLastName());
            preparedStatement.setByte(4, User.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from users where id =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, User.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        String sql = "select * from users";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "truncate users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, User.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}