package com.kneecapdev.jlogix.api.lang;

import java.util.Locale;

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
	
	@Override
	public String toString(){
		return getLocal().getDisplayName(this.getLocal());
		
	}
	
}
