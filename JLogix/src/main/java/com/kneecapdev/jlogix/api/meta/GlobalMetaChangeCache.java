package com.kneecapdev.jlogix.api.meta;

import com.kneecapdev.jlogix.utils.collections.LimitedArrayDeque;

public class GlobalMetaChangeCache<MetaChangeRecord> extends LimitedArrayDeque<MetaChangeRecord> {

	private static final long serialVersionUID = -2596824322224744517L;

	private static GlobalMetaChangeCache<MetaChangeRecord<?>> instance;
	
	private GlobalMetaChangeCache(int size) {
		super(size);
	}
	
	public static GlobalMetaChangeCache<MetaChangeRecord<?>> getInstance() {
		if(instance == null) instance = new GlobalMetaChangeCache<MetaChangeRecord<?>>(16);
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
