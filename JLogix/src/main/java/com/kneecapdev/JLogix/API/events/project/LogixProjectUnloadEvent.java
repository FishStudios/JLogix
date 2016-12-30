package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.LogixEvent;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixProjectUnloadEvent implements LogixEvent {

	public LogixProject project;
	public boolean saving;
	
	public LogixProjectUnloadEvent(LogixProject project, boolean saving) {
		super();
		this.project = project;
		this.saving = saving;
	}
	
}
