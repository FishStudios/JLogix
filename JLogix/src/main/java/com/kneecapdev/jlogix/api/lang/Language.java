package com.kneecapdev.jlogix.api.lang;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import com.kneecapdev.jlogix.api.log.LogixLogger;

public class Language {

	private String language;
	private String country;
	
	private Locale local;
	
	public Language(String language, String country) {
		this.language = language;
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public String getCountry() {
		return country;
	}
	
	public String getID() {
		return language + "_" + country;
	}

	public Locale getLocal() {
		if(local == null) local = new Locale(language, country);
		return local;
	}
	
	public ResourceBundle getResourceBundle() {
		ResourceBundle resource = null;
		try {
			URL[] urls = {LanguageManager.langDir.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			resource = ResourceBundle.getBundle("lang", this.getLocal(), loader);
			LogixLogger.info(this, "Language changed to '" + this.toString() + "'.");
		} catch (MalformedURLException e) {
			LogixLogger.error(this, e.getMessage());
		}
		return resource;
	}
	
	@Override
	public String toString(){
		return getLocal().getDisplayName(this.getLocal());
	}
	
}
