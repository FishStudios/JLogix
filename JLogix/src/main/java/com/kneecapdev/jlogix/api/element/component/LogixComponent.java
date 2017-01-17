package com.kneecapdev.jlogix.api.element.component;

import java.util.ArrayList;

import com.kneecapdev.jlogix.api.element.Element;
import com.kneecapdev.jlogix.api.element.Placeable;
import com.kneecapdev.jlogix.api.element.component.LogixConnector.Type;
import com.kneecapdev.jlogix.api.element.data.BitWidth;
import com.kneecapdev.jlogix.api.element.data.Location;
import com.kneecapdev.jlogix.api.meta.Meta;
import com.kneecapdev.jlogix.api.meta.MetaValue;
import com.kneecapdev.jlogix.api.meta.MetaValue.MetaType;


/**
 * Components are all things, that interact with the Logix-Simulation, such as Gates, Clocks, I/O-Devices etc.
 */
public class LogixComponent extends Element implements Placeable {

	public Location location; 
	public BitWidth bitWidth;
	
	protected ArrayList<LogixConnector> allCons, inputCons, outputCons;
	
	private MetaValue<Meta> connectorMeta;

	public LogixComponent() {
		super();
		
		allCons = new ArrayList<>();
		inputCons = new ArrayList<>();
		outputCons = new ArrayList<>();
	}
	
	@Override
	public void onCreate() {
		location = new Location();
		this.meta.add(location.dataValue);
		
		bitWidth = new BitWidth();
		this.meta.add(bitWidth.dataValue);
		
		connectorMeta = new MetaValue<>("connectors", new Meta()).addTo(meta);
	}
	
	@Override
	public void onLoad() {
		Meta conMeta = connectorMeta.getValue();
		ArrayList<MetaValue<?>> metas = conMeta.get(MetaType.META);
		
		metas.forEach((m) -> {
			Meta meta = (Meta) m.getValue();
			LogixComponent.this.addConnector(LogixConnector.loadConnector(meta));
		});
	}
	
	public void addConnector(LogixConnector c){
		if(allCons.contains(c)) return;
		
		allCons.add(c);
		if(c.isInput()) inputCons.add(c);
		else outputCons.add(c);
		
		connectorMeta.getValue().add(c.dataValue);
	}
	
	public void removeConnector(LogixConnector c){
		if(!allCons.contains(c)) return;
		
		allCons.remove(c);
		if(c.isInput()) inputCons.remove(c);
		else outputCons.remove(c);
		
		connectorMeta.getValue().remove(c.dataValue);
	}
	
	public void changeConnectorType(LogixConnector c, LogixConnector.Type io) {
		if(allCons.contains(c)) {
			if(c.isInput()) {
				c.setIOType(Type.OUTPUT);
				inputCons.remove(c);
				outputCons.add(c);
			} else {
				c.setIOType(Type.INPUT);
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
	
	public void setConnectorsUnkown() {
		allCons.forEach(LogixConnector::setUnknown);
	}
	
}
