package com.kneecapdav.JLogix;

public class JLogixClassLoader extends ClassLoader {
	
	public JLogixClassLoader() {
	}
	
	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		System.out.println("Trying to load " + className);
		return super.loadClass(className);
	}
	
	@Override
	public Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
		System.out.println("Trying to load " + className);
		return super.loadClass(className, resolve);
	}
	
}
