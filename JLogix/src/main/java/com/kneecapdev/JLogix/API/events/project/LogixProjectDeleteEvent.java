package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixProjectDeleteEvent extends CancelableEvent {

	public LogixProject project;
	
	public LogixProjectDeleteEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
