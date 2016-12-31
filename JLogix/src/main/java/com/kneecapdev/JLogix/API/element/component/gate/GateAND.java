package com.kneecapdev.JLogix.API.element.component.gate;

import com.kneecapdev.JLogix.API.element.ElementInfo;
import com.kneecapdev.JLogix.API.element.component.LogixConnector;
import com.kneecapdev.JLogix.API.element.data.LogixState;

@ElementInfo(moduleID = "JLogix", elementID = "AND")
public class GateAND extends LogixGate{

	public GateAND(){
		super();
	}
	
	@Override
	public void process() {
		if(checkError()) return;
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
