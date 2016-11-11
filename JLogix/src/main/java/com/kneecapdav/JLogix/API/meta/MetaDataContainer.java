package com.kneecapdav.JLogix.API.meta;

import java.util.HashMap;

public class MetaDataContainer {

	public HashMap<String, MetaData> metaData = new HashMap<>();
	
	public void add(String name, MetaData data) {
		metaData.put(name, data);
	}
	
	public void remove(String name) {
		metaData.remove(name);
	}
	
	public boolean contains(String name) {
		return metaData.containsKey(name);
	}
	
	public MetaData get(String name) {
		if(contains(name)) return metaData.get(name);
		return null;
	}
	
}
