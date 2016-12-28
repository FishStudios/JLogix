package com.kneecapdav.JLogix.API.element;

import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaValue;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaAccess;
import com.kneecapdav.JLogix.utils.ReflectionUtils;

/**
 * Elements are all Things that can and/or are on the Canvas, but don´t have to be placeable such as Gates, I/O-Devices, Text-Fields, Wires, Drawings etc.
 * 
 * It's important that you have an empty constructor with the super constructor in your element class.
 * Also always call the super constructor in your constructors.
 * 
 * @author Dominik
 *
 */
public abstract class Element {
	
	
	//Main Meta object all MetaValues of this Element should be stored in there
	public Meta meta;
	
	private MetaValue<String> uuid;
	
	public Element() {
		this.meta = new Meta();
		this.uuid = new MetaValue<>("UUID", "", MetaAccess.READ_ONLY).addTo(meta);
		this.onCreate();
	}

	public Meta getMeta(){
		return this.meta;
	}
	
	public UUID getUUID() {
		return UUID.fromString(this.uuid.getValue());
	}
	
	public boolean hasUUID() {
		return (this.uuid.getValue() != null && !this.uuid.getValue().equalsIgnoreCase(""));
	}
	
	public void generateUUID() {
		if(hasUUID()) return;
		ReflectionUtils.setField(this.uuid, "value", UUID.randomUUID().toString());
	}
	
	/**
	 * This method got automatically called when the element got created in a canvas
	 */
	public void onCreate() {}
	
	/**
	 * This method got automatically called when the element got placed in a canvas
	 */
	public void onPlace() {}
	
	/**
	 * This method got automatically called when the element got deleted from a canvas
	 */
	public void onDelete() {}
	
	/**
	 * Builds the Element when the object got loaded from an existing project.
	 * 
	 * @param jsonObj Object
	 */
	public void readMeta(JSONObject jsonObj) {
		JSONArray jsonMeta = (JSONArray) jsonObj.get("meta");
		
		meta.loadFromJSON(jsonMeta);
	}

	@SuppressWarnings("unchecked")
	public JSONObject writeMeta() {
		JSONObject result = new JSONObject();

		if(!this.saveElementInfo(result)) return null;
		
		result.put("meta", meta.saveToJSON());
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private boolean saveElementInfo(JSONObject obj) {
		ElementInfo[] info = this.getClass().getAnnotationsByType(ElementInfo.class);
    	if(info.length != 0) {
    		ElementInfo elementInfo = info[0];
    		obj.put("moduleID", elementInfo.moduleID());
    		obj.put("elementID", elementInfo.elementID());
    		return true;
    	}
    	return false;
	}
	
}
