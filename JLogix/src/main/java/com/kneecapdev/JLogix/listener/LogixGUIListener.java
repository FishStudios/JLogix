package com.kneecapdev.JLogix.listener;

import com.kneecapdev.JLogix.API.events.EventHook;
import com.kneecapdev.JLogix.API.events.EventListener;
import com.kneecapdev.JLogix.API.project.ProjectManager;
import com.kneecapdev.JLogix.gui.events.GUICloseEvent;
import com.kneecapdev.JLogix.gui.events.GUICreateEvent;

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
