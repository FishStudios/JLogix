package com.kneecapdev.jlogix.console;

import com.kneecapdev.jlogix.console.gui.LogixConsoleGUI;

import javafx.application.Platform;

public class LogixConsole {

	private static LogixConsole instance;
	
	private LogixConsoleGUI gui;
	
	private LogixConsole() {
		this.init();
	}
	
	public void append(String str) {
		Platform.runLater(() -> gui.getTextArea().appendText(str));
	}
	
	public void toggle() {
		if(gui.isOpen()) gui.hide();
		else gui.show();
	}
	
	public void terminate() {
		gui.terminate();
	}
	
	private void init() {
		Platform.runLater(() -> gui = new LogixConsoleGUI());
	}
	
	public static LogixConsole getInstance() {
		if(instance == null) instance = new LogixConsole();
		return instance;
	}
	
}
