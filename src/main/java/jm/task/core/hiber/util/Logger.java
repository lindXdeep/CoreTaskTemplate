package jm.task.core.hiber.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Logger {

	private static String pathLogConfig = "./src/main/resources/log.properties";

	private static String pathOutLog = "./log";

	private static String defaultConfig = "handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler\n"
			+ "\n" + "java.util.logging.FileHandler.level     = INFO\n"
			+ "java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter\n"
			+ "java.util.logging.FileHandler.append    = true\n"
			+ "java.util.logging.FileHandler.pattern   = ./log/log.%u.%g.txt\n" + "\n"
			+ "java.util.logging.ConsoleHandler.level     = INFO\n"
			+ "java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter";

	private static FileOutputStream out = null;

	private Logger() {
	}

	public static void readConfiguration() {

		try (FileInputStream logConfig = new FileInputStream(checkConfig(pathLogConfig))) {

			LogManager.getLogManager().readConfiguration(logConfig);

		}
		catch (IOException e) {
			e.printStackTrace();
		}

		File logFolder = new File(pathOutLog);

		if (!new File(pathOutLog).exists()) {
			try {
				logFolder.mkdir();
			}
			catch (Exception e) {
				System.err.println("Can't create " + logFolder.getName() + " folder");
			}
		}
	}

	private static String checkConfig(final String path) throws IOException {

		File p = new File(path);

		if (!p.exists()) {
			try {

				if (new File(p.getParent()).mkdir()) {
					p.createNewFile();
				}

				out = new FileOutputStream(p.getCanonicalPath());
				out.write(defaultConfig.getBytes());

			}
			catch (Exception e) {
				System.err.println("Can't create " + p.getName() + " folder");
			}
		}
		return p.getCanonicalPath();
	}

}
