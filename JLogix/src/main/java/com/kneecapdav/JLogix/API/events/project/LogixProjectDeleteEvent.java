package com.kneecapdav.JLogix.API.events.project;

import com.kneecapdav.JLogix.API.events.CancelableEvent;
import com.kneecapdav.JLogix.API.project.LogixProject;

public class LogixProjectDeleteEvent extends CancelableEvent {

	public LogixProject project;
	
	public LogixProjectDeleteEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
