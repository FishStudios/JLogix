package com.kneecapdev.jlogix.api.config;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.kneecapdev.jlogix.api.log.LogixLogger;

public class ConfigManager {
	
	private static ConfigManager instance;
	
	private ConfigManager(){
		loadConfig("main");
	}
	
	public static ConfigManager getInstance(){
		if (ConfigManager.instance == null) {
			ConfigManager.instance = new ConfigManager();
		}
		
		return ConfigManager.instance;
	}
	
	private String basePath = System.getenv("APPDATA") + "\\Logix\\configs";
	private Map<String, PropertiesConfiguration> configs = new HashMap<>();
	
	private PropertiesConfiguration createConfig(String name, String basePath){
		PropertiesConfiguration new_config = new PropertiesConfiguration();
		File file = new File(basePath + "\\" + name + ".cfg");
		
		
		new_config.setBasePath(basePath);
		new_config.setFileName(name + ".cfg");
		new_config.setAutoSave(true);		
				
		try {
			new_config.load();
			new_config.save(file);

		} catch (ConfigurationException e) {
			try {
				new_config.save(file);
				
			} catch (ConfigurationException e1) {
				LogixLogger.getLogger(instance).warn("Can´t access the config file directory for: " + name);
				
			}
		}
		return new_config;
		
	}
	
	private PropertiesConfiguration createConfig(String name){
		return createConfig(name, this.basePath);
		
	}
	
	public PropertiesConfiguration loadConfig(String name){
		PropertiesConfiguration new_config;
		if(!configs.containsKey(name)) {
			 new_config = createConfig(name);
			 configs.put(name, new_config);
			 
		} else {
			return configs.get(name);
			
		}
		return new_config;	
	}
	
	public PropertiesConfiguration loadConfig(String name, String basepath){
		PropertiesConfiguration new_config;
		if(!configs.containsKey(name)) {
			 new_config = createConfig(name, basepath);
			 configs.put(name, new_config);
			 
		} else {
			return configs.get(name);
			
		}
		return new_config;	
	}
	
	public PropertiesConfiguration getConfig(String name) {
		if(configs.containsKey(name)) return configs.get(name);
		return null;
		
	}

	public String getString(String key, String config_name) {
		if(configs.containsKey(config_name)) return configs.get(config_name).getString(key);
		return null;
	}
	
	public int getInt(String key, String config_name) {
		if(configs.containsKey(config_name)) return configs.get(config_name).getInt(key);
		return 0;
		
	}
	
	public boolean getBoolean(String key, String config_name) {
		if(configs.containsKey(config_name)) return configs.get(config_name).getBoolean(key);
		return false;
		
	}
	
	public void set(String key, String value, String config_name) {
		if(configs.containsKey(config_name))  configs.get(config_name).setProperty(key, value);
		
	}
	
	public void reloadAll(){
		for(Map.Entry<String, PropertiesConfiguration> entry : configs.entrySet()) {
		    PropertiesConfiguration config = entry.getValue();
		    config.reload();

		}
	}
	
	public void saveAll(){
		for(Map.Entry<String, PropertiesConfiguration> entry : configs.entrySet()) {
		    PropertiesConfiguration config = entry.getValue();
		    try {
				config.save();
			} catch (ConfigurationException e) {
				// TODO LOG
				
			}  
		}
	}
}