package com.kneecapdev.jlogix.api.meta;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kneecapdev.jlogix.utils.ReflectionUtils;

/**
 * 
 * @author Dominik
 *
 * @param <T> Type of this meta object.
 */
public class MetaValue<T> implements Cloneable {

	public enum MetaType {
		BOOLEAN, BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, STRING, META;
		
		public static MetaType getMetaType(Object o) throws MetaTypeException {
			if(o instanceof Boolean) return BOOLEAN;
			if(o instanceof Byte) return BYTE;
			if(o instanceof Short) return SHORT;
			if(o instanceof Integer) return INTEGER;
			if(o instanceof Long) return LONG;
			if(o instanceof Float) return FLOAT;
			if(o instanceof Double) return DOUBLE;
			if(o instanceof String) return STRING;
			if(o instanceof JSONArray || o instanceof Meta) return META;
			throw new MetaTypeException("Unknown MetaValue type! (" + o + ")");
		}
	}
	
	/**
	 * Access level for MetaValues
	 */
	public enum MetaAccess {
		READ_WRITE, READ_ONLY, HIDDEN
	}
	
	private ArrayList<MetaValueListener<T>> listeners;
	
	public String id;
	
	public MetaType type;
	private T value;
	
	public MetaAccess access;
	
	public MetaValue(JSONObject obj) {
		this.type = MetaType.valueOf((String) obj.get("type"));
		this.access = MetaAccess.valueOf((String) obj.get("access"));
		this.id = (String) obj.get("id");
		
		Object value = obj.get("value");
		
		if(value == null) return;
		
		switch(type) {
			case BOOLEAN:
				ReflectionUtils.setField(this, "value", obj.get("value"));
				break;
			case BYTE:
				ReflectionUtils.setField(this, "value", Byte.parseByte(Long.toString((Long)obj.get("value"))));
				break;
			case FLOAT:
				ReflectionUtils.setField(this, "value", Float.parseFloat(Double.toString((Double)obj.get("value"))));
				break;
			case DOUBLE:
				ReflectionUtils.setField(this, "value", obj.get("value"));
				break;
			case INTEGER:
				ReflectionUtils.setField(this, "value", Math.toIntExact((long) obj.get("value")));
				break;
			case LONG:
				ReflectionUtils.setField(this, "value", obj.get("value"));
				break;
			case SHORT:
				ReflectionUtils.setField(this, "value", Short.parseShort(Long.toString((Long) obj.get("value"))));
				break;
			case STRING:
				ReflectionUtils.setField(this, "value", obj.get("value"));
				break;
			case META:
				Meta meta = new Meta();
				meta.loadFromJSON((JSONArray) obj.get("value"));
				ReflectionUtils.setField(this, "value", meta);
				break;
		}
		
	}
	
	/**
	 * Creates new MetaValue instance.
	 * MetaAccess will be set to READ_WRITE by default.
	 * 
	 * @param id id
	 * @param value value
	 */
	public MetaValue(String id, T value) {
		this.id = id;
		listeners = new ArrayList<>();
		this.value = value;
		this.access = MetaAccess.READ_WRITE;
		try {
			this.type = MetaType.getMetaType(value);
		} catch (MetaTypeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates new MetaValue instance.
	 * 
	 * @param id id
	 * @param value value
	 * @param access level
	 */	
	public MetaValue(String id, T value, MetaAccess access) {
		this.id = id;
		listeners = new ArrayList<>();
		this.value = value;
		this.access = access;
		try {
			this.type = MetaType.getMetaType(value);
		} catch (MetaTypeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates new empty MetaValue instance.
	 * 
	 * @param type type
	 * @param id id
	 * @param access level
	 */	
	public MetaValue(MetaType type, String id, MetaAccess access) {
		this.id = id;
		listeners = new ArrayList<>();
		this.access = access;
		this.type = type;
	}
	
	/**
     * Adds the MetaValue to a given Meta object	
	 * 
	 * @param meta object
	 */
	public MetaValue<T> addTo(Meta meta) {
		if(meta.contains(this.id)) {
			MetaValue<T> m = meta.getFirst(this.id);
			m.setValue(this.value);
			return m;
		} else {
			meta.add(this);
			return this;
		}
	}
	
	/**
	 * Sets the value for this meta object.
	 * Automatically calls all registered change listeners.
	 * 
	 * @param value value
	 */
	public void setValue(T value) {
		if(access == MetaAccess.READ_ONLY) return;
		if(!listeners.isEmpty()) {
			listeners.forEach((listener) -> listener.change(this.value, value));
		}
		this.value = value;
	}
	
	/**
	 * Sets the value for this meta object.
	 * Didn't calls change listeners.
	 * 
	 * This method should only be used for actions not caused by the user.
	 * 
	 * @param value value
	 */
	public void setValueSilent(T value) {
		if(access == MetaAccess.READ_ONLY) return;
		this.value = value;
	}
	
	/**
	 * Gets the current value of this Meta object
	 * 
	 * @return Current value
	 */
	public T getValue() {
		return value;
	}
	
	
	/**
	 * 
	 * Register new change listener which gets called when the value changes.
	 * 
	 * @param listener listener to be registered
	 */
	public void addListener(MetaValueListener<T> listener) {
		if(!listeners.contains(listener)) listeners.add(listener);
	}
	
	/**
	 * Unregister change listener.
	 * 
	 * @param listener listener to be unregistered
	 */
	public void removeListener(MetaValueListener<T> listener) {
		if(listeners.contains(listener)) listeners.remove(listener);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject saveToJSON() {
		JSONObject metaJSON = new JSONObject();
		metaJSON.put("type", this.type.toString());
		metaJSON.put("access", this.access.toString());
		metaJSON.put("id", this.id); 
		if(this.type == MetaType.META) metaJSON.put("value", ((Meta)this.value).saveToJSON());
		else metaJSON.put("value", this.value);
		return metaJSON;
	}
	
	/**
	 * 
	 * @return Exact copy of this object
	 */
	@Override
	public MetaValue<T> clone() {
		MetaValue<T> meta = new MetaValue<>(this.id, this.value);
		if(!listeners.isEmpty()) listeners.forEach(meta::addListener);
		return meta;
	}
	
	
	
}
