package com.kneecapdev.jlogix.gui.view;

import com.kneecapdev.jlogix.api.events.LogixEvent;

public class GUIViewShowEvent implements LogixEvent {

	public GUIView view;
	
	public GUIViewShowEvent(GUIView view) {
		this.view = view;
	}
	
}
