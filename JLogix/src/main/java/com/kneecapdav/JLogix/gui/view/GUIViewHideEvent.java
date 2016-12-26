package com.kneecapdav.JLogix.gui.view;

import com.kneecapdav.JLogix.API.events.LogixEvent;

public class GUIViewHideEvent implements LogixEvent {

	public GUIView view;
	
	public GUIViewHideEvent(GUIView view) {
		this.view = view;
	}
	
}
