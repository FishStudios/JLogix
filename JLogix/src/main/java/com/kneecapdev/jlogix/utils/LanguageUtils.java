package com.kneecapdev.jlogix.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import com.kneecapdev.jlogix.api.log.LogixLogger;

public class LanguageUtils {

	public static void checkFile(File internal, File external) {
		HashMap<String, String> intContent, extContent;
		
		intContent = getLanguageEntries(internal);
		extContent = getLanguageEntries(external);
		
		int missing = intContent.size() - extContent.size();
		intContent.entrySet().forEach((e) -> {
			if(!extContent.containsKey(e.getKey())) {
				extContent.put(e.getKey(), e.getValue());
			}
		});
		
		if(missing == 0) return;
		
		try(PrintWriter pw = new PrintWriter(external.getAbsolutePath(), "ISO-8859-1")) {
			extContent.entrySet().forEach((e) -> {
				pw.println(e.getKey() + "=" + e.getValue());
			});
		} catch (IOException e) {
			LogixLogger.getLogger(LanguageUtils.class).error(e);
		}
		
		LogixLogger.getLogger(LanguageUtils.class).info("Added " + missing + " language entries to " + internal.getName());
	}
	
	public static HashMap<String, String> getLanguageEntries(File file) {
		HashMap<String, String> content = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));) {
			br.lines().forEach((line) -> {
				if(line.contains("=")) {
					String[] entry = line.split("=");
					content.put(entry[0], entry[1]);
				}
			});
		} catch (IOException e) {
			LogixLogger.getLogger(LanguageUtils.class).error(e);
		}
		return content;
	}
	
}
