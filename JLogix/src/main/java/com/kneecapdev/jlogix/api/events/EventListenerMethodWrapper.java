package com.kneecapdev.jlogix.api.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.api.module.Module;

public class EventListenerMethodWrapper {

	public EventListener listener;
	public Module module;
	public EventHook eventHook;
	public Method method;
	public Class<?> eventType;
	
	public EventListenerMethodWrapper(EventListener listener, Module module, EventHook eventHook, Method method, Class<?> eventType) {
		this.listener = listener;
		this.module = module;
		this.eventHook = eventHook;
		this.method = method;
		this.eventType = eventType;
	}
	
	public void invoke(Object eventObj) {
		try {
			method.invoke(listener, eventObj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogixLogger.getLogger(this).error("An error occurred while invoking " + eventObj.getClass().getSimpleName() + " of module " + module.moduleInfo.moduleID() + " in listener " + listener.getClass().getSimpleName() + " in method " + method.getName(), e);
		}
	}
	
}
