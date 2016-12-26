package com.kneecapdav.JLogix.gui.events;

import com.kneecapdav.JLogix.API.events.LogixEvent;
import com.kneecapdav.JLogix.gui.LogixGUI;

public class GUICloseEvent implements LogixEvent {

	public LogixGUI gui;
	
	public GUICloseEvent(LogixGUI gui) {
		this.gui = gui;
	}
	
}
