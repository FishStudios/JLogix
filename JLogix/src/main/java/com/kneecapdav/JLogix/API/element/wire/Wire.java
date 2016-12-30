package com.kneecapdav.JLogix.API.element.wire;

import java.util.ArrayList;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.component.LogixConnector;
import com.kneecapdav.JLogix.API.element.data.LogixState;
import com.kneecapdav.JLogix.API.sim.Processable;

public class Wire extends Element implements Processable{
	
	private ArrayList<LogixConnector> allCons, inputCons, outputCons;
	private LogixState wireState;
	private LogixState[] states;
	
	public Wire() {
		super();
		
		allCons = new ArrayList<>();
		inputCons = new ArrayList<>();
		outputCons = new ArrayList<>();
		
		wireState = LogixState.UNKNOWN;
		
	}
	
	@Override
	public void onCreate() {		
		
	}
	
	public void addConnector(LogixConnector c){
		if(allCons.contains(c)) return;
		
		allCons.add(c);
		if(c.isInput()) inputCons.add(c);
		else outputCons.add(c);
	}
	
	public void removeConnector(LogixConnector c){
		if(!allCons.contains(c)) return;
		
		allCons.remove(c);
		if(c.isInput()) inputCons.remove(c);
		else outputCons.remove(c);
	}
	
	public void changeConnectorType(LogixConnector c, LogixConnector.Type io) {
		if(allCons.contains(c)) {
			if(c.isInput()){
				inputCons.remove(c);
				outputCons.add(c);
				
			}else{
				outputCons.remove(c);
				inputCons.add(c);
			}
		}
	}
	
	public boolean hasConnector(LogixConnector c) {
		return allCons.contains(c);
	}
	
	public ArrayList<LogixConnector> getInputs() {
		return this.inputCons;
	}
	
	public ArrayList<LogixConnector> getOutputs() {
		return this.outputCons;
	}
	
	public int getInputCount(){
		return this.inputCons.size();
	}
	
	public int getOutputCount(){
		return this.outputCons.size();
	}
	
	protected void setUnknown() {
		for(int i = 0; i < states.length; i++) states[i] = LogixState.UNKNOWN;
	}
	
	@Override
	public boolean checkError(){
		if(getInputCount() > 1) {
			this.wireState = LogixState.ERROR;
			return true;
			
		} else if(!inputCons.isEmpty()){
			for (LogixConnector out: outputCons) {
			    if(out.bitWidth.getWidth() != inputCons.get(0).bitWidth.getWidth()){
			    	this.wireState = LogixState.INCOMPATIBLE;
			    	return true;
			    }
		    }			
		}
		if(this.wireState == LogixState.ERROR) this.wireState = LogixState.UNKNOWN;
		return false;
		
	}

	@Override
	public void process() {
		if(!checkError()) {
			if(inputCons.isEmpty()){
				this.wireState = LogixState.UNKNOWN;
				setUnknown();
			
			} else {
				this.states = inputCons.get(0).getStates();
			
			}
			for (LogixConnector outputCon : this.outputCons) {
				outputCon.setStates(this.states);

			}
		}
	}
}
