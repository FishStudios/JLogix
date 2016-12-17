package com.kneecapdav.JLogix;

import com.google.common.eventbus.Subscribe;
import com.kneecapdav.JLogix.API.events.EventListener;
import com.kneecapdav.JLogix.API.events.element.ElementPlaceEvent;
import com.kneecapdav.JLogix.API.events.project.LogixProjectLoadEvent;
import com.kneecapdav.JLogix.API.log.LogixLogger;

public class TestListener implements EventListener {

	@Subscribe
	public void onProjectLoad(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[LoadEvent] Loading " + e.project.getName());
	}
	
	@Subscribe
	public void onElementPlace(ElementPlaceEvent e) {
		LogixLogger.info(this, "[PlaceEvent] Placing " + e.element.getClass().getName() + " in project " + e.canvas.getProject().getName());
	}
	
}
