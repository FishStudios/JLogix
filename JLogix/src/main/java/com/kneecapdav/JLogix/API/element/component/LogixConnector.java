package com.kneecapdav.JLogix.API.element.component;

import com.kneecapdav.JLogix.API.element.data.BitWidth;
import com.kneecapdav.JLogix.API.element.data.Data;

public class LogixConnector extends Data{
	public Type typeIO;
	public BitWidth bitWidth;
	/*
	private LogixState[] states;
	private MetaValue<Boolean> isNegated;
	*/
	
	public enum Type {
		INPUT, OUTPUT
		
	}
	
	public LogixConnector(){
		super("connector"); //TODO Fix this MetaID Stuff (this is just temporary)
			
	}
		
	public boolean isInput(){
		return this.typeIO == Type.INPUT;
		
	}
	/*
	public void setState(LogixState[] newStates) {
		this.states = newStates;
		
	}
	*/
}
