package com.kneecapdev.JLogix.gui.view;

import com.kneecapdev.JLogix.API.events.LogixEvent;

public class GUIViewShowEvent implements LogixEvent {

	public GUIView view;
	
	public GUIViewShowEvent(GUIView view) {
		this.view = view;
	}
	
}
