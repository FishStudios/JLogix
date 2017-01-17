package com.kneecapdev.jlogix.api.element.component.gate;

import com.kneecapdev.jlogix.api.element.component.LogixComponent;
import com.kneecapdev.jlogix.api.element.component.LogixConnector;
import com.kneecapdev.jlogix.api.element.data.LogixState;
import com.kneecapdev.jlogix.api.sim.Processable;

public abstract class LogixGate extends LogixComponent implements Processable{	
	
	public LogixGate(){
		super();
		
	}	
	
	@Override
	public boolean checkError(){
		for(LogixConnector in_con:inputCons) {
			for(LogixState input:in_con.getStates()){
				if(input==LogixState.ERROR){
					for (LogixConnector outputCon : outputCons) {
						outputCon.setAllState(LogixState.ERROR);
					}
					return true;
				} else if(input==LogixState.UNKNOWN){
					for (LogixConnector outputCon : outputCons) {
						outputCon.setAllState(LogixState.UNKNOWN);
					}
					return true;
				} else if(input==LogixState.INCOMPATIBLE){
					for (LogixConnector outputCon : outputCons) {
						outputCon.setAllState(LogixState.ERROR);
					}
					return true;
				}
			}
		}
		return false;
	}
}