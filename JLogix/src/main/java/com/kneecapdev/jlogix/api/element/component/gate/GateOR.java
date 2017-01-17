package com.kneecapdev.jlogix.api.element.component.gate;

import com.kneecapdev.jlogix.api.element.ElementInfo;
import com.kneecapdev.jlogix.api.element.component.LogixConnector;
import com.kneecapdev.jlogix.api.element.data.LogixState;

@ElementInfo(moduleID = "JLogix", elementID = "OR")
public class GateOR extends LogixGate{

	public GateOR(){
		super();
		
	}
	
	@Override
	public void process() {
		if(checkError()) return;
		LogixState[] output = new LogixState[bitWidth.getWidth()];
		for(int i=0; i<bitWidth.getWidth(); i++) {
			output[i] = LogixState.FALSE;
			for(LogixConnector in_con:inputCons) {
				if(in_con.getStates()[i]==LogixState.TRUE){
					output[i] = LogixState.TRUE;
					break;
				}
			}
		}
		outputCons.get(0).setStates(output);
	}
}
