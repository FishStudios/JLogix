package com.kneecapdav.JLogix;

import java.io.File;

import com.kneecapdav.JLogix.API.element.ElementRegistry;
import com.kneecapdav.JLogix.API.log.LogixLogger;
import com.kneecapdav.JLogix.API.module.loader.ModuleManager;
import com.kneecapdav.JLogix.API.project.LogixCanvas;
import com.kneecapdav.JLogix.API.project.LogixProject;
import com.kneecapdav.JLogix.API.project.ProjectManager;

public class JLogix {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		try { //Run program in this try block
			ModuleManager.getInstance().moduleLoader.loadAll();
    		ModuleManager.getInstance().enableAll();
    		
    		ElementRegistry.getInstance().register(TestElement.class);
    		
    		ProjectManager.getInstance().resolveProjects();
    		
    		
    		
    		// ~~~~~~~~ Test ~~~~~~~~~
    		LogixLogger.fatal(JLogix.class, "testfatal");
    		LogixLogger.info(JLogix.class, "testinfo");
    		LogixLogger.debug(JLogix.class, "testdebug1");
    		
    		LogixLogger.setDebug(true);
    		LogixLogger.debug(JLogix.class, "testdebug2");
    		
    		boolean save = false;
    		
    		if(save && !ProjectManager.getInstance().projects.contains("TestProject")) ProjectManager.getInstance().createNewProject("TestProject");
    		
    		LogixProject project = ProjectManager.getInstance().switchProject("TestProject", false);
    		
    		if(save) {
    			
    			LogixCanvas canvas = project.createNewCanvas("TestCanvas1");
    			
    			canvas.add(new TestElement("element1" , 1));
    			
    			project.save(new File(System.getenv("APPDATA") + "\\Logix\\projects\\TestProject\\"));
    		}
    		
    		
    		// ~~~~~~~~ ~~~~ ~~~~~~~~~
    		
    		
    		
    	} catch(Exception e) { //Log exception if the program unfortunately crashed
    		LogixLogger.getLogger(JLogix.class).error("An error occured!", e);
    	} finally { //Release all recourses
    		ModuleManager.getInstance().disableAll();
    	}
    }
}
