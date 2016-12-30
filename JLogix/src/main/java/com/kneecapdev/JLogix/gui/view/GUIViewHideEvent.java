package com.kneecapdev.JLogix.gui.view;

import com.kneecapdev.JLogix.API.events.LogixEvent;

public class GUIViewHideEvent implements LogixEvent {

	public GUIView view;
	
	public GUIViewHideEvent(GUIView view) {
		this.view = view;
	}
	
}
