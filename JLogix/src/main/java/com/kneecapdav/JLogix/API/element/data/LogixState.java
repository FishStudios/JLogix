package com.kneecapdav.JLogix.API.element.data;

import java.awt.Color;

import com.kneecapdav.JLogix.API.config.ConfigManager;

public enum LogixState {
	TRUE, FALSE, UNKNOWN, ERROR, INVISIBLE, INCOMPATIBLE, MUTLIPLE;

	public Color getColor() {
		    return new Color(
		            Integer.valueOf( ConfigManager.config.getString(this.name() + "_COLOR").substring( 1, 3 ), 16 ));
		    
	}
	
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
