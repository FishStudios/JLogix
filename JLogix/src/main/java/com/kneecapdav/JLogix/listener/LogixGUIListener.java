package com.kneecapdav.JLogix.listener;

import com.kneecapdav.JLogix.API.events.EventHook;
import com.kneecapdav.JLogix.API.events.EventListener;
import com.kneecapdav.JLogix.API.project.ProjectManager;
import com.kneecapdav.JLogix.gui.events.GUICloseEvent;
import com.kneecapdav.JLogix.gui.events.GUICreateEvent;

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
