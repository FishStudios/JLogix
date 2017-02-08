package com.kneecapdev.jlogix.api.lang;

import java.io.File;
import java.util.ArrayList;

public class LanguageManager {

	private static LanguageManager instance;
	
	private Language currentLanguage;
	
	private File langDir = new File(System.getenv("APPDATA") + "\\Logix\\lang");
	
	private ArrayList<Language> languages = new ArrayList<>();
	
	private LanguageManager() {
		this.load();
	}
	
	private void load() {
		
	}
	
	public static LanguageManager getInstance() {
		if(instance == null) instance = new LanguageManager();
		return instance;
	}
	
}
