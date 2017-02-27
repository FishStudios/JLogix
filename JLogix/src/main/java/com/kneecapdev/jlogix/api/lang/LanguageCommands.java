package com.kneecapdev.jlogix.api.lang;

import java.util.ArrayList;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.console.commands.CommandInfo;
import com.kneecapdev.jlogix.console.commands.arguments.StringArgument;

public class LanguageCommands {

	@CommandInfo(cmd="lang_change", usage="lang_change <language>")
	public void langSwitch(StringArgument lang) {
		LanguageManager.getInstance().changeLanguage(lang.getValue());
	}
	
	@CommandInfo(cmd="lang_list", usage="lang_list")
	public void langList() {
		StringBuilder sb = new StringBuilder();
		sb.append("Registered languages: \n");
		ArrayList<Language> langs = LanguageManager.getInstance().listLanguages();
		for(int i = 0; i < langs.size(); i++) {
			Language lang = langs.get(i);
			if(i == (langs.size() - 1)) sb.append("\t > " + lang.getID());
			else sb.append("\t > " + lang.getID() + "\n");
		}
		LogixLogger.info(this, sb.toString());
	}
	
	@CommandInfo(cmd="lang_reload", usage="lang_reload")
	public void langReload() {
		LanguageManager.getInstance().reload();
	}
	
	@CommandInfo(cmd="lang_current", usage="lang_current")
	public void langCurrent(){
		LogixLogger.info(this, LanguageManager.getInstance().getCurrentLanguage().getID());
	}
}
