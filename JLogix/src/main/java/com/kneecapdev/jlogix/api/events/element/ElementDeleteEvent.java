package com.kneecapdev.jlogix.api.events.element;

import com.kneecapdev.jlogix.api.element.Element;
import com.kneecapdev.jlogix.api.events.CancelableEvent;
import com.kneecapdev.jlogix.api.project.LogixCanvas;

public class ElementDeleteEvent extends CancelableEvent {

	public LogixCanvas canvas;
	public Element element;
	
	public ElementDeleteEvent(LogixCanvas canvas, Element element) {
		super();
		this.canvas = canvas;
		this.element = element;
	}
	
}
