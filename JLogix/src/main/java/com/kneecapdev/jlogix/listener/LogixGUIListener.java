package com.kneecapdev.jlogix.listener;

import com.kneecapdev.jlogix.api.events.EventHook;
import com.kneecapdev.jlogix.api.events.EventListener;
import com.kneecapdev.jlogix.api.project.ProjectManager;
import com.kneecapdev.jlogix.gui.events.GUICloseEvent;
import com.kneecapdev.jlogix.gui.events.GUICreateEvent;

public class LogixGUIListener implements EventListener {

	@EventHook
	public void onGUIPreCreate(GUICreateEvent e) {
		if(e.isPre()) {
			ProjectManager.getInstance().resolveProjects();
		}
	}
	
	@EventHook
	public void onGUIClose(GUICloseEvent e) {
		//Release resources here
	}
	
}
