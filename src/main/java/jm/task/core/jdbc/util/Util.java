package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mysqljpp1.1.4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "cfqvjy1NhfQk44";
    private static Connection connection = null;
    private static SessionFactory sessionFactory;
    public static Connection getOpen() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void getClose() throws SQLException {
        connection.close();
    }
    public static SessionFactory getSession(){

        if(sessionFactory == null){
            try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER,DRIVER);
            settings.put(Environment.URL,URL);
            settings.put(Environment.USER,USERNAME);
            settings.put(Environment.PASS,PASSWORD);
            settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.SHOW_SQL,"true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration.setProperties(settings).addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}

