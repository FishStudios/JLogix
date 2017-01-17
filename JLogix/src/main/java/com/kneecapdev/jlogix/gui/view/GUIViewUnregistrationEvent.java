package com.kneecapdev.jlogix.gui.view;

import com.kneecapdev.jlogix.api.events.CancelableEvent;

public class GUIViewUnregistrationEvent extends CancelableEvent {

	public GUIView view;
	
	public GUIViewUnregistrationEvent(GUIView view) {
		this.view = view;
	}
	
}
