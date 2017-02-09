package com.kneecapdev.jlogix.api.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;

import com.kneecapdev.jlogix.api.log.LogixLogger;

import javafx.application.Platform;

public class LanguageBindings {

	private static HashMap<String, WrappedObjectBinding> bindings = new HashMap<>();
	
	public static void bind(String key, Object obj) {
		WrappedObjectBinding binding = new WrappedObjectBinding(obj);
		if(!binding.isValidObject()) {
			LogixLogger.getLogger(LanguageBindings.class).warn("Unable to bind object of type '" + obj.getClass().getSimpleName() + "', method does not contain a 'setText' method!");
			return;
		}
		binding.update(LanguageManager.getInstance().get(key));
		bindings.put(key, binding);
	}
	
	public static void remove(Object obj) {
		String key = null;
		for(Entry<String, WrappedObjectBinding> e: bindings.entrySet()) {
			if(e.getValue().obj == obj) {
				key = e.getKey();
				break;
			}
		}
		if(key != null) bindings.remove(key);
	}
	
	public static void remove(String key) {
		bindings.remove(key);
	}
	
	public static void update() {
		bindings.entrySet().forEach((e) -> Platform.runLater(() -> e.getValue().update(LanguageManager.getInstance().get(e.getKey()))));
	}
	
	private static class WrappedObjectBinding {
		
		private Object obj;
		private Method method;
		
		public WrappedObjectBinding(Object obj) {
			this.obj = obj;
		}
		
		public boolean isValidObject() {
			for(Method m: obj.getClass().getMethods()) {
				if(m.getName().equalsIgnoreCase("setText")) {
					this.method = m;
					return true;
				}
			}
			return false;
		}
		
		public void update(String arg) {
			try {
				method.invoke(obj, arg);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
