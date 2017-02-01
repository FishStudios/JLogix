package com.kneecapdev.jlogix.api.meta;

import com.kneecapdev.jlogix.utils.collections.LimitedArrayDeque;

public class GlobalMetaChangeCache {

	private static GlobalMetaChangeCache instance;
	
	private LimitedArrayDeque<MetaChangeRecord<?>> changeCache;
	
	private GlobalMetaChangeCache(int size) {
		changeCache = new LimitedArrayDeque<>(size);
	}
	
	public void setCacheSize(int size) {
		this.changeCache.setLimit(size);
	}
	
	public int getCacheSize() {
		return changeCache.getLimit();
	}
	
	public int size() {
		return changeCache.size();
	}
	
	public void push(MetaChangeRecord<?> record) {
		changeCache.push(record);
	}
	
	public MetaChangeRecord<?> pop() {
		return changeCache.pop();
	}
	
	public static GlobalMetaChangeCache getInstance() {
		if(instance == null) instance = new GlobalMetaChangeCache(16);
		return instance;
	}
	
	public static class MetaChangeRecord<T> {
		
		private MetaValue<T> metaValue;
		private T value;
		
		public MetaChangeRecord(MetaValue<T> metaValue, T value) {
			this.metaValue = metaValue;
			this.value = value;
		}
		
		public MetaValue<T> getMetaValue() {
			return this.metaValue;
		}

		public T getValue() {
			return this.value;
		}
		
 		public void undo() {
 			getMetaValue().notifyJavaFxListeners(getValue());
 			getMetaValue().value = getValue();
 		}
		
	}
	
}
