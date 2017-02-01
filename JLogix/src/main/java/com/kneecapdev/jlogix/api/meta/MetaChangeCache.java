package com.kneecapdev.jlogix.api.meta;

import com.kneecapdev.jlogix.utils.collections.LimitedArrayDeque;

public class MetaChangeCache<T> extends LimitedArrayDeque<T> {

	private static final long serialVersionUID = 2710465336545074640L;
	
	private MetaValue<T> metaValue;
	
	/**
	 * Creates a new MetaChangeCache object with the given cache size
	 * @param size Size of the cache
	 */
	public MetaChangeCache(MetaValue<T> metaValue, int size) {
		super(size);
		this.metaValue = metaValue;
	}
	
	public MetaValue<T> getMetaValue() {
		return this.metaValue;
	}
	
}
