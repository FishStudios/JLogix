package com.kneecapdev.jlogix.utils.collections;

import java.util.ArrayDeque;

public class LimitedArrayDeque<E> extends ArrayDeque<E> {

	private static final long serialVersionUID = 1661774584284772351L;
	
	private int limit;

	public LimitedArrayDeque(int limit) {
		super(limit + 1);
		this.limit = limit;
	}

	public int getLimit() {
		return this.limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
		while (this.size() >= limit) {
			this.removeLast();
		}
	}
	
	@Override
	public void push(E o) {
		while (this.size() >= limit) {
			this.removeLast();
		}
		super.push(o);
	}
	
}
