package com.kneecapdev.jlogix.console;

import com.kneecapdev.jlogix.console.gui.LogixConsoleGUI;

public class LogixConsole {

	private static LogixConsole instance;
	
	private LogixConsoleGUI gui;
	
	private LogixConsole() {
		this.init();
	}
	
	public void append(String str) {
		gui.getTextArea().appendText(str + "\n");
	}
	
	public void toggle() {
		if(gui.isOpen()) gui.hide();
		else gui.show();
	}
	
	public void terminate() {
		gui.terminate();
	}
	
	private void init() {
		gui = new LogixConsoleGUI();
	}
	
	public static LogixConsole getInstance() {
		if(instance == null) instance = new LogixConsole();
		return instance;
	}
	
}
