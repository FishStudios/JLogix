package com.kneecapdev.JLogix.API.element.data;

import com.kneecapdev.JLogix.API.meta.MetaValue;

/**
 * This class represents the X and Y coordinates and the orientation of an Element. 
 * 
 */
public class Location extends Data{
	
	public MetaValue<Double> x, y;
	public MetaValue<Integer> orientation;
	
	public Location() {
		super("location");
		x = dataValue.getValue().newDouble("x");
		y = dataValue.getValue().newDouble("y");
		orientation = dataValue.getValue().newInteger("orientation");
	}
	
	public double getX() {
		return x.getValue();
	}
	
	public double getY() {
		return y.getValue();
	}
	
	public void setX(double newX) {
		x.setValue(newX);
	}
	
	public void setY(double newY) {
		y.setValue(newY);
	}
	
	public void setOrientation(int newOrientation) {
		orientation.setValue(newOrientation);
		
	}
	
	public int getOrientation(){
		return orientation.getValue();
		
	}
	
	public void turnClockwise(){
		orientation.setValue((orientation.getValue() + 1)%4);
	
	}
	
	public void turnCounterwise(){
		orientation.setValue((orientation.getValue() + 5)%4);

	}
	
}
