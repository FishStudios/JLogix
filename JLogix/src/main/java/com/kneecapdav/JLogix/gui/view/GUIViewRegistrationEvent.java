package com.kneecapdav.JLogix.gui.view;

import com.kneecapdav.JLogix.API.events.CancelableEvent;

public class GUIViewRegistrationEvent extends CancelableEvent {

	public GUIView view;
	
	public GUIViewRegistrationEvent(GUIView view) {
		this.view = view;
	}
	
}
