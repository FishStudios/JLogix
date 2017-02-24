package com.kneecapdev.jlogix.options;

import com.kneecapdev.jlogix.options.gui.LogixOptionsGUI;

public class LogixOptions {

	private static LogixOptions instance;
	
	private LogixOptionsGUI gui;
	
	private LogixOptions() {
		this.init();
	}
	
	public void toggle() {
		if(gui.isOpen()) gui.hide();
		else gui.show();
	}
	
	public void terminate() {
		gui.terminate();
	}
	
	private void init() {
		gui = new LogixOptionsGUI();
	}
	
	public static LogixOptions getInstance() {
		if(instance == null) instance = new LogixOptions();
		return instance;
	}
	
}
