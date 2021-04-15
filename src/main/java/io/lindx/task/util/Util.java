package io.lindx.task.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Util class for logger.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-04-15
 */
public final class Util {

  @Autowired
  private Logger logger;

  private Util() {
    logger.readConfiguration();
  }

  public static java.util.logging.Logger getLogger() {
    return java.util.logging.Logger.getLogger(Util.class.getSuperclass().getName());
  }

}
