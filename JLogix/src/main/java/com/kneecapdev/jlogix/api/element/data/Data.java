package com.kneecapdev.jlogix.api.element.data;

import com.kneecapdev.jlogix.api.meta.Meta;
import com.kneecapdev.jlogix.api.meta.MetaValue;

public class Data {

	public MetaValue<Meta> dataValue;
	
	public Data(String ID) {
		dataValue = new MetaValue<>(ID, new Meta());
	}
	
}