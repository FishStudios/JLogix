package com.kneecapdev.JLogix.API.events;

import com.kneecapdev.JLogix.API.module.Module;

public class EventManager {

	private static EventManager eventManager;

	/**
	 * Registers new listener in the EventBus
	 * @param module module which the listener belongs to
	 * @param listener listener to be registered
	 */
	public void registerListener(Module module, EventListener listener) {
		EventListenerRegistry.getInstance().register(module, listener);
	}
	
	/**
	 * Unregisters existent listener from the EventBus
	 * @param listener listener to be unregistered
	 */
	public void unregisterListener(EventListener listener) {
		EventListenerRegistry.getInstance().unregister(listener);
	}
	
	/**
	 * Fires an event to all registered listeners
	 * @param event event to be fired
	 */
	public void fire(LogixEvent event) {
		EventListenerRegistry.getInstance().fireEvent(event);
	}
	
	public static EventManager getInstance() {
		if(eventManager == null) eventManager = new EventManager();
		
		return eventManager;
	}
	
}
