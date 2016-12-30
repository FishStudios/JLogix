package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixCanvas;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixCanvasDeleteEvent extends CancelableEvent {

	public LogixProject project;
	public LogixCanvas newCanvas;
	
	public LogixCanvasDeleteEvent(LogixProject project, LogixCanvas newCanvas) {
		super();
		this.project = project;
		this.newCanvas = newCanvas;
	}
	
}
