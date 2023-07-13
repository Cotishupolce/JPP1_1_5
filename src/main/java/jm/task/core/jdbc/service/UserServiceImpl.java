package jm.task.core.jdbc.service;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;


import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoHibernateImpl();

    public UserServiceImpl() {
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        userList.forEach(System.out::println);
        return userList;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}