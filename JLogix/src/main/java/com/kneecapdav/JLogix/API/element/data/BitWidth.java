package com.kneecapdav.JLogix.API.element.data;

public class BitWidth extends Data{
	
	public static final BitWidth UNKNOWN = new BitWidth(0);
	public static final BitWidth ONE = new BitWidth(1);
	
	private int width;
	private final int maxWidth;
	
	public BitWidth(int width) {
		super("bitwidth");
		this.width = width;
	this.maxWidth = 6; //TODO config
	}
	
	public BitWidth() {
		this(0);
	}
	
	public void setWidth(int width) {
		this.width = Integer.min(this.maxWidth, width);
		
	}
	
	public int getWidth() {
		return width;
	}
	

	public int getMaxWidth() {
		return maxWidth;
	}
}
