package com.kneecapdav.JLogix.API.module;

import java.util.ArrayList;

import com.kneecapdav.JLogix.API.events.EventListener;
import com.kneecapdav.JLogix.API.events.EventManager;

public class Module {

	public final ModuleInfo moduleInfo = null;

	private ArrayList<EventListener> listeners = new ArrayList<>();
	
	/**
	 * This method got automatically called from the ModuleLoader immediately after it's been loaded.
	 */
	public void onInit(){};
	/**
	 * This method got automatically called from the ModuleLoader immediately before it's got enabled.
	 */
	public void onEnable(){};
	/**
	 * This method got automatically called from the ModuleLoader immediately before it's got disabled.
	 */
	public void onDisable(){};
	
	/**
	 * Returns an ArrayList of all registered EventListener objects of this module
	 * @return
	 */
	public ArrayList<EventListener> getRegisteredListeners() {
		return this.listeners;
	}
	
	/**
	 * Registers a new EventListener from this module
	 * @param listener
	 */
	public void registerListener(EventListener listener) {
		this.listeners.add(listener);
		EventManager.getInstance().registerListener(listener);
	}
	
	/**
	 * Unregisters an existing EventListener from this module
	 * @param listener
	 */
	public void unregisterListener(EventListener listener) {
		this.listeners.remove(listener);
		EventManager.getInstance().unregisterListener(listener);
	}
	
}
