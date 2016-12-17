package com.kneecapdav.JLogix.API.events.project;

import com.kneecapdav.JLogix.API.events.LogixEvent;
import com.kneecapdav.JLogix.API.project.LogixProject;

public class LogixProjectUnloadEvent implements LogixEvent {

	public LogixProject project;
	public boolean saving;
	
	public LogixProjectUnloadEvent(LogixProject project, boolean saving) {
		super();
		this.project = project;
		this.saving = saving;
	}
	
}
