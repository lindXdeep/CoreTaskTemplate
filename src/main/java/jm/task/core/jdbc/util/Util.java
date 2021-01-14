package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import jm.task.core.jdbc.util.properties.JDBCProperty;
import jm.task.core.jdbc.util.properties.LoggerProperty;

public class Util {

    public static Connection getConnection() throws SQLException {

        JDBCProperty.getMySqlDriver();
        return DriverManager.getConnection(JDBCProperty.getURL(), JDBCProperty.getUser(), JDBCProperty.getPass());
    }

    public static Logger getLogger(){
    
        LoggerProperty.readConfiguration();
        return Logger.getLogger(Util.class.getSuperclass().getName());
    }
}