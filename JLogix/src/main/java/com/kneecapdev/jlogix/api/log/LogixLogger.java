package com.kneecapdev.jlogix.api.log;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogixLogger {	
	
	private static HashMap<Class<?>, Logger> loggers = new HashMap<>();
	private static boolean debug = true;
	private static Level standard;
	
	static {
		Properties props = System.getProperties();
		props.setProperty("app.data", System.getenv("APPDATA"));
		PropertyConfigurator.configureAndWatch("resources/configs/Logger.cfg", 60*1000 );
		standard = Logger.getRootLogger().getLevel();
	}
	
	public static Logger getLogger(Object o) {
		return getLogger(o.getClass());
	}
	
	public static Logger getLogger(Class<?> c) {
		Logger logger = loggers.get(c);
		if(logger == null) {
			loggers.put(c, logger = Logger.getLogger(c));
			logger.setLevel(debug ? Level.DEBUG : standard);
		}
		return logger;
	}
	
	public static void info(Object o, String msg) {
		getLogger(o).info(msg);
		setDebug(debug);
	}
	
	public static void debug(Object o, String msg) {
		getLogger(o).debug(msg);
		setDebug(debug);
	}
	
	public static void warn(Object o, String msg) {
		getLogger(o).warn(msg);	
		setDebug(debug);	
	}
	
	public static void error(Object o, String msg) {
		getLogger(o).error(msg);
		setDebug(debug);
	}
	
	public static void fatal(Object o, String msg) {
		getLogger(o).fatal(msg);
		setDebug(debug);
	}
	
	public static void setDebug(boolean b) {
		debug = b;
		loggers.entrySet().forEach((e) -> e.getValue().setLevel(debug ? Level.DEBUG : standard));
	}
}
