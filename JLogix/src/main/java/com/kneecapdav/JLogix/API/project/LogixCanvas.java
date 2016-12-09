package com.kneecapdav.JLogix.API.project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.ElementRegistry;
import com.kneecapdav.JLogix.API.element.ElementRegistry.ElementRegistryRecord;

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
		element.onPlace();
	}
	
	/**
	 * Removes an Element object to this canvas
	 * 
	 * @param element Element object to remove
	 */
	public void remove(Element element) {
		elements.remove(element);
		element.onDelete();
	}
	
	@SuppressWarnings("unchecked")
	public void save(File file) {
		JSONArray jsonArray = new JSONArray();
		
		for(Element e: elements) {
			JSONObject obj = e.writeMeta();
			if(obj == null) continue;
			jsonArray.add(obj);
		}
		
		try {
			
			FileWriter fw = new FileWriter(file);
			fw.write(jsonArray.toJSONString());
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(File file) {
		try {
			Object parsedObj = ProjectManager.parser.parse(new FileReader(file));
			
			JSONArray jsonArray = (JSONArray) parsedObj;
			@SuppressWarnings("unchecked")
			ArrayList<Object> content = (ArrayList<Object>) jsonArray.stream().collect(Collectors.toCollection(ArrayList::new));
			
			for(Object obj: content) {
				JSONObject jObj = (JSONObject) obj;
				String moduleID = (String) jObj.get("moduleID");
				String elementID = (String) jObj.get("elementID");
				
				ElementRegistryRecord record = ElementRegistry.instance.get(moduleID, elementID);
				if(record == null) {
					System.out.println("Skipped element! No ElementRegistryRecord found!");
					continue;
				}
				
				try {
					Element e = record.getElementClass().newInstance();
					
					e.onCreate();
					e.readMeta(jObj);
					
					this.add(e);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
}
