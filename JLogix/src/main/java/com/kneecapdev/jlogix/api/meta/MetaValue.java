package com.kneecapdev.jlogix.api.meta;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kneecapdev.jlogix.api.meta.GlobalMetaChangeCache.MetaChangeRecord;
import com.kneecapdev.jlogix.utils.ReflectionUtils;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 
 * @author Dominik
 *
 * @param <T> Type of this meta object.
 */
public class MetaValue<T> implements Cloneable, ObservableValue<T> {

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
		READ_WRITE, READ_ONLY;
	}
	
	public enum UserAccess {
		HIDDEN, READ_ONLY, LIVE_WRITE, DEFAULT_WRITE;
	}
	
	private ArrayList<MetaValueListener<T>> listeners;
	
	private String id;
	
	public MetaType type;
	protected T value;
	
	public MetaAccess access;
	public UserAccess userAccess;
	
	public MetaChangeCache<T> changeCache;
	
	protected MetaValue(JSONObject obj) {
		this.type = MetaType.valueOf((String) obj.get("type"));
		this.access = MetaAccess.valueOf((String) obj.get("access"));
		this.userAccess = UserAccess.valueOf((String) obj.get("userAccess"));
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
		this.userAccess = UserAccess.LIVE_WRITE;
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
	 * @param access meta access level
	 */	
	public MetaValue(String id, T value, MetaAccess access) {
		this.id = id;
		listeners = new ArrayList<>();
		this.value = value;
		this.access = access;
		this.userAccess = UserAccess.LIVE_WRITE;
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
	 * @param access meta access level
	 */	
	public MetaValue(String id, T value, UserAccess userAccess) {
		this.id = id;
		listeners = new ArrayList<>();
		this.value = value;
		this.access = MetaAccess.READ_WRITE;
		this.userAccess = userAccess;
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
	 * @param access meta access level
	 * @param userAccess user access level
	 */	
	public MetaValue(String id, T value, MetaAccess access, UserAccess userAccess) {
		this.id = id;
		listeners = new ArrayList<>();
		this.value = value;
		this.access = access;
		this.userAccess = userAccess;
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
	 */	
	public MetaValue(MetaType type, String id) {
		this.id = id;
		listeners = new ArrayList<>();
		this.access = MetaAccess.READ_WRITE;
		this.userAccess = UserAccess.LIVE_WRITE;
		this.type = type;
	}
	
	/**
	 * Creates new empty MetaValue instance.
	 * 
	 * @param type type
	 * @param id id
	 * @param access meta access level
	 */	
	public MetaValue(MetaType type, String id, MetaAccess access) {
		this.id = id;
		listeners = new ArrayList<>();
		this.access = access;
		this.userAccess = UserAccess.LIVE_WRITE;
		this.type = type;
	}
	
	/**
	 * Creates new empty MetaValue instance.
	 * 
	 * @param type type
	 * @param id id
	 * @param userAccess user access level
	 */	
	public MetaValue(MetaType type, String id, UserAccess userAccess) {
		this.id = id;
		listeners = new ArrayList<>();
		this.access = MetaAccess.READ_WRITE;
		this.userAccess = userAccess;
		this.type = type;
	}
	
	/**
	 * Creates new empty MetaValue instance.
	 * 
	 * @param type type
	 * @param id id
	 * @param access meta access level
	 * @param userAccess user access level
	 */	
	public MetaValue(MetaType type, String id, MetaAccess access, UserAccess userAccess) {
		this.id = id;
		listeners = new ArrayList<>();
		this.access = access;
		this.userAccess = userAccess;
		this.type = type;
	}
	
	/**
	 * Returns the id of the MetaValue
	 * @return id of the MetaValue
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * Creates a new MetaChangeCache for this MetaValue instance
	 * @param cacheSize Size of the cache
	 * @return Current MetaValue instance
	 */
	public MetaValue<T> setupChangeCache(int cacheSize) {
		this.changeCache = new MetaChangeCache<T>(this, cacheSize);
		return this;
	}
	
	/**
	 * Returns if the MetaChangeCache is already created
	 * @return Boolean if the MetaChangeCache is created or not
	 */
	public boolean hasChangeCache() {
		return this.changeCache != null;
	}
	
	/**
	 * Returns null if setupChangeCache was not called at this point
	 * @return MetaChangeCache instance of this MetaValue
	 */
	public MetaChangeCache<T> getChangeCache() {
		return this.changeCache;
	}
	
	/**
	 * Undos the last change of this MetaValue object
	 */
	public void undo() {
		if(!hasChangeCache()) return;
		if(this.changeCache.size() > 0) this.value = this.changeCache.pop();
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
	public void setValue(T newValue) {
		if(access == MetaAccess.READ_ONLY) return;
		//Call all internal MetaListeners
		if(!listeners.isEmpty()) {
			listeners.forEach((listener) -> listener.change(this.value, newValue));
		}
		//Call all JavaFx change listeners to alert bounded Properties that the value changed
		notifyJavaFxListeners(newValue);
		//Push the old value into the GlobalMetaChangeCache
		GlobalMetaChangeCache.getInstance().push(new MetaChangeRecord<T>(this, this.value));
		//Push the old value into the MetaChangeCache
		if(this.hasChangeCache()) this.changeCache.push(this.value);
		
		this.value = newValue;
	}
	
	/**
	 * Gets the current value of this Meta object
	 * 
	 * @return Current value
	 */
	@Override
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
		metaJSON.put("userAccess", userAccess.toString());
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

	private ArrayList<InvalidationListener> invalidationListeners = new ArrayList<>();
	private ArrayList<ChangeListener<? super T>> changeListeners = new ArrayList<>();
	
	protected void notifyJavaFxListeners(T newValue) {
		changeListeners.forEach((l) -> l.changed(this, this.value, newValue));
	}
	
	@Override
	public void addListener(InvalidationListener arg0) {
		invalidationListeners.add(arg0);
	}

	@Override
	public void removeListener(InvalidationListener arg0) {
		invalidationListeners.remove(arg0);
	}

	@Override
	public void addListener(ChangeListener<? super T> arg0) {
		changeListeners.add(arg0);
	}

	@Override
	public void removeListener(ChangeListener<? super T> arg0) {
		changeListeners.remove(arg0);
	}
	
}
