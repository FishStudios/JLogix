package com.kneecapdev.JLogix.API.element.component.gate;

import com.kneecapdev.JLogix.API.element.ElementInfo;

@ElementInfo(moduleID = "JLogix", elementID = "NOT")
public class GateNOT extends LogixGate{

	public GateNOT(){
		super();
		
	}
	
	@Override
	public void process() {
		if(checkError()) return;
		outputCons.get(0).setStates(inputCons.get(0).getNegatedStates());
	}
}
