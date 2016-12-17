package com.kneecapdav.JLogix.API.events.project;

import com.kneecapdav.JLogix.API.events.CancelableEvent;
import com.kneecapdav.JLogix.API.project.LogixCanvas;
import com.kneecapdav.JLogix.API.project.LogixProject;

public class LogixCanvasCreateEvent extends CancelableEvent {

	public LogixProject project;
	public LogixCanvas newCanvas;
	
	public LogixCanvasCreateEvent(LogixProject project, LogixCanvas newCanvas) {
		super();
		this.project = project;
		this.newCanvas = newCanvas;
	}

}
