package com.kneecapdev.JLogix.API.project;

import java.util.ArrayList;
import java.util.List;

import com.kneecapdev.JLogix.JLogixModule;
import com.kneecapdev.JLogix.API.element.ElementRegistry;
import com.kneecapdev.JLogix.API.element.ElementRegistry.ElementRegistryRecord;
import com.kneecapdev.JLogix.API.module.Module;

public class ProjectElementLibrary {

	public ArrayList<String> usedModules;
	public ArrayList<ElementRegistryRecord> elements;
	
	public ProjectElementLibrary(LogixProject project) {
		usedModules = new ArrayList<>();
		elements = new ArrayList<>();
		
		loadLibrary(project);
	}
	
	public void addModule(String moduleID) {
		usedModules.add(moduleID);
		elements.addAll(ElementRegistry.getInstance().getAll(moduleID));
	}
	
	public void addModule(Module module) {
		this.addModule(module.moduleInfo.moduleID());
	}

	private void loadLibrary(LogixProject project) {
		if(project.config.containsKey("modules")) {
			List<Object> modules = project.config.getList("modules");
			
			modules.forEach((m) -> {
				String moduleID = (String) m;
				usedModules.add(moduleID);
				elements.addAll(ElementRegistry.getInstance().getAll(moduleID));
			});
		}
		
		elements.addAll(ElementRegistry.getInstance().getAll(JLogixModule.instance.moduleInfo.moduleID()));
	}
	
}
