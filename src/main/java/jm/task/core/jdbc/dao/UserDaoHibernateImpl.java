package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSession();
    public UserDaoHibernateImpl() {

    }

    private static final String CREATE_USERS_TABDLE_SQL = """
            CREATE TABLE `user` (
              `id` int AUTO_INCREMENT,
              `name` varchar(45) NOT NULL,
              `lastName` varchar(45) NOT NULL,
              `age` int DEFAULT NULL,
              PRIMARY KEY (`id`)
            ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3""";
    private static final String DROP_USERS_TABLE_SQL = "DROP TABLE IF EXISTS user";
    private static final String GET_ALL_USERS_SQL = "SELECT id, name, lastName, age FROM user";
    private static final String CLEAN_USERS_TABLE_SQL = "TRUNCATE TABLE user";


    @Override
    public void createUsersTable() {
        var session = sessionFactory.getCurrentSession();
        try (session) {
            var transaction = session.beginTransaction();
            session.createNativeQuery(CREATE_USERS_TABDLE_SQL).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        var session = sessionFactory.getCurrentSession();
        try (session) {
            var transaction = session.beginTransaction();
            session.createNativeQuery(DROP_USERS_TABLE_SQL).addEntity(User.class).executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        var session = sessionFactory.getCurrentSession();
        try (session) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        var session = sessionFactory.getCurrentSession();
        try (session) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        var session = sessionFactory.getCurrentSession();
        List<User>  userList = new ArrayList<>();
        try (session) {
            session.beginTransaction();
            userList = session.createQuery(GET_ALL_USERS_SQL).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        var session = sessionFactory.getCurrentSession();
        try (session) {
            session.beginTransaction();
            session. createQuery(CLEAN_USERS_TABLE_SQL).executeUpdate();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}