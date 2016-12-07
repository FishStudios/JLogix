package com.kneecapdav.JLogix.API.element;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaTypeException;
import com.kneecapdav.JLogix.API.meta.MetaValue;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaAccess;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaType;

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
	 * @param Meta Object
	 */
	@SuppressWarnings("unchecked")
	public void readMeta(JSONObject jsonObj) {
		JSONArray jsonMeta = (JSONArray) jsonObj.get("meta");
		
		ArrayList<Object> content = (ArrayList<Object>) jsonMeta.stream().collect(Collectors.toCollection(ArrayList::new));
		
		for(Object obj: content) {
			JSONObject jObj = (JSONObject) obj;
			
			try {
				switch(MetaType.getMetaType(jObj.get("value"))) {
				case BOOLEAN:
					new MetaValue<Boolean>(jObj).addTo(meta);
					break;
				case BYTE:
					new MetaValue<Byte>(jObj).addTo(meta);
					break;
				case DOUBLE:
					new MetaValue<Double>(jObj).addTo(meta);
					break;
				case ENUM:
					new MetaValue<Enum<?>>(jObj).addTo(meta);
					break;
				case INTEGER:
					new MetaValue<Integer>(jObj).addTo(meta);
					break;
				case LONG:
					new MetaValue<Long>(jObj).addTo(meta);
					break;
				case SHORT:
					new MetaValue<Short>(jObj).addTo(meta);
					break;
				case STRING:
					new MetaValue<String>(jObj).addTo(meta);
					break;
				}
			} catch (MetaTypeException e) {
				e.printStackTrace();
			}
			
		}
	};
	
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
