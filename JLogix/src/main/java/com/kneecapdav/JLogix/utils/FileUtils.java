package com.kneecapdav.JLogix.utils;

import java.io.File;

public class FileUtils {

	/**
	 * Deletes a directory all his content recursively.
	 * @param directory directory to be deleted
	 */
	public static void deleteDirectory(File directory) {
		if(directory.isDirectory()) {
			for(File f: directory.listFiles()) {
				deleteDirectory(f);
			}
		} else directory.delete();
	}
	
}
