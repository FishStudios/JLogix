package com.kneecapdev.jlogix.gui.events;

import com.kneecapdev.jlogix.api.events.LogixEvent;
import com.kneecapdev.jlogix.gui.LogixGUI;

public class GUICloseEvent implements LogixEvent {

	public LogixGUI gui;
	
	public GUICloseEvent(LogixGUI gui) {
		this.gui = gui;
	}
	
}
