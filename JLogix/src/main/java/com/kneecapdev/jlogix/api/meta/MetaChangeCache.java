package com.kneecapdev.jlogix.api.meta;

import com.kneecapdev.jlogix.utils.collections.LimitedArrayDeque;

public class MetaChangeCache<T> {

	private MetaValue<T> metaValue;
	
	private LimitedArrayDeque<T> changeCache;
	
	/**
	 * Creates a new MetaChangeCache object with the given cache size
	 * @param size Size of the cache
	 */
	public MetaChangeCache(MetaValue<T> metaValue, int size) {
		this.metaValue = metaValue;
		changeCache = new LimitedArrayDeque<T>(size);
	}
	
	public MetaValue<T> getMetaValue() {
		return this.metaValue;
	}
	
	/**
	 * Sets the max. size of the cache
	 * @param size
	 */
	public void setCacheSize(int size) {
		this.changeCache.setLimit(size);
	}
	
	/**
	 * Returns the max. size of the cache
	 * @return
	 */
	public int getCacheSize() {
		return this.changeCache.getLimit();
	}
	
	/**
	 * Returns the current size of the cache
	 * @return
	 */
	public int size() {
		return changeCache.size();
	}
	
	/**
	 * Pushes a new object into the cache
	 * @param obj
	 */
	public void push(T obj) {
		changeCache.push(obj);
	}
	
	/**
	 * Pops a object from the cache;
	 * @return
	 */
	public T pop() {
		return changeCache.pop();
	}
	
}
