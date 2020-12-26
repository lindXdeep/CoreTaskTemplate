package jm.task.core.jdbc.util;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Util {

    public static Logger getLogger(final String path ){
    
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
