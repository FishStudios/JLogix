package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.LogixEvent;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixProjectLoadEvent implements LogixEvent {

	public LogixProject project;
	
	public LogixProjectLoadEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
