package com.kneecapdav.JLogix.API.element.data;

import com.kneecapdav.JLogix.API.meta.MetaValue;

/**
 * This class represents the X and Y coordinates and the orientation of an Element. 
 * 
 */
public class Location extends Data{
	
	public MetaValue<Double> x, y;
	public MetaValue<Integer> orientation;
	
	public Location() {
		super("location");
		x = dataMeta.newDouble("x");
		y = dataMeta.newDouble("y");
		orientation = dataMeta.newInteger("orientation");
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
