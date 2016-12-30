package com.kneecapdav.JLogix.API.element.component.gate;

public class GateNOT extends LogixGate{

	public GateNOT(){
		super();
		
	}
	
	@Override
	public void process() {
		if(!checkError()){
			outputCons.get(0).setStates(inputCons.get(0).getNegatedStates());
			
		}
	}
}
