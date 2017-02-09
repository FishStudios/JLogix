package com.kneecapdev.jlogix.api.lang;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.console.commands.CommandRegistry;
import com.kneecapdev.jlogix.utils.FileUtils;

public class LanguageManager {

	private static LanguageManager instance;
	
	private Language currentLanguage;
	private ResourceBundle resource;
	
	private File langDir = new File(System.getenv("APPDATA") + "\\Logix\\lang");
	private LanguageFileFilter fileFilter = new LanguageFileFilter();
	
	private ArrayList<Language> languages = new ArrayList<>();
	
	private String defaultLanguage = "en_US";
	
	private LanguageManager() {
		if(!langDir.exists()) langDir.mkdir();
		this.copyDefaults();
		this.load();
		
		changeLanguage(defaultLanguage);
		
		CommandRegistry.getInstance().register(new LanguageCommands());
	}
	
	public String get(String key) {
		try {
			return resource.getString(key);
		} catch (MissingResourceException e) {
			return "Missing '" + key + "'";
		}
	}
	
	public void changeLanguage(String id) {
		Language newLanguage = getLanguage(id);
		if(newLanguage == null) {
			LogixLogger.warn(this, "Unable to change language to '" + id + "', language not loaded!");
			return;
		}
		currentLanguage = newLanguage;
		ResourceBundle.clearCache();
		try {
			URL[] urls = {langDir.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			resource = ResourceBundle.getBundle("lang", currentLanguage.getLocal(), loader);
			LogixLogger.info(this, "Language changed to '" + id + "'.");
		} catch (MalformedURLException e) {
			LogixLogger.error(this, e.getMessage());
		}
		
		LanguageBindings.update();
	}
	
	public Language getLanguage(String id) {
		for(Language lang: languages) {
			if(lang.getID().equalsIgnoreCase(id)) return lang;
		}
		return null;
	}
	
	public ArrayList<Language> listLanguages() {
		return this.languages;
	}
	
	private void copyDefaults() {
		File internalDir = new File("resources/lang/");
		for(File f: internalDir.listFiles()) {
			File file = new File(langDir, f.getName());
			if(!file.exists()) {
				try {
					LogixLogger.info(this, "Creating lang file '" + f.getName() + "'");
					FileUtils.exportResource(f, langDir, f.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void resetFiles() {
		File internalDir = new File("resources/lang/");
		for(File f: internalDir.listFiles()) {
			File file = new File(langDir, f.getName());
			if(file.exists()) file.delete();
		}
		copyDefaults();
	}
	
	private void load() {
		File[] files = langDir.listFiles(fileFilter);

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i] .getName();
				Language lang = new Language(fileName.substring(5,7),fileName.substring(8,10));
				languages.add(lang);
				LogixLogger.info(this, "Language '" + lang.getID() + "' registered.");
			}			
		}
	}

	public void reload() {
		this.languages.clear();
		this.copyDefaults();
		this.load();
		
		changeLanguage(defaultLanguage);
	}
	
	public static LanguageManager getInstance() {
		if(instance == null) instance = new LanguageManager();
		return instance;
	}
	
}
