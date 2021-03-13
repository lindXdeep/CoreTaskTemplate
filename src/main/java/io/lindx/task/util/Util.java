package io.lindx.task.util;

import org.springframework.beans.factory.annotation.Autowired;

public class Util {

  @Autowired
  private static Logger logger;

  private Util() {
  }

  static {
    logger.readConfiguration();
  }

  public static java.util.logging.Logger getLogger() {
    return java.util.logging.Logger.getLogger(Util.class.getSuperclass().getName());
  }

}