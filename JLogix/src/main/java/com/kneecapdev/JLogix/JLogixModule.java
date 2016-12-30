package com.kneecapdev.JLogix;

import com.kneecapdev.JLogix.API.config.ConfigManager;
import com.kneecapdev.JLogix.API.module.Module;
import com.kneecapdev.JLogix.API.module.ModuleInfo;
import com.kneecapdev.JLogix.listener.LogixGUIListener;

@ModuleInfo(moduleID = "JLogix", author = "KneecapDev", version = "InDev.0.1", description = "Main JLogix module")
public class JLogixModule extends Module {
	
	public static JLogixModule instance;

	//Run code in here
	@Override
	public void onEnable() {
		instance = this;
		
		this.registerListener(new LogixGUIListener());
		ConfigManager.init();
		
	}
	
	@Override
	public void onDisable() {

	}
}
