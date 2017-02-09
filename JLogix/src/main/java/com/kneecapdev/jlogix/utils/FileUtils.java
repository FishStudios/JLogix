package com.kneecapdev.jlogix.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	/**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @param targetDir Target directory as File
     * @param targetName Targetname of the File
     * @throws Exception
     */
	public static void exportResource(File toCopy, File targetDir, String targetName) throws IOException, FileNotFoundException{
		InputStream stream;
		OutputStream resStreamOut;

		stream = new FileInputStream(toCopy);

		int readBytes;
		byte[] buffer = new byte[4096];
		
		resStreamOut = new FileOutputStream(targetDir + "\\" + targetName);

		while ((readBytes = stream.read(buffer)) > 0) {
			resStreamOut.write(buffer, 0, readBytes);
		}

		stream.close();
		resStreamOut.close();

	}
	
	/**
	 * Deletes a directory all his content recursively.
	 * @param directory directory to be deleted
	 */
	public static void deleteDirectory(File directory) {
		if(directory.isDirectory()) {
			for(File f: directory.listFiles()) {
				deleteDirectory(f);
			}
			directory.delete();
		} else directory.delete();
	}
	
}
