package com.kneecapdav.JLogix.API.element.component.gate;

import com.kneecapdav.JLogix.API.element.component.LogixConnector;
import com.kneecapdav.JLogix.API.element.data.LogixState;

public class GateAND extends LogixGate{

	public GateAND(){
		super();
		
	}
	
	@Override
	public void process() {
		if(!checkError()){
			LogixState[] output = new LogixState[bitWidth.getWidth()];
			for(int i=0; i<bitWidth.getWidth(); i++) {
				output[i] = LogixState.TRUE;
				for(LogixConnector in_con:inputCons) {
					if(in_con.getStates()[i]==LogixState.FALSE){
						output[i] = LogixState.FALSE;
						break;
					}
				}
			}
			outputCons.get(0).setStates(output);
		}
	}
}
