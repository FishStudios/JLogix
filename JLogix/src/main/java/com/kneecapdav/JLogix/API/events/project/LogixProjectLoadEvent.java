package com.kneecapdav.JLogix.API.events.project;

import com.kneecapdav.JLogix.API.events.LogixEvent;
import com.kneecapdav.JLogix.API.project.LogixProject;

public class LogixProjectLoadEvent implements LogixEvent {

	public LogixProject project;
	
	public LogixProjectLoadEvent(LogixProject project) {
		super();
		this.project = project;
	}
	
}
