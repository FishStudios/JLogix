package com.kneecapdev.JLogix.gui.events;

import com.kneecapdev.JLogix.API.events.LogixEvent;
import com.kneecapdev.JLogix.gui.LogixGUI;

public class GUICloseEvent implements LogixEvent {

	public LogixGUI gui;
	
	public GUICloseEvent(LogixGUI gui) {
		this.gui = gui;
	}
	
}
