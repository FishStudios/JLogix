package com.kneecapdev.JLogix.API.events.element;

import com.kneecapdev.JLogix.API.element.Element;
import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixCanvas;

public class ElementDeleteEvent extends CancelableEvent {

	public LogixCanvas canvas;
	public Element element;
	
	public ElementDeleteEvent(LogixCanvas canvas, Element element) {
		super();
		this.canvas = canvas;
		this.element = element;
	}
	
}
