package com.kneecapdev.jlogix.gui.view;

import com.kneecapdev.jlogix.api.events.LogixEvent;

public class GUIViewHideEvent implements LogixEvent {

	public GUIView view;
	
	public GUIViewHideEvent(GUIView view) {
		this.view = view;
	}
	
}
