package com.kneecapdev.jlogix.api.module.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.kneecapdev.jlogix.JLogixModule;
import com.kneecapdev.jlogix.api.events.EventManager;
import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.api.module.Module;
import com.kneecapdev.jlogix.api.module.ModuleInfo;
import com.kneecapdev.jlogix.utils.ReflectionUtils;

public class ModuleManager {

	private static ModuleManager instance;
	
	public ArrayList<Module> modules;
	public HashMap<String, Boolean> enabledModules;
	
	public ModuleLoader moduleLoader;
	
	private ModuleManager() {
		this.modules = new ArrayList<>();
		this.enabledModules = new HashMap<>();

		moduleLoader = new ModuleLoader(new File(System.getenv("APPDATA") + "\\Logix\\modules"));
		
		JLogixModule mainModule = new JLogixModule();
		
		ModuleInfo[] info = JLogixModule.class.getAnnotationsByType(ModuleInfo.class);
		ReflectionUtils.setFinalField(ReflectionUtils.getField(Module.class, "moduleInfo"), mainModule, info[0]);
		
		this.register(mainModule);
	}
	
	public void enableAll() {
		modules.forEach(this::enable);
	}

	public boolean isEnabled(Module module) {
		return enabledModules.get(module.moduleInfo.moduleID());
	}
	
	public void enable(Module module) {
		if(!isEnabled(module)) {
			module.onEnable();
			LogixLogger.info(this, "Enabled module: " + module.moduleInfo.moduleID() + " version: " + module.moduleInfo.version());
		}
		enabledModules.put(module.moduleInfo.moduleID(), true);
	}
	
	public void disableAll() {
		modules.forEach(this::disable);
	}
	
	public void disable(Module module) {
		if(isEnabled(module)) {
			module.onDisable();
			module.getRegisteredListeners().forEach((listener) -> EventManager.getInstance().unregisterListener(listener));
			LogixLogger.info(this, "Disabled module: " + module.moduleInfo.moduleID());
		}
		enabledModules.put(module.moduleInfo.moduleID(), false);
	}
	
	protected void register(Module module) {
		if(getModule(module.moduleInfo.moduleID()) != null) {
			LogixLogger.warn(this, "Unable to register new module! There is already a registered module with this ID! (" + module.moduleInfo.moduleID() + ")");
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
	
	public static ModuleManager getInstance() {
		if(instance == null) instance = new ModuleManager();
		
		return instance;
	}
	
}
