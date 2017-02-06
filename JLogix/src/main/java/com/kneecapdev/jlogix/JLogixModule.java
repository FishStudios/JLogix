package com.kneecapdev.jlogix;

import com.kneecapdev.jlogix.api.element.ElementRegistry;
import com.kneecapdev.jlogix.api.element.component.gate.GateAND;
import com.kneecapdev.jlogix.api.element.component.gate.GateNOT;
import com.kneecapdev.jlogix.api.element.component.gate.GateOR;
import com.kneecapdev.jlogix.api.module.Module;
import com.kneecapdev.jlogix.api.module.ModuleInfo;
import com.kneecapdev.jlogix.listener.LogixGUIListener;

@ModuleInfo(moduleID = "JLogix", author = "KneecapDev", version = "InDev.0.1", description = "Main JLogix module")
public class JLogixModule extends Module {
	
	public static JLogixModule instance;

	//Run code in here
	@Override
	public void onEnable() {
		instance = this;

		ElementRegistry.getInstance().register(GateAND.class);
		ElementRegistry.getInstance().register(GateNOT.class);
		ElementRegistry.getInstance().register(GateOR.class);
		
		this.registerListener(new LogixGUIListener());
	}
	
	@Override
	public void onDisable() {

	}
}
