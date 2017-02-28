package com.kneecapdev.jlogix.api.element.data;

import com.kneecapdev.jlogix.api.meta.Meta;
import com.kneecapdev.jlogix.api.meta.MetaValue;

public class Data implements Cloneable {

	public MetaValue<Meta> dataValue;
	
	public Data(String ID) {
		dataValue = new MetaValue<>(ID, new Meta());
	}
	
	@Override
	public Data clone() {
		Data data = new Data(dataValue.getID());
		data.dataValue.setValue(this.dataValue.getValue().clone());
		return data;
	}
	
}