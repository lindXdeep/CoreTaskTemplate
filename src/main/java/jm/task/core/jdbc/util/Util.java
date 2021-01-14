package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.properties.HibernateProperty;
import jm.task.core.jdbc.util.properties.JDBCProperty;
import jm.task.core.jdbc.util.properties.LoggerProperty;

public class Util {

    private static SessionFactory sessionFactory = null;
    private static Configuration configuration = null;

    static{
        JDBCProperty.getMySqlDriver();
        LoggerProperty.readConfiguration();
        configuration = HibernateProperty.getConfiguration();

        configuration.addAnnotatedClass(User.class);
        sessionFactory = buildSessionFactory();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBCProperty.getURL(), JDBCProperty.getUser(), JDBCProperty.getPass());
    }

    public static Logger getLogger(){
        return Logger.getLogger(Util.class.getSuperclass().getName());
    }

    private static SessionFactory buildSessionFactory() {

        try {
            return configuration.buildSessionFactory();
        }catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}