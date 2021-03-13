package io.lindx.task.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.LogManager;

import org.springframework.stereotype.Service;

/**
 * Logger.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Service
public class Logger {

  private String pathLogConfig = "./src/main/resources/log.properties";

  private String pathOutLog = "./log";

  private String defaultConfig = "handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler\n"
      + "\n" + "java.util.logging.FileHandler.level = INFO\n"
      + "java.util.logging.FileHandler.formatter    = java.util.logging.SimpleFormatter\n"
      + "java.util.logging.FileHandler.append       = true\n"
      + "java.util.logging.FileHandler.pattern      = ./log/log.%u.%g.txt\n" + "\n"
      + "java.util.logging.ConsoleHandler.level     = INFO\n"
      + "java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter";

  private FileOutputStream out = null;

  private Logger() {
  }

  public void readConfiguration() {

    try (FileInputStream logConfig = new FileInputStream(checkConfig(pathLogConfig))) {

      LogManager.getLogManager().readConfiguration(logConfig);

    } catch (IOException e) {
      e.printStackTrace();
    }

    File logFolder = new File(pathOutLog);

    if (!new File(pathOutLog).exists()) {
      try {
        logFolder.mkdir();
      } catch (Exception e) {
        System.err.println("Can't create " + logFolder.getName() + " folder");
      }
    }
  }

  private String checkConfig(final String path) throws IOException {

    File p = new File(path);

    if (!p.exists()) {
      try {

        if (new File(p.getParent()).mkdir()) {
          p.createNewFile();
        }

        out = new FileOutputStream(p.getCanonicalPath());
        out.write(defaultConfig.getBytes());

      } catch (Exception e) {
        System.err.println("Can't create " + p.getName() + " folder");
      }
    }
    return p.getCanonicalPath();
  }

}
