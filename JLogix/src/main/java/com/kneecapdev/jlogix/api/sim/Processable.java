package com.kneecapdev.jlogix.api.sim;

public interface Processable {
	//All calculating Elements have to implement this.
	void process();
	boolean checkError();
	
}
