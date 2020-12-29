package jm.task.core.jdbc.util;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Util {

    private static String db      = "Users";
    private static String address = "localhost:3306";
    private static String user    = "lindx";
    private static String pass    = "MySql12345!";
    private static String param   = "?autoReconnect=true&useSSL=false";

    private static String pathLogConfig = "./log/log.config";

    public static Connection getConnection() throws SQLException{

        String url = "jdbc:mysql://".concat(address).concat("/").concat(db);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Util.getLogger().warning(e.getMessage());
        }

        Connection connection = DriverManager.getConnection(url + param, user, pass);
                   
        Util.getLogger().fine("Connect to " + url + " successful");

        return connection;
    }

    public static Logger getLogger(){
    
        try (FileInputStream logConfig = new FileInputStream(checkConfig(pathLogConfig))) {
        
            LogManager.getLogManager().readConfiguration(logConfig);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Logger.getLogger(Util.class.getSuperclass().getName());
    }

    private static String checkConfig(final String path) throws IOException {
        
        File p = new File(path);

        String defaultConfig =  "handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler\n"+
                                "\n"+
                                "java.util.logging.FileHandler.level     = INFO\n"+
                                "java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter\n"+
                                "java.util.logging.FileHandler.append    = true\n"+
                                "java.util.logging.FileHandler.pattern   = ./log/log.%u.%g.txt\n"+
                                "\n"+
                                "java.util.logging.ConsoleHandler.level     = INFO\n"+
                                "java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter";
        if(!p.exists()){
            try {

                if(new File(p.getParent()).mkdir()){
                    p.createNewFile();
                }

                FileOutputStream out = new FileOutputStream(p.getCanonicalPath());
                
                out.write(defaultConfig.getBytes());

            } catch (Exception e) {
                System.err.println("Can't create " + p.getName() + " folder");
            }
        }
        return p.getCanonicalPath();
    }
}
