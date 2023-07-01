package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем –" + User.getName() + "добавлен в базу данных");
        userServiceImpl.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем –" + User.getName() + "добавлен в базу данных");
        userServiceImpl.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем –" + User.getName() + "добавлен в базу данных");
        userServiceImpl.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем –" + User.getName() + "добавлен в базу данных");
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();

    }

}
