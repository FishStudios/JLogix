package com.kneecapdav.JLogix.gui.view;

import com.kneecapdav.JLogix.API.events.LogixEvent;

public class GUIViewShowEvent implements LogixEvent {

	public GUIView view;
	
	public GUIViewShowEvent(GUIView view) {
		this.view = view;
	}
	
}
