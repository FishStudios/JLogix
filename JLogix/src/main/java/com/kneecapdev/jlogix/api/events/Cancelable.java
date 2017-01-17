package com.kneecapdev.jlogix.api.events;

public interface Cancelable {

	boolean isCanceled();
	void setCanceled(boolean cancel);
	
}
