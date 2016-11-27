package com.kneecapdav.JLogix.API.element;

import java.util.ArrayList;
import java.util.HashMap;

import com.kneecapdav.JLogix.API.module.Module;

public class ElementRegistry {

	public static ElementRegistry instance;
	
	static {
		instance = new ElementRegistry();
	}
	
	public HashMap<String, ArrayList<ElementRegistryRecord>> elementRegistry;
	
	public ElementRegistry() {
		elementRegistry = new HashMap<>();
	}
	
	public void register(Module module, ElementRegistryRecord element) {
		if(contains(module, element.clazz)) return;
		if(!elementRegistry.containsKey(module.moduleInfo.moduleID())) {
			ArrayList<ElementRegistryRecord> elements = new ArrayList<>();
			elements.add(element);
			elementRegistry.put(module.moduleInfo.moduleID(), elements);
		} else {
			elementRegistry.get(module.moduleInfo.moduleID()).add(element);
		}
		System.out.println("Registered element " + element.getInfo().elementID() + " from " + module.moduleInfo.moduleID());
	}
	
	public boolean contains(Module module, Class<? extends Element> clazz) {
		if(!elementRegistry.containsKey(module.moduleInfo.moduleID())) return false;
		else return elementRegistry.get(module.moduleInfo.moduleID()).contains(clazz);
	}
	
	public ElementRegistryRecord get(String moduleID, String elementID) {
		if(elementRegistry.containsKey(moduleID)) {
			ArrayList<ElementRegistryRecord> elements = elementRegistry.get(moduleID);
			for(ElementRegistryRecord record: elements) {
				if(record.getInfo().elementID().equalsIgnoreCase(elementID)) return record;
			}
			return null;
		} else return null;
	}
	
	public static class ElementRegistryRecord {
		
		private Class<? extends Element> clazz;
		private ElementInfo info;
		
		public ElementRegistryRecord(Class<? extends Element> clazz, ElementInfo info) {
			this.clazz = clazz;
			this.info = info;
		}

		public Class<? extends Element> getElementClass() {
			return clazz;
		}

		public ElementInfo getInfo() {
			return info;
		}
		
	}
	
}
