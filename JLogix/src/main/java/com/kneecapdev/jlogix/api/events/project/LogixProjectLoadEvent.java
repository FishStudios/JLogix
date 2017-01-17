package com.kneecapdev.jlogix.api.events.project;

import com.kneecapdev.jlogix.api.events.LogixEvent;
import com.kneecapdev.jlogix.api.project.LogixProject;

public class LogixProjectLoadEvent implements LogixEvent {

	public LogixProject project;
	
	public LogixProjectLoadEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
