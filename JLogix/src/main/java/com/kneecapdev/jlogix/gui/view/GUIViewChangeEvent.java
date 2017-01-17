package com.kneecapdev.jlogix.gui.view;

import com.kneecapdev.jlogix.api.events.CancelableEvent;

public class GUIViewChangeEvent extends CancelableEvent {

	public GUIView from, to;
	
	public GUIViewChangeEvent(GUIView from, GUIView to) {
		this.from = from;
		this.to = to;
	}
	
}
