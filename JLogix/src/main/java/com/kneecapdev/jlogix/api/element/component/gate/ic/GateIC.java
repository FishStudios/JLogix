package com.kneecapdev.jlogix.api.element.component.gate.ic;

import com.kneecapdev.jlogix.api.element.component.gate.LogixGate;
import com.kneecapdev.jlogix.api.project.LogixICCanvas;

//TODO: Save icCanvas
public class GateIC extends LogixGate {

	private LogixICCanvas icCanvas;
	
	public GateIC() {
		super();
	}
	
	public void setLogixICCanvas(LogixICCanvas canvas) {
		icCanvas = canvas.bind(this);
	}
	
	@Override
	public void process() {
		if(icCanvas != null) icCanvas.simulate();
	}

}
