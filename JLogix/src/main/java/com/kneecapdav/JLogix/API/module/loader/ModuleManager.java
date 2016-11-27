package com.kneecapdav.JLogix.API.module.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.kneecapdav.JLogix.JLogixModule;
import com.kneecapdav.JLogix.API.module.Module;

public class ModuleManager {

	public ArrayList<Module> modules;
	public HashMap<String, Boolean> enabledModules;
	
	public ModuleLoader moduleLoader;
	
	public ModuleManager() {
		this.modules = new ArrayList<>();
		this.enabledModules = new HashMap<>();
		
		moduleLoader = new ModuleLoader(new File(System.getenv("APPDATA") + "\\Logix\\modules"));
		
		this.register(new JLogixModule());
	}
	
	public void enableAll() {
		modules.forEach((m) -> enable(m));
	}

	public boolean isEnabled(Module module) {
		return enabledModules.get(module.moduleInfo.moduleID());
	}
	
	public void enable(Module module) {
		if(!isEnabled(module)) {
			module.onEnable();
			System.out.println("Enabled module: " + module.moduleInfo.moduleID() + " version:" + module.moduleInfo.version() + "!");
		}
		enabledModules.put(module.moduleInfo.moduleID(), true);
	}
	
	public void disableAll() {
		modules.forEach((m) -> disable(m));
	}
	
	public void disable(Module module) {
		if(isEnabled(module)) {
			module.onDisable();
			System.out.println("Disabled module: " + module.moduleInfo.moduleID() + " version:" + module.moduleInfo.version() + "!");
		}
		enabledModules.put(module.moduleInfo.moduleID(), false);
	}
	
	protected void register(Module module) {
		if(getModule(module.moduleInfo.moduleID()) != null) {
			System.err.println("Unable to register new module! Theres already a registered module with this ID! (" + module.moduleInfo.moduleID() + ")");
			return;
		}
		modules.add(module);
		enabledModules.put(module.moduleInfo.moduleID(), false);
	}
	
	public void unregisterAll() {
		disableAll();
		modules.clear();
		enabledModules.clear();
	}
	
	public void unregister(Module module) {
		if(isEnabled(module)) disable(module);
		if(modules.contains(module)) {
			modules.remove(module);
			enabledModules.remove(module.moduleInfo.moduleID());
		}
	}
	
	public Module getModule(String moduleID) {
		if(modules.isEmpty()) return null;
		for(Module m: modules) if(m.moduleInfo.moduleID().equalsIgnoreCase(moduleID)) return m;
		return null;
	}
	
}
