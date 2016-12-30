package com.kneecapdev.JLogix.gui.events;

import com.kneecapdev.JLogix.API.events.EventState;
import com.kneecapdev.JLogix.API.events.LogixEvent;
import com.kneecapdev.JLogix.gui.LogixGUI;

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
