package com.kneecapdev.JLogix.API.meta;

public interface MetaValueListener<T> {

	/**
	 * This method gets automatically called when the value of an MetaValue object got changed.
	 * 
	 * @param from old value
	 * @param to new value
	 */
	void change(T from, T to);
	
}
