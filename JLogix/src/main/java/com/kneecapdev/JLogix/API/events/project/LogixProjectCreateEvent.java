package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixProjectCreateEvent extends CancelableEvent {

	public LogixProject project;
	
	public LogixProjectCreateEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
