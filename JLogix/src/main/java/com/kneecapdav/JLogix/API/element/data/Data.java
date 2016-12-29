package com.kneecapdav.JLogix.API.element.data;

import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaValue;

public class Data {

	public MetaValue<Meta> dataValue;
	
	public Data(String ID) {
		dataValue = new MetaValue<Meta>(ID, new Meta());
	}
	
}