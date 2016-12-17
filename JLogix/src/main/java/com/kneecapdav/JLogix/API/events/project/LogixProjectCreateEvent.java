package com.kneecapdav.JLogix.API.events.project;

import com.kneecapdav.JLogix.API.events.CancelableEvent;
import com.kneecapdav.JLogix.API.project.LogixProject;

public class LogixProjectCreateEvent extends CancelableEvent {

	public LogixProject project;
	
	public LogixProjectCreateEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
