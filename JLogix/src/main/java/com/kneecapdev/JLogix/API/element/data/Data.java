package com.kneecapdev.JLogix.API.element.data;

import com.kneecapdev.JLogix.API.meta.Meta;
import com.kneecapdev.JLogix.API.meta.MetaValue;

public class Data {

	public MetaValue<Meta> dataValue;
	
	public Data(String ID) {
		dataValue = new MetaValue<>(ID, new Meta());
	}
	
}