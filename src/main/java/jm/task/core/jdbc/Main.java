package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDaoJDBCImpl.getInstance();
        userDao.saveUser("Name1","lastName1",(byte) 3);
        System.out.println("User с именем –"+ User.getName() + " добавлен в базу данных");

    }

}
