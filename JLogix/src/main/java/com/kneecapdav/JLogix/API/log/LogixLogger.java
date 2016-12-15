package com.kneecapdav.JLogix.API.log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogixLogger {	
	private static HashMap<Class<?>, Logger> loggers = new HashMap<>();
	private static boolean debug = false;
	private static Level standard;
	
	static {
		Properties props = System.getProperties();
		props.setProperty("app.data", System.getenv("APPDATA"));
		PropertyConfigurator.configureAndWatch( "resources/log4j.properties", 60*1000 );
		standard = Logger.getRootLogger().getLevel();
	}
	
	public static Logger getLogger(Object o) {
		return getLogger(o.getClass());
		
	}
	
	public static Logger getLogger(Class<?> c) {
		Logger logger = loggers.get(c);
		
		if(logger == null) {
			loggers.put(c, Logger.getLogger(c.getClass()));
			return loggers.get(c);
			
		} else {
			return loggers.get(c);
		
		}
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
		
		Iterator<?> entries = loggers.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<?, ?> thisEntry = (Entry<?, ?>) entries.next();
			Logger logger = (Logger) thisEntry.getValue();
			  
			if(b) {
				logger.setLevel(Level.DEBUG);

			} else {
				logger.setLevel(standard);
				
			}
		}
	}
}
