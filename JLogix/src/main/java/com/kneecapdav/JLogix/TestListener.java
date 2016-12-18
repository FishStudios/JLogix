package com.kneecapdav.JLogix;

import com.kneecapdav.JLogix.API.events.EventHook;
import com.kneecapdav.JLogix.API.events.EventHook.Priority;
import com.kneecapdav.JLogix.API.events.EventListener;
import com.kneecapdav.JLogix.API.events.element.ElementPlaceEvent;
import com.kneecapdav.JLogix.API.events.project.LogixProjectLoadEvent;
import com.kneecapdav.JLogix.API.log.LogixLogger;

public class TestListener implements EventListener {

	@EventHook(priority = Priority.HIGHEST)
	public void onProjectLoadHighest(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[HIGHEST] [LoadEvent] Loading " + e.project.getName());
	}
	
	@EventHook(priority = Priority.VERY_HIGH)
	public void onProjectLoadVeryHigh(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[VERY_HIGH] [LoadEvent] Loading " + e.project.getName());
	}
	
	@EventHook(priority = Priority.HIGH)
	public void onProjectLoadHigh(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[HIGH] [LoadEvent] Loading " + e.project.getName());
	}
	
	@EventHook(priority = Priority.NORMAL)
	public void onProjectLoadNormal(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[NORMAL] [LoadEvent] Loading " + e.project.getName());
	}
	
	@EventHook(priority = Priority.LOW)
	public void onProjectLoadLow(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[LOW] [LoadEvent] Loading " + e.project.getName());
	}
	
	@EventHook(priority = Priority.VERY_LOW)
	public void onProjectLoadVeryLow(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[VERY_LOW] [LoadEvent] Loading " + e.project.getName());
	}
	
	@EventHook(priority = Priority.LOWEST)
	public void onProjectLoadLowest(LogixProjectLoadEvent e) {
		LogixLogger.info(this, "[LOWEST] [LoadEvent] Loading " + e.project.getName());
	}
	
	
	@EventHook
	public void onElementPlace(ElementPlaceEvent e) {
		LogixLogger.info(this, "[PlaceEvent] Placing " + e.element.getClass().getName() + " in project " + e.canvas.getProject().getName());
	}
	
}
