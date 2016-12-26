package com.kneecapdav.JLogix.gui.view;

import com.kneecapdav.JLogix.API.events.CancelableEvent;

public class GUIViewUnregistrationEvent extends CancelableEvent {

	public GUIView view;
	
	public GUIViewUnregistrationEvent(GUIView view) {
		this.view = view;
	}
	
}
