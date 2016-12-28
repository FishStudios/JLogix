package com.kneecapdav.JLogix.API.element.data;

public enum LogixState {
	TRUE, FALSE, NULL, UNKNOWN, ERROR, INVISIBLE, INCOMPATIBLE;

	//TODO add color values for different states
	
	public boolean isTrue() {
		return this == LogixState.TRUE;
	}
	
	public boolean isFalse(){
		return this == LogixState.FALSE;
	}
	
	public boolean isValid() {
		return !(this.isFalse() || this.isTrue());
	}
	
	public LogixState negate(){
		if(this.isTrue()) return LogixState.FALSE;
		else if(this.isFalse()) return LogixState.TRUE;
		else return this;
	}
}	
