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

  private FileOutputStream out = null;

  public Logger() {
  }

  /**
   * Reading config file.
   */
  public void readConfiguration() {

    try (FileInputStream logConfig = new FileInputStream(pathLogConfig)) {

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

}
