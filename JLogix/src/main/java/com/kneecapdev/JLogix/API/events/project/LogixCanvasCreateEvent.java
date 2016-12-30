package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixCanvas;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixCanvasCreateEvent extends CancelableEvent {

	public LogixProject project;
	public LogixCanvas newCanvas;
	
	public LogixCanvasCreateEvent(LogixProject project, LogixCanvas newCanvas) {
		super();
		this.project = project;
		this.newCanvas = newCanvas;
	}

}
