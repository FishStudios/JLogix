package com.kneecapdev.JLogix.API.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.kneecapdev.JLogix.JLogixModule;
import com.kneecapdev.JLogix.API.log.LogixLogger;
import com.kneecapdev.JLogix.API.module.Module;

public class EventListenerRegistry {

	private static EventListenerRegistry instance;
	
	private HashMap<Class<?>, ArrayList<EventListenerMethodWrapper>> registeredListeners;
	
	private EventListenerComparator comparator;
	
	private EventListenerRegistry() {
		this.registeredListeners = new HashMap<>();
		
		this.comparator = new EventListenerComparator();
	}
	
	public void fireEvent(Object eventObj) {
		ArrayList<EventListenerMethodWrapper> methods = registeredListeners.get(eventObj.getClass());
		if(methods == null) return;
		
		methods.forEach((method) -> method.invoke(eventObj));
	}
	
	public void register(Module module, EventListener listener) {
		for(EventListenerMethodWrapper wrappedMethod: this.resolveListenerMethods(module, listener)) {
			ArrayList<EventListenerMethodWrapper> methods = registeredListeners.get(wrappedMethod.eventType);
			if(methods == null) methods = new ArrayList<>();
			methods.add(wrappedMethod);
			
			registeredListeners.put(wrappedMethod.eventType, methods);
		}
		
		registeredListeners.values().forEach((methods) -> methods.sort(comparator));
	}
	
	public void unregister(EventListener listener) {
		registeredListeners.values().forEach((methods) -> methods.removeIf((method) -> method.listener == listener));
	}
	
	private ArrayList<EventListenerMethodWrapper> resolveListenerMethods(Module module, EventListener listener) {
		Class<?> eventClass = listener.getClass();
		
		ArrayList<EventListenerMethodWrapper> result = new ArrayList<>();
		
		//Iterate all possible methods
		for(Method method: eventClass.getMethods()) {
			//Get all EventHook annotations of this method
			EventHook[] eventHooks = method.getAnnotationsByType(EventHook.class);
			//Check if the method contains any EventHook annotations
			if(eventHooks.length == 0) continue;
			
			Class<?>[] parameters = method.getParameterTypes();
			
			//Check if the method only contains one parameter
			if(parameters.length != 1) {
				LogixLogger.warn(this, "Unable to register listener of method: " + method.getName() + " in class: " + eventClass.getSimpleName() + ". Method contains more or less than one method parameters!");
				continue;
			}
			
			//Type of the event this method is listening
			Class<?> eventType = parameters[0];
			
			//Create new EventListenerMethodWrapper instance
			EventListenerMethodWrapper wrappedMethod = new EventListenerMethodWrapper(listener, module, eventHooks[0], method, eventType);
			
			result.add(wrappedMethod);
		}
		
		return result;
	}
	
	public static EventListenerRegistry getInstance() {
		if(instance == null) instance = new EventListenerRegistry();
		
		return instance;
	}
	
	public class EventListenerComparator implements Comparator<EventListenerMethodWrapper> {

		@Override
		public int compare(EventListenerMethodWrapper o1, EventListenerMethodWrapper o2) {
			if(o1.module == JLogixModule.instance) return 1;
			
			return o2.eventHook.priority().weight - o1.eventHook.priority().weight;
		}
		
	}
	
}
