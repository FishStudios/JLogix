package com.kneecapdev.JLogix.API.sim;

public interface Processable {
	//All calculating Elements have to implement this.
	void process();
	boolean checkError();
	
}
