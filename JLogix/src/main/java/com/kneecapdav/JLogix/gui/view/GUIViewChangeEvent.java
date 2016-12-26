package com.kneecapdav.JLogix.gui.view;

import com.kneecapdav.JLogix.API.events.CancelableEvent;

public class GUIViewChangeEvent extends CancelableEvent {

	public GUIView from, to;
	
	public GUIViewChangeEvent(GUIView from, GUIView to) {
		this.from = from;
		this.to = to;
	}
	
}
