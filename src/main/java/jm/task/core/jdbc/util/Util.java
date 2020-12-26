package jm.task.core.jdbc.util;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Util {

    static String url = "jdbc:mysql://localhost:3306/users";
    static String user = "lindx";
    static String pass = "MySql12345!";

    public static String connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        try(Connection conn = DriverManager.getConnection(url, user, pass)){
            return "Connection to database Succsesfull";
        }
    }

    public static Logger getLogger(final String path){
    
        try (FileInputStream logConfig = new FileInputStream(checkConfig(path))) {
            
            LogManager.getLogManager().readConfiguration(logConfig);
            
        } catch (Exception e) {
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
