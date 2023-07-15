package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private static final String CREATE_USERS_TABLE_SQL = """
            CREATE TABLE `user` (
              `id` int AUTO_INCREMENT,
              `name` varchar(45) NOT NULL,
              `lastName` varchar(45) NOT NULL,
              `age` int DEFAULT NULL,
              PRIMARY KEY (`id`)
            ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3""";
    private static final String DROP_USERS_TABLE_SQL = "DROP TABLE IF EXISTS user";
    private static final String SAVE_USER_SQL = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
    private static final String REMOVAE_USER_BY_ID_SQL = "DELETE FROM user WHERE id = ?";

    private static final String GET_ALL_USERS_SQL = "SELECT id, name, lastName, age FROM user";
    private static final String CLEAN_USERS_TABLE_SQL = "TRUNCATE TABLE user";


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();
            session.createNativeQuery(CREATE_USERS_TABLE_SQL).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();
            session.createNativeQuery(DROP_USERS_TABLE_SQL).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();

            session.createNativeQuery(SAVE_USER_SQL)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();

            session.createNativeQuery(REMOVAE_USER_BY_ID_SQL + id)
                    .executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();

            users = session.createNativeQuery(GET_ALL_USERS_SQL).addEntity(User.class).list();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();

            session.createNativeQuery(CLEAN_USERS_TABLE_SQL).executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}