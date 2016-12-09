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
		
		LogixProject project  = new LogixProject("TestProject");
		
		/*LogixCanvas canvas = project.createNewCanvas("TestCanvas1");
		
		for(int i = 0; i < 1000; i++) {
			canvas.add(new TestElement("element_" + i, i));
		}
		*/
		long timestamp = System.currentTimeMillis();
		
		//project.save(new File(System.getenv("APPDATA") + "\\Logix\\Projects\\TestProject\\"));
		project.load(new File(System.getenv("APPDATA") + "\\Logix\\Projects\\TestProject\\"));
		
		System.out.println("Took " + (System.currentTimeMillis() - timestamp) + "ms to load!");
		
		
		
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
