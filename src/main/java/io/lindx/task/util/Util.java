package io.lindx.task.util;

import org.springframework.beans.factory.annotation.Autowired;

public class Util {

	@Autowired
	private Logger logger;

	private Util() {
		logger.readConfiguration();
	}

	public static java.util.logging.Logger getLogger() {
		return java.util.logging.Logger.getLogger(Util.class.getSuperclass().getName());
	}

}