package com.kneecapdev.JLogix.gui.view;

import com.kneecapdev.JLogix.API.events.CancelableEvent;

public class GUIViewRegistrationEvent extends CancelableEvent {

	public GUIView view;
	
	public GUIViewRegistrationEvent(GUIView view) {
		this.view = view;
	}
	
}
