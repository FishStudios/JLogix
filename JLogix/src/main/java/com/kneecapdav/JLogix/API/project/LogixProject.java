package com.kneecapdav.JLogix.API.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class LogixProject {

	public HashMap<String, LogixCanvas> canvas;
	
	public LogixProject(String name) {
		canvas = new HashMap<>();
	}
	
	/**
	 * Creates and registers new LogixCanvas instance for this project.
	 * @param name of the new canvas
	 * @return New LogixCanvas instance
	 */
	public LogixCanvas createNewCanvas(String name) {
		LogixCanvas c = new LogixCanvas(this, name);
		canvas.put(name.toLowerCase(), c);
		return c;
	}
	
	/**
	 * Returns existing LogixCanvas instance from this project.
	 * If theres no registered canvas with this name it will return null.
	 * @param name of the canvas
	 * @return Canvas object
	 */
	public LogixCanvas getCanvas(String name) {
		return canvas.get(name.toLowerCase());
	}
	
	/**
	 * Returns true or false if the project contains an Element of the given module
	 * @param moduleID ID of the module to check
	 * @return True or false if the project contains an Element of the given module
	 */
	public boolean isUsingModule(String moduleID) {
		for(LogixCanvas c: canvas.values()) {
			if(c.isUsingElements(moduleID)) return true;
		}
		return false;
	}
	
	/**
	 * Returns an ArrayList<LogixCanvas> of all canvas instances which using an element of the given module
	 * @param moduleID
	 * @return
	 */
	public ArrayList<LogixCanvas> getCanvasWithElementsOfModule(String moduleID) {
		ArrayList<LogixCanvas> result = new ArrayList<>();
		for(LogixCanvas c: canvas.values()) {
			if(c.isUsingElements(moduleID)) result.add(c);
		}
		return result;
	}
	
	/**
	 * Saves and writes the project to the given file.
	 * @param file Project path
	 */
	public void save(File file) {
		//Iterate all canvas objects and save it
		for(LogixCanvas c: canvas.values()) {
			c.save(new File(file, "\\" + c.getName() + ".canvas"));
		}
		/*TODO: Save all project data
		 * 
		 * name
		 * settings
		 * etc.
		 * 
		*/
	}
	
	/**
	 * Loads the project from the given path.
	 * @param file Project path
	 */
	public void load(File file) {
		for(File f: file.listFiles()) {
			if(f.isDirectory()) continue;
			if(!f.getName().endsWith(".canvas")) continue;
			LogixCanvas c = new LogixCanvas(this, f.getName().substring(0, f.getName().length() - 7));
			c.load(f);
			
			canvas.put(c.getName().toLowerCase(), c);
		}
	}
	
}
