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
					for(int i=0;i<outputCons.size();i++){
						outputCons.get(i).setAllState(LogixState.ERROR);
					}
					return true;
				} else if(input==LogixState.UNKNOWN){
					for(int i=0;i<outputCons.size();i++){
						outputCons.get(i).setAllState(LogixState.UNKNOWN);
					}
					return true;
				} else if(input==LogixState.INCOMPATIBLE){
					for(int i=0;i<outputCons.size();i++){
						outputCons.get(i).setAllState(LogixState.ERROR);
					}
					return true;
				}
			}
		}
		return false;
	}
}