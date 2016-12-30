package com.kneecapdev.JLogix.API.events.project;

import com.kneecapdev.JLogix.API.events.CancelableEvent;
import com.kneecapdev.JLogix.API.project.LogixProject;

public class LogixProjectSwitchEvent extends CancelableEvent {

	public LogixProject oldProject, newProject;
	
	public LogixProjectSwitchEvent(LogixProject oldProject, LogixProject newProject) {
		super();
		this.oldProject = oldProject;
		this.newProject = newProject;
	}
	
}
