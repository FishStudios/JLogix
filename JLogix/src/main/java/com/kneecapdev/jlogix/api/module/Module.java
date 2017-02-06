package com.kneecapdev.jlogix.api.module;

import java.util.ArrayList;

import com.kneecapdev.jlogix.api.events.EventListener;
import com.kneecapdev.jlogix.api.events.EventManager;
import com.kneecapdev.jlogix.console.commands.CommandRegistry;

public class Module {

	public final ModuleInfo moduleInfo = null;

	private ArrayList<EventListener> listeners = new ArrayList<>();
	private ArrayList<Object> commandObjects = new ArrayList<>();
	
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
	 * Returns an ArrayList containing all objects that contains a registered command.
	 * @return registered command objects of the module
	 */
	public ArrayList<Object> getRegisteredCommandObjects() {
		return this.commandObjects;
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
	
	public void registerCommands(Object obj) {
		this.commandObjects.add(obj);
		CommandRegistry.getInstance().register(obj);
	}
	
	public void unregisterCommands(Object obj) {
		this.commandObjects.remove(obj);
		CommandRegistry.getInstance().unregister(obj);
	}
	
}
