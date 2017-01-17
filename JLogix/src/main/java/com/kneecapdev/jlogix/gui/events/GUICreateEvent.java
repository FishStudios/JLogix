package com.kneecapdev.jlogix.gui.events;

import com.kneecapdev.jlogix.api.events.EventState;
import com.kneecapdev.jlogix.api.events.LogixEvent;
import com.kneecapdev.jlogix.gui.LogixGUI;

public class GUICreateEvent implements LogixEvent {

	public LogixGUI gui;
	public EventState state;
	
	public GUICreateEvent(LogixGUI gui, EventState state) {
		this.gui = gui;
		this.state = state;
	}
	
	public boolean isPre() {
		return this.state == EventState.PRE;
	}
	
	public boolean isPost() {
		return this.state == EventState.POST;
	}
	
}
