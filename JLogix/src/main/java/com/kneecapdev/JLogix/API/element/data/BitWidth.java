package com.kneecapdev.JLogix.API.element.data;

import com.kneecapdev.JLogix.API.config.ConfigManager;

public class BitWidth extends Data{
	
	public static final BitWidth UNKNOWN = new BitWidth(0);
	public static final BitWidth ONE = new BitWidth(1);
	
	private int width;
	private final int maxWidth;
	
	public BitWidth(int width) {
		super("bitwidth");
		this.width = width;

		this.maxWidth = ConfigManager.getInstance().getInt("bitwidth.max", "main");

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
