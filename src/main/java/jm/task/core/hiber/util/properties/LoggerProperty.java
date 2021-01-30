package jm.task.core.hiber.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class LoggerProperty {

    private static String pathLogConfig = "./log/log.config";

    public static void readConfiguration(){
        try (FileInputStream logConfig = new FileInputStream(checkConfig(pathLogConfig))) {
        
            LogManager.getLogManager().readConfiguration(logConfig);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
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
