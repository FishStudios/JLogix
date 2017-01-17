package com.kneecapdev.jlogix.api.events.project;

import com.kneecapdev.jlogix.api.events.CancelableEvent;
import com.kneecapdev.jlogix.api.project.LogixProject;

public class LogixProjectCreateEvent extends CancelableEvent {

	public LogixProject project;
	
	public LogixProjectCreateEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
