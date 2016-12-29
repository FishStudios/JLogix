package com.kneecapdav.JLogix.API.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigManager {
	private static String basePath = System.getenv("APPDATA") + "//Logix//configs";
	public static PropertiesConfiguration config = new PropertiesConfiguration();
	
	public static void init(){			
		config.setBasePath(basePath);
		config.setFileName("Logix.cfg");
		config.setAutoSave(true);
		
		try {
			config.load();
		} catch (ConfigurationException e) {
			//TODO log
			e.printStackTrace();
		}
	}
	
	public static void reload(){
		try {
			config.refresh();
		} catch (ConfigurationException e) {
			// TODO Log

		}
	}
}