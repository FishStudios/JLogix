package com.kneecapdav.JLogix.API.events;

import com.google.common.eventbus.EventBus;

public class EventManager {

	private static EventManager eventManager;

	private EventBus eventBus;
	
	private EventManager() {
		this.eventBus = new EventBus();
	}
	
	/**
	 * Registers new listener in the EventBus
	 * @param listener
	 */
	public void registerListener(EventListener listener) {
		eventBus.register(listener);
	}
	
	/**
	 * Unregisters existent listener from the EventBus
	 * @param listener
	 */
	public void unregisterListener(EventListener listener) {
		eventBus.unregister(listener);
	}
	
	/**
	 * Fires an event to all registered listeners
	 * @param event
	 */
	public void fire(LogixEvent event) {
		eventBus.post(event);
	}
	
	public static EventManager getInstance() {
		if(eventManager == null) eventManager = new EventManager();
		
		return eventManager;
	}
	
}
