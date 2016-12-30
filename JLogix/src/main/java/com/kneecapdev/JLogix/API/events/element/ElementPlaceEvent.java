package com.kneecapdev.JLogix.API.events.element;

import com.kneecapdev.JLogix.API.element.Element;
import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixCanvas;

public class ElementPlaceEvent extends CancelableEvent {

	public LogixCanvas canvas;
	public Element element;
	
	public ElementPlaceEvent(LogixCanvas canvas, Element element) {
		super();
		this.canvas = canvas;
		this.element = element;
	}
	
}
