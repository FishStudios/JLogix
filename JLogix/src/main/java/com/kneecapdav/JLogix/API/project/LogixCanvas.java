package com.kneecapdav.JLogix.API.project;

import java.util.ArrayList;

import com.kneecapdav.JLogix.API.element.Element;

public class LogixCanvas {

	public ArrayList<Element> elements;
	
	public LogixCanvas() {
		elements = new ArrayList<>();
	}
	
	public void add(Element element) {
		elements.add(element);
	}
	
	public void remove(Element element) {
		elements.remove(element);
	}
	
}
