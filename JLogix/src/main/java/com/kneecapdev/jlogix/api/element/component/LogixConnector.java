package com.kneecapdev.jlogix.api.element.component;

import com.kneecapdev.jlogix.api.element.data.BitWidth;
import com.kneecapdev.jlogix.api.element.data.Data;
import com.kneecapdev.jlogix.api.element.data.LogixState;
import com.kneecapdev.jlogix.api.meta.Meta;
import com.kneecapdev.jlogix.api.meta.MetaValue;
import com.kneecapdev.jlogix.api.meta.MetaValue.MetaAccess;

public class LogixConnector extends Data implements Cloneable {
	
	public MetaValue<String> id;
	public MetaValue<Boolean> isNegated;
	public MetaValue<Boolean> typeMeta;
	
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
	public LogixConnector(String ID, Type typeIO){
		super(ID);
		
		id = new MetaValue<>("id", ID, MetaAccess.READ_ONLY).addTo(dataValue.getValue());
		isNegated = new MetaValue<>("negated", false, MetaAccess.READ_WRITE).addTo(dataValue.getValue());
		typeMeta = new MetaValue<>("type", typeIO.getBooleanValue(), MetaAccess.READ_WRITE).addTo(dataValue.getValue());
	}

	public String getID() {
		return id.getValue();
	}
	
	public Type getIOType() {
		return Type.typeFromBoolean(typeMeta.getValue());
	}
	
	public void setIOType(Type type) {
		typeMeta.setValue(type.getBooleanValue());
	}
	
	public boolean isInput(){
		return this.typeMeta.getValue();
	}
	
	public boolean isOutput(){
		return !this.typeMeta.getValue();
	}
	
	public void setStates(LogixState[] newStates) {
		this.states = newStates;
	}
	
	public LogixState[] getStates(){
		return states;
		
	}
	
	public LogixState[] getNegatedStates(){
		LogixState[] newStates = states;
		for(int i=0; i<states.length;i++){
			newStates[i] = states[i].negate();
		}
		return states;
	}
	
	public void setAllState(LogixState newState) {
		for(int i = 0; i < states.length; i++) states[i] = newState;
	}
	
	protected void setUnknown() {
		for(int i = 0; i < states.length; i++) states[i] = LogixState.UNKNOWN;
	}
	
	protected static LogixConnector loadConnector(Meta meta) {
		String id = (String) meta.getFirst("id").getValue();
		boolean type = (Boolean) meta.getFirst("type").getValue();
		
		LogixConnector connector = new LogixConnector(id, Type.typeFromBoolean(type));
		connector.dataValue.getValue().moveMeta(meta);
		
		return connector;
	}
	
	@Override
	public LogixConnector clone() {
		LogixConnector con = new LogixConnector(this.id.getValue(), this.getIOType());
		return con;
	}
	
}
