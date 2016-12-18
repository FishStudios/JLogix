package com.kneecapdav.JLogix.API.events;

import com.kneecapdav.JLogix.API.module.Module;

public class EventManager {

	private static EventManager eventManager;

	/**
	 * Registers new listener in the EventBus
	 * @param listener
	 */
	public void registerListener(Module module, EventListener listener) {
		EventListenerRegistry.getInstance().register(module, listener);
	}
	
	/**
	 * Unregisters existent listener from the EventBus
	 * @param listener
	 */
	public void unregisterListener(EventListener listener) {
		EventListenerRegistry.getInstance().unregister(listener);
	}
	
	/**
	 * Fires an event to all registered listeners
	 * @param event
	 */
	public void fire(LogixEvent event) {
		EventListenerRegistry.getInstance().fireEvent(event);
	}
	
	public static EventManager getInstance() {
		if(eventManager == null) eventManager = new EventManager();
		
		return eventManager;
	}
	
}
