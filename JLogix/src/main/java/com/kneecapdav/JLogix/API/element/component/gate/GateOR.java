package com.kneecapdav.JLogix.API.element.component.gate;

import com.kneecapdav.JLogix.API.element.component.LogixConnector;
import com.kneecapdav.JLogix.API.element.data.LogixState;

public class GateOR extends LogixGate{

	public GateOR(){
		super();
		
	}
	
	@Override
	public void process() {
		if(!checkError()){
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
}
