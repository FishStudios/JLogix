package com.kneecapdav.JLogix.API.element;

import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaValue;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaAccess;

public class Element {
	
	//main Meta object all MetaValues of this Element should be stored in there
	public Meta meta;
	
	private MetaValue<Integer> id;
	
	public Element(int id) {
		this.meta = new Meta();
		this.id = new MetaValue<Integer>("ID", id, MetaAccess.HIDDEN).addTo(meta);
	}
	
	public Meta getMeta(){
		return this.meta;
	}
	
	public Integer getID() {
		return this.id.getValue();
	}
	
	/**
	 * Builds the Element when the object got loaded from an existing project.
	 * 
	 * @param Meta Object
	 */
	public void readMeta(Meta meta) {
	};
}
