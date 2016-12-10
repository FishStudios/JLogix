package com.kneecapdav.JLogix;

import java.io.File;

import com.kneecapdav.JLogix.API.element.ElementInfo;
import com.kneecapdav.JLogix.API.element.ElementRegistry;
import com.kneecapdav.JLogix.API.element.ElementRegistry.ElementRegistryRecord;
import com.kneecapdav.JLogix.API.module.Module;
import com.kneecapdav.JLogix.API.module.ModuleInfo;
import com.kneecapdav.JLogix.API.project.LogixCanvas;
import com.kneecapdav.JLogix.API.project.LogixProject;

@ModuleInfo(moduleID = "JLogix", author = "KneecapDev", version = "InDev.0.1", description = "Main JLogix module")
public class JLogixModule extends Module {

	public static JLogixModule instance;

	//Run code in here
	@Override
	public void onEnable() {
		instance = this;
		
		ElementInfo[] info = TestElement.class.getAnnotationsByType(ElementInfo.class);
		ElementRegistryRecord err = new ElementRegistryRecord(TestElement.class, info[0]);
		ElementRegistry.instance.register(this, err);
		
		boolean save = false;
		
		LogixProject project  = new LogixProject("TestProject");
		
		if(save) {
			LogixCanvas canvas = project.createNewCanvas("TestCanvas1");
			
			canvas.add(new TestElement("element1" , 1));
			
			project.save(new File(System.getenv("APPDATA") + "\\Logix\\Projects\\TestProject\\"));
		} else {
			project.load(new File(System.getenv("APPDATA") + "\\Logix\\Projects\\TestProject\\"));
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
