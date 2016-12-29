package com.kneecapdav.JLogix.API.element.component.gate;

import com.kneecapdav.JLogix.API.element.component.LogixComponent;
import com.kneecapdav.JLogix.API.element.component.LogixConnector;
import com.kneecapdav.JLogix.API.element.data.LogixState;
import com.kneecapdav.JLogix.API.sim.Processable;

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