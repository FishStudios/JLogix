package com.kneecapdav.JLogix.API.config;

import java.io.File;
import java.util.Properties;

public class LogixConfig {
	private int id;
	
	private File path;
	
	private boolean xml;
	
	private String name;
	private String title;
	
	private Properties props;

	public LogixConfig(int id, File path, boolean xml, String name, String title, Properties props) {
		this.id = id;
		this.path = path;
		this.xml = xml;
		this.name = name;
		this.title = title;
		this.props = props;
		
	}
	
	/** Returns value for key as String 
	 * @param key key for the Entry in the properties
	 * @return  value (as String) of the Entry with the key 'key'
	 * */
	public String get(String key){
		return this.props.getProperty(key.toUpperCase());
		
	}
	
	/** Sets the value for the Entry with the given key
	 * @param key The key of the entry wanted to set
	 * @param value value wanted to set for the entry, casted to a String
	 * 
	 */
	public void set(String key, Object value) {
			this.props.setProperty(key.toUpperCase(), value.toString());
			
	}
	
}
