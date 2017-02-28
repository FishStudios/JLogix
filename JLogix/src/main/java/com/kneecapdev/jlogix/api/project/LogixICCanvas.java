package com.kneecapdev.jlogix.api.project;

import java.io.File;
import java.util.ArrayList;

import com.kneecapdev.jlogix.api.element.component.LogixConnector;
import com.kneecapdev.jlogix.api.element.component.gate.ic.GateIC;
import com.kneecapdev.jlogix.api.element.component.gate.ic.ThroughputConnector;

/**
 * This class is representing an integrated circuit.
 * When the canvas got loaded by the LogixProject it will automatically generate a new Element instance which can be used in the project.
 * @author Dominik
 *
 */
public class LogixICCanvas extends LogixCanvas {

	private GateIC gate;
	
	public ArrayList<ThroughputConnector> inputThroughput;
	public ArrayList<ThroughputConnector> outputThroughput;
	
	public LogixICCanvas(LogixProject project, String name) {
		super(project, name);
	}
	
	private LogixICCanvas(GateIC gate, LogixProject project, String name) {
		super(project, name);
		this.gate = gate;
	}
	
	@Override
	public void load(File file) {
		super.load(file);
		
		//TODO Load throughputs.
	}
	
	@Override
	public void save(File file) {
		super.save(file);
		
		//TODO Save throughputs
	}
	
	@Override
	public void simulate() {
		if(gate == null) {
			super.simulate();
			return;
		}
		
		inputThroughput.forEach((i) -> {
			LogixConnector external = this.gate.getConnector(i.getID());
			if(external.getIOType() == i.getIOType()) {
				i.setStates(external.getStates());
			}
		});
		
		super.simulate();
		
		outputThroughput.forEach((i) -> {
			LogixConnector external = this.gate.getConnector(i.getID());
			if(external.getIOType() == i.getIOType()) {
				i.setStates(external.getStates());
			}
		});
	}
	
	public LogixICCanvas bind(GateIC gate) {
		LogixICCanvas tempCanvas = new LogixICCanvas(gate, this.getProject(), this.getName());
		this.elements.forEach((e) -> tempCanvas.add(e.clone()));
		this.inputThroughput.forEach((i) -> tempCanvas.inputThroughput.add((ThroughputConnector) i.clone()));
		this.outputThroughput.forEach((i) -> tempCanvas.outputThroughput.add((ThroughputConnector) i.clone()));
		return tempCanvas;
	}
	
}
