package com.kneecapdav.JLogix.API.events;

public interface Cancelable {

	boolean isCanceled();
	void setCanceled(boolean cancel);
	
}
