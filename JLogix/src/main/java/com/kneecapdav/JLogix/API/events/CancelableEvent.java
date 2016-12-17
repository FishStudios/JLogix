package com.kneecapdav.JLogix.API.events;

public abstract class CancelableEvent implements LogixEvent, Cancelable {

	private boolean canceled = false;
	
	@Override
	public boolean isCanceled() {
		return this.canceled;
	}
	
	@Override
	public void setCanceled(boolean cancel) {
		this.canceled = cancel;
	}
	
}
