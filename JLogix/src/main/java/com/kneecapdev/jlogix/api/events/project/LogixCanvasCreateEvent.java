package com.kneecapdev.jlogix.api.events.project;

import com.kneecapdev.jlogix.api.events.CancelableEvent;
import com.kneecapdev.jlogix.api.project.LogixCanvas;
import com.kneecapdev.jlogix.api.project.LogixProject;

public class LogixCanvasCreateEvent extends CancelableEvent {

	public LogixProject project;
	public LogixCanvas newCanvas;
	
	public LogixCanvasCreateEvent(LogixProject project, LogixCanvas newCanvas) {
		super();
		this.project = project;
		this.newCanvas = newCanvas;
	}

}
