package com.kneecapdev.jlogix.api.events.project;

import com.kneecapdev.jlogix.api.events.CancelableEvent;
import com.kneecapdev.jlogix.api.project.LogixProject;

public class LogixProjectSwitchEvent extends CancelableEvent {

	public LogixProject oldProject, newProject;
	
	public LogixProjectSwitchEvent(LogixProject oldProject, LogixProject newProject) {
		super();
		this.oldProject = oldProject;
		this.newProject = newProject;
	}
	
}
