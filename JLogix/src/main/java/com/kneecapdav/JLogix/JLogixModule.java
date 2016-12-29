package com.kneecapdav.JLogix;

import com.kneecapdav.JLogix.API.config.ConfigManager;
import com.kneecapdav.JLogix.API.module.Module;
import com.kneecapdav.JLogix.API.module.ModuleInfo;
import com.kneecapdav.JLogix.listener.LogixGUIListener;

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
