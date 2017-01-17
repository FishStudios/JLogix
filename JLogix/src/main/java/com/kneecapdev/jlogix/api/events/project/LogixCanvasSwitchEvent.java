package com.kneecapdev.jlogix.api.events.project;

import com.kneecapdev.jlogix.api.events.CancelableEvent;
import com.kneecapdev.jlogix.api.project.LogixCanvas;
import com.kneecapdev.jlogix.api.project.LogixProject;

public class LogixCanvasSwitchEvent extends CancelableEvent {

	public LogixProject project;
	public LogixCanvas oldCanvas, newCanvas;
	
	public LogixCanvasSwitchEvent(LogixProject project, LogixCanvas oldCanvas, LogixCanvas newCanvas) {
		super();
		this.project = project;
		this.oldCanvas = oldCanvas;
		this.newCanvas = newCanvas;
	}
	
}
