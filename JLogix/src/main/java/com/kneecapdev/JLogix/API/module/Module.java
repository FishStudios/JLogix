package com.kneecapdev.JLogix.API.module;

import java.util.ArrayList;

import com.kneecapdev.JLogix.API.events.EventListener;
import com.kneecapdev.JLogix.API.events.EventManager;

public class Module {

	public final ModuleInfo moduleInfo = null;

	private ArrayList<EventListener> listeners = new ArrayList<>();
	
	/**
	 * This method got automatically called from the ModuleLoader immediately after it's been loaded.
	 */
	public void onLoad(){}

	/**
	 * This method got automatically called from the ModuleLoader immediately before it's got enabled.
	 */
	public void onEnable(){}

	/**
	 * This method got automatically called from the ModuleLoader immediately before it's got disabled.
	 */
	public void onDisable(){}

	/**
	 * Returns an ArrayList of all registered EventListener objects of this module
	 * @return registered EventListener of the module
	 */
	public ArrayList<EventListener> getRegisteredListeners() {
		return this.listeners;
	}
	
	/**
	 * Registers a new EventListener from this module
	 * @param listener listener to be registered
	 */
	public void registerListener(EventListener listener) {
		this.listeners.add(listener);
		EventManager.getInstance().registerListener(this, listener);
	}
	
	/**
	 * Unregisters an existing EventListener from this module
	 * @param listener listener to be unregistered
	 */
	public void unregisterListener(EventListener listener) {
		this.listeners.remove(listener);
		EventManager.getInstance().unregisterListener(listener);
	}
	
}
