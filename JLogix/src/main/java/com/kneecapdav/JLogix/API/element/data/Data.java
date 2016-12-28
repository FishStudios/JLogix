package com.kneecapdav.JLogix.API.element.data;

import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaValue;

public class Data {

	public Meta dataMeta;
	
	public MetaValue<Meta> dataValue;
	
	public Data(String ID) {
		dataMeta = new Meta();
		
		dataValue = new MetaValue<Meta>(ID, dataMeta);
	}
	
}