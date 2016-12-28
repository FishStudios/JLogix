package com.kneecapdav.JLogix.API.element.wire;

import java.util.ArrayList;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.component.LogixConnector;
import com.kneecapdav.JLogix.API.element.data.LogixState;
import com.kneecapdav.JLogix.API.meta.MetaValue;

public class Wire extends Element{
	
	//private ArrayList<Location> cornerPoints;
	
	private MetaValue<Integer> connectorCount; //Not sure if even needed
	private ArrayList<LogixConnector> allCons, inputCons, outputCons;
	private LogixState state;
	
	public Wire() {
		super();
		
		allCons = new ArrayList<>();
		inputCons = new ArrayList<>();
		outputCons = new ArrayList<>();
		
		state = LogixState.UNKNOWN;
	}
	
	@Override
	public void onCreate() {		
		connectorCount = this.meta.newInteger("connectorCount");
		
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
	
	public boolean checkError(){
		if(getInputCount() > 1) {
			this.state = LogixState.ERROR;
			return true;
		
		} else if(!inputCons.isEmpty()){
			for (LogixConnector out: outputCons) {
			    if(out.bitWidth.getWidth() != inputCons.get(0).bitWidth.getWidth());
				this.state = LogixState.INCOMPATIBLE;
				return true;
			}
		}
		if(this.state == LogixState.ERROR) this.state = LogixState.UNKNOWN;
		return false;
		
	}
}
