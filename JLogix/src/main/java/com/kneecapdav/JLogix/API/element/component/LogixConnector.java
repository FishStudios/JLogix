package com.kneecapdav.JLogix.API.element.component;

import com.kneecapdav.JLogix.API.element.data.BitWidth;
import com.kneecapdav.JLogix.API.element.data.Data;
import com.kneecapdav.JLogix.API.element.data.LogixState;
import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaValue;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaAccess;

public class LogixConnector extends Data{
	
	public Type typeIO;
	
	public MetaValue<String> id;
	public MetaValue<Boolean> isNegated;
	
	public BitWidth bitWidth;
	private LogixState[] states;
	
	public enum Type {
		INPUT, OUTPUT;

		public boolean getBooleanValue() {
			return this == INPUT;
		}
		
		public static Type typeFromBoolean(boolean value) {
			if(value) return INPUT;
			else return OUTPUT;
		}
		
	}
	
	/**
	 * Use this constructor if you need to create a new LogixConnector instance
	 * @param ID
	 */
	public LogixConnector(String ID){
		super(ID);
		
		id = new MetaValue<String>("id", ID, MetaAccess.READ_ONLY).addTo(dataValue.getValue());
		isNegated = new MetaValue<Boolean>("negated", false, MetaAccess.READ_WRITE).addTo(dataValue.getValue());
	}
	
	public boolean isInput(){
		return this.typeIO == Type.INPUT;
	}
	
	public boolean isOutput(){
		return this.typeIO == Type.OUTPUT;
	}
	
	public void setState(LogixState[] newStates) {
		this.states = newStates;
	}
	
	public LogixState[] getStates(){
		return states;
		
	}
	
	protected void setUnknown() {
		for(int i = 0; i < states.length; i++) states[i] = LogixState.UNKNOWN;
	}
	
	protected static LogixConnector loadConnector(Meta meta) {
		String id = (String) meta.getFirst("id").getValue();
		
		LogixConnector connector = new LogixConnector(id);
		connector.dataValue.getValue().moveMeta(meta);
		
		return connector;
	}
	
}
