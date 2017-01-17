package com.kneecapdev.jlogix.api.project;

import java.io.File;

/**
 * This class is representing a custom gate.
 * When the canvas got loaded by the LogixProject it will automatically generate a new Element instance which can be used in the project.
 * @author Dominik
 *
 */
public class LogixElementCanvas extends LogixCanvas {

	public LogixElementCanvas(LogixProject project, String name) {
		super(project, name);
	}

	@Override
	public void load(File file) {
		super.load(file);
		
		//TODO Load element data.
	}
	
	@Override
	public void save(File file) {
		super.save(file);
		
		//TODO Save element data
	}
	
	private void buildElement() {
		
	}
	
}
