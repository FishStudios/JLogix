package com.kneecapdev.jlogix.api.events.project;

import com.kneecapdev.jlogix.api.events.LogixEvent;
import com.kneecapdev.jlogix.api.project.LogixProject;

public class LogixProjectUnloadEvent implements LogixEvent {

	public LogixProject project;
	public boolean saving;
	
	public LogixProjectUnloadEvent(LogixProject project, boolean saving) {
		super();
		this.project = project;
		this.saving = saving;
	}
	
}
