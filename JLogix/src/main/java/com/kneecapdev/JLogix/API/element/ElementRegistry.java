package com.kneecapdev.JLogix.API.element;

import java.util.ArrayList;
import java.util.HashMap;

import com.kneecapdev.JLogix.API.log.LogixLogger;
import com.kneecapdev.JLogix.API.module.Module;
import com.kneecapdev.JLogix.API.module.loader.ModuleManager;

public class ElementRegistry {

	private static ElementRegistry instance;
	
	public HashMap<String, ArrayList<ElementRegistryRecord>> elementRegistry;
	
	private ElementRegistry() {
		elementRegistry = new HashMap<>();
	}
	
	public void register(Class<? extends Element> clazz) {
		ElementInfo[] info = clazz.getAnnotationsByType(ElementInfo.class);
		if(info.length > 0) {
			ElementRegistryRecord err = new ElementRegistryRecord(clazz, info[0]);
		
			Module module = ModuleManager.getInstance().getModule(err.info.moduleID());
			if(module == null) {
				LogixLogger.error(this,"Unable to register Element " + clazz.getSimpleName() + " module " + err.info.moduleID() + " not found!");
				return;
			}
			
			this.register(module, err);
		} else {
			LogixLogger.error(this,"Unable to register Element " + clazz.getSimpleName() + " no ElementInfo annotation found!");
			
		}
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
		LogixLogger.info(this,"Registered element " + element.getInfo().elementID() + " from " + module.moduleInfo.moduleID());
	}
	
	public boolean contains(Module module, Class<? extends Element> clazz) {
		return get(module.moduleInfo.moduleID(), clazz) != null;
	}
	
	public boolean contains(String moduleID, Class<? extends Element> clazz) {
		return get(moduleID, clazz) != null;
	}
	
	public boolean contains(Module module, String elementID) {
		return get(module.moduleInfo.moduleID(), elementID) != null;
	}
	
	public boolean contains(String moduleID, String elementID) {
		return get(moduleID, elementID) != null;
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
	
	public ElementRegistryRecord get(String moduleID, Class<? extends Element> clazz) {
		if(elementRegistry.containsKey(moduleID)) {
			ArrayList<ElementRegistryRecord> elements = elementRegistry.get(moduleID);
			for(ElementRegistryRecord record: elements) {
				if(record.clazz == clazz) return record;
			}
			return null;
		} else return null;
	}
	
	public String getModule(Class<? extends Element> clazz) {
		for(String module: this.elementRegistry.keySet()) {
			ArrayList<ElementRegistryRecord> elements = this.elementRegistry.get(module);
			for(ElementRegistryRecord record: elements) {
				if(record.clazz == clazz) return module;
			}
		}
		return null;
	}
	
	public static ElementRegistry getInstance() {
		if(instance == null) instance = new ElementRegistry();
		
		return instance;
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
