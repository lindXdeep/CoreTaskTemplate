package jm.task.core.hiber.util.properties;

import jm.task.core.hiber.util.Util;

public class JDBCProperty {
    private static String db      = "users";
    private static String address = "localhost:3306";
    private static String user    = "lindx";
    private static String pass    = "MySql12345!";
    private static String param   = "?autoReconnect=true&useSSL=false";

    private static String mysqlDriver  = "com.mysql.jdbc.Driver";

    public static Class<?> getMySqlDriver(){
        try {
            return Class.forName(mysqlDriver);
        } catch (ClassNotFoundException e) {
            Util.getLogger().warning(e.getMessage());
        }
        throw new RuntimeException();
    }

    public static String getURL(){
        return "jdbc:mysql://".concat(address).concat("/").concat(db).concat(param);
    }

    public static String getUser() {
        return user;
    }

    public static String getPass() {
        return pass;
    }
}
