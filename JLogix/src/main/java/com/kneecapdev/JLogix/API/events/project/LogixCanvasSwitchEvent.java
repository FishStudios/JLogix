package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixCanvas;
import com.kneecapdev.JLogix.API.project.LogixProject;

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
