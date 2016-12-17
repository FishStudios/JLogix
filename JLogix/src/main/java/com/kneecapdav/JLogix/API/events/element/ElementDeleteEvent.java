package com.kneecapdav.JLogix.API.events.element;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.events.CancelableEvent;
import com.kneecapdav.JLogix.API.project.LogixCanvas;

public class ElementDeleteEvent extends CancelableEvent {

	public LogixCanvas canvas;
	public Element element;
	
	public ElementDeleteEvent(LogixCanvas canvas, Element element) {
		super();
		this.canvas = canvas;
		this.element = element;
	}
	
}
