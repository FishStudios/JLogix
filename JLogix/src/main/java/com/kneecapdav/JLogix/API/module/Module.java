package com.kneecapdav.JLogix.API.module;

public class Module {

	public final ModuleInfo moduleInfo = null;
	
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
	
}
