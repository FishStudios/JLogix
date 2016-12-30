package com.kneecapdev.JLogix.gui.view;

import com.kneecapdev.JLogix.API.events.CancelableEvent;

public class GUIViewUnregistrationEvent extends CancelableEvent {

	public GUIView view;
	
	public GUIViewUnregistrationEvent(GUIView view) {
		this.view = view;
	}
	
}
