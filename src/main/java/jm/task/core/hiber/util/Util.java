package jm.task.core.hiber.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jm.task.core.hiber.model.User;
import jm.task.core.hiber.util.properties.HibernateProperty;
import jm.task.core.hiber.util.properties.JDBCProperty;


public class Util {
    
    private static SessionFactory sessionFactory = null;
    private static Configuration configuration = null;

    static{
        Logger.readConfiguration();

        JDBCProperty.getMySqlDriver();
        
        configuration = HibernateProperty.getConfiguration();

        //TODO: config for entity-classes there
        configuration.addAnnotatedClass(User.class);

        sessionFactory = buildSessionFactory();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBCProperty.getURL(), JDBCProperty.getUser(), JDBCProperty.getPass());
    }

    public static java.util.logging.Logger getLogger(){
        return java.util.logging.Logger.getLogger(Util.class.getSuperclass().getName());
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