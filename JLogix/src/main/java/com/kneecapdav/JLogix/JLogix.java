package com.kneecapdav.JLogix;

import com.kneecapdav.JLogix.API.module.loader.ModuleManager;

public class JLogix {
	
	public static ModuleManager moduleManager;

	public static void main(String[] args) {
    	try { //Run program in this try block
    		moduleManager = new ModuleManager();
    		moduleManager.moduleLoader.loadAll();
    		moduleManager.enableAll();
    		
    	} catch(Exception e) { //Log exception if the program unfortunately crashed
    		e.printStackTrace();
    	} finally { //Release all recourses
    		moduleManager.disableAll();
    	}
    }
}
