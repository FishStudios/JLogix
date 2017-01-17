package com.kneecapdev.jlogix.api.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.kneecapdev.jlogix.api.events.EventManager;
import com.kneecapdev.jlogix.api.events.project.LogixCanvasCreateEvent;
import com.kneecapdev.jlogix.api.events.project.LogixCanvasDeleteEvent;
import com.kneecapdev.jlogix.api.events.project.LogixCanvasSwitchEvent;

public class LogixProject {

	public HashMap<String, LogixCanvas> canvas;

	private String projectName;
	
	public ProjectElementLibrary elementLibrary;
	
	public LogixCanvas currentCanvas;
	
	protected PropertiesConfiguration config;
	
	public LogixProject(String name) {
		canvas = new HashMap<>();
		
		this.projectName = name;
	}
	
	public String getName() {
		return this.projectName;
	}
	
	/**
	 * Switch canvas view to the given canvas.
	 * @param canvas canvas to be displayed
	 */
	public void switchCanvas(LogixCanvas canvas) {
		LogixCanvasSwitchEvent e = new LogixCanvasSwitchEvent(this, currentCanvas, canvas);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return;
		
		this.currentCanvas = canvas;
	}
	
	/**
	 * Creates and registers new LogixCanvas instance for this project.
	 * @param name of the new canvas
	 * @return New LogixCanvas instance. Returns null if the LogixCanvasCreateEvent got canceled.
	 */
	public LogixCanvas createNewCanvas(String name) {
		LogixCanvas c = new LogixCanvas(this, name);
		
		LogixCanvasCreateEvent e = new LogixCanvasCreateEvent(this, c);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return null;
		
		canvas.put(name.toLowerCase(), c);
		return c;
	}
	
	public void deleteCanvas(String name) {
		if(name.equalsIgnoreCase(currentCanvas.getName())) switchCanvas(null);
		
		LogixCanvas c = this.getCanvas(name);
		if(c == null) return;
		
		LogixCanvasDeleteEvent e = new LogixCanvasDeleteEvent(this, c);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return;
		
		this.canvas.remove(name.toLowerCase());
	}
	
	/**
	 * Returns existing LogixCanvas instance from this project.
	 * If there is no registered canvas with this name it will return null.
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
	 * Returns an ArrayList<LogixCanvas> of all canvas instances which are using an element of the given module
	 * @param moduleID id of the module
	 * @return ArrayList<LogixCanvas> of all canvas instances which are using an element of the given module
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
	public void save() {
		File file = new File(ProjectManager.getInstance().projectDir, "\\" + this.getName());
		if(!file.exists()) file.mkdirs();
		//Iterate all canvas objects and save it
		for(LogixCanvas c: canvas.values()) {
			File f = new File(file, "\\" + c.getName() + ".canvas");
			if(!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			c.save(f);
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
	public void load() {
		File file = new File(ProjectManager.getInstance().projectDir, "\\" + this.getName());
		for(File f: file.listFiles()) {
			if(f.isDirectory()) continue;
			if(!f.getName().endsWith(".canvas")) continue;
			LogixCanvas c = new LogixCanvas(this, f.getName().substring(0, f.getName().length() - 7));
			c.load(f);
			
			canvas.put(c.getName().toLowerCase(), c);
		}
		
		this.elementLibrary = new ProjectElementLibrary(this);
	}
	
	public void unload() {
		switchCanvas(null);
		this.canvas.clear();
		this.currentCanvas = null;
		this.elementLibrary = null;
	}
	
}
