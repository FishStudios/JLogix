package com.kneecapdev.jlogix.gui.view;

import com.kneecapdev.jlogix.api.events.CancelableEvent;

public class GUIViewRegistrationEvent extends CancelableEvent {

	public GUIView view;
	
	public GUIViewRegistrationEvent(GUIView view) {
		this.view = view;
	}
	
}
