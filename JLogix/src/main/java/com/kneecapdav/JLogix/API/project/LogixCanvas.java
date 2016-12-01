package com.kneecapdav.JLogix.API.project;

import java.util.ArrayList;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.ElementRegistry;

public class LogixCanvas {

	public ArrayList<Element> elements;
	
	private LogixProject project;
	private String name;
	
	public LogixCanvas(LogixProject project, String name) {
		this.project = project;
		this.name = name;
		
		this.elements = new ArrayList<>();
	}
	
	/**
	 * Returns the LogixProject instance of this canvas
	 */
	public LogixProject getProject() {
		return this.project;
	}
	
	/**
	 * Returns the name of the canvas
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Renames the canvas
	 * @param New canvas name
	 */
	public void rename(String newName) {
		this.name = newName;
	}
	
	public boolean isUsingElement(String module, Class<? extends Element> clazz) {
		return ElementRegistry.instance.get(module, clazz) != null;
	}
	
	public boolean isUsingElements(String module) {
		for(Element e: elements) {
			if(isUsingElement(module, e.getClass())) return true;
		}
		return false;
	}
	
	public ArrayList<Element> getUsedElementsFromModule(String module) {
		ArrayList<Element> result = new ArrayList<>();
		for(Element e: elements) {
			if(isUsingElement(module, e.getClass())) result.add(e);
		}
		return result;
	}
	
	/**
	 * Registers a new Element object to this canvas
	 * 
	 * @param element Element object to add
	 */
	public void add(Element element) {
		elements.add(element);
	}
	
	/**
	 * Removes an Element object to this canvas
	 * 
	 * @param element Element object to remove
	 */
	public void remove(Element element) {
		elements.remove(element);
	}
	
}
