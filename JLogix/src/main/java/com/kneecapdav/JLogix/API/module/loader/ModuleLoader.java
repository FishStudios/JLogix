package com.kneecapdav.JLogix.API.module.loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.kneecapdav.JLogix.JLogix;
import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.ElementInfo;
import com.kneecapdav.JLogix.API.element.ElementRegistry;
import com.kneecapdav.JLogix.API.element.ElementRegistry.ElementRegistryRecord;
import com.kneecapdav.JLogix.API.module.Module;
import com.kneecapdav.JLogix.API.module.ModuleInfo;

public class ModuleLoader {

	private File moduleDirectory;
	
	public ModuleLoader(File moduleDirectory) {
		this.moduleDirectory = moduleDirectory;
		if(!this.moduleDirectory.exists()) this.moduleDirectory.mkdirs();
	}
	
	public void loadAll() {
		for(File f: moduleDirectory.listFiles()) {
			if(f.isDirectory()) continue;
			if(!isJarFile(f)) continue;
			
			Module module = null;
			try {
				module = load(f);
				System.out.println("Module " + f.getName() + " loaded!");
			} catch (Exception e) {
				System.err.println("An error occured while loading module " + f.getName());
				e.printStackTrace();
			}
			
			if(module != null) {
				JLogix.moduleManager.register(module);
				module.onInit();
			}
		}
	}
	
	public Module load(File file) throws IOException, InstantiationException, IllegalAccessException {
		
		Module module = null;
		
		URLClassLoader cl = null;

		try {
			cl = new URLClassLoader(new URL[]{file.toURI().toURL()}, ModuleLoader.class.getClassLoader());
			
			JarFile jar = new JarFile(file);
            Enumeration<JarEntry> enumeration = jar.entries();

            Class<?> mainClass = null;
            ModuleInfo moduleInfo = null;
            
            ArrayList<ElementRegistryRecord> elements = new ArrayList<>();
            
            try {
	            while(enumeration.hasMoreElements()) {
	                JarEntry entry = enumeration.nextElement();
	
	                //If the entry is not a class file we skip it.
	                if(entry.isDirectory() || !entry.getName().endsWith(".class")) continue;
	
	                //Split the raw class name from the file to load it.
	                String className = entry.getName().substring(0,entry.getName().length()-6);
	                className = className.replace('/', '.');
	
	                //Finally load our class.
	                try {
	                    Class<?> clazz = cl.loadClass(className);
	                    if(Module.class.isAssignableFrom(clazz)) {
	                    	if(mainClass != null) {
	                    		throw new ModuleLoadException("Theres more than one classes in this module wich extends Module!");
	                    	}
	                    	ModuleInfo[] info = clazz.getAnnotationsByType(ModuleInfo.class);
	                    	if(info.length != 0) {
	                    		mainClass = clazz;
	                    		moduleInfo = info[0];
	                    	}
	                    }
	                    
	                    if(Element.class.isAssignableFrom(clazz)) {
	                    	ElementInfo[] info = clazz.getAnnotationsByType(ElementInfo.class);
	                    	if(info.length != 0) {
	                    		@SuppressWarnings("unchecked")
								ElementRegistryRecord registryRecord = new ElementRegistryRecord((Class<? extends Element>) clazz, info[0]);
	                    		elements.add(registryRecord);
	                    	}
	                    }
	                    
	                } catch (ClassNotFoundException e) {
	                    throw new ModuleLoadException("Unable to load class " + entry.getName() + "!");
	                }
	            }
            } finally {
            	jar.close();
            }
            
            //Check if theres a class wich extends Module
            if(mainClass == null) {
            	throw new ModuleLoadException("Theres no valid main class in this Module!");
            }
            
            module = (Module) mainClass.newInstance();
            setModuleInfo(module, moduleInfo);
            
            for(ElementRegistryRecord element: elements) {
            	ElementRegistry.instance.register(module, element);
            }
		} finally {
			cl.close();
		}
		
		return module;
	}
	
	private boolean isJarFile(File file) {
		return file.getName().endsWith(".jar");
	}
	
	private void setModuleInfo(Module module, ModuleInfo info) {
		try {
			Field field = module.getClass().getField("moduleInfo");
			field.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(module, info);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
}
