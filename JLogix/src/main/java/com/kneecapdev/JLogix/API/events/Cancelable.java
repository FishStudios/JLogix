package com.kneecapdev.JLogix.API.events;

public interface Cancelable {

	boolean isCanceled();
	void setCanceled(boolean cancel);
	
}
