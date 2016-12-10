package com.kneecapdav.JLogix.API.meta;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kneecapdav.JLogix.API.meta.MetaValue.MetaAccess;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaType;

/**
 * Stores and manage MetaValue objects.
 * 
 * @author Dominik
 *
 */
public class Meta implements Cloneable {

	public UUID uuid;
	
	public ArrayList<MetaValue<?>> metaValues;
	
	/**
	 * Creates new Meta object.
	 */
	public Meta() {
		metaValues = new ArrayList<>();
		uuid = UUID.randomUUID();
	}
	
	/**
	 * Gets all MetaValue objects of this Meta object with the given id.
	 * 
	 * @param Meta ID
	 * @return List of all found MetaValues with the given ID.
	 */
	public ArrayList<MetaValue<?>> get(String meta) {
		return metaValues.stream().filter((m) -> m.id.equalsIgnoreCase(meta)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * 
	 * Gets the first of all found MetaValue objects of this MetaData object with the given ID.
	 * 
	 * @param Data type
	 * @param Meta ID
	 * @return First found MetaValue object with the given ID.
	 */
	@SuppressWarnings("unchecked")
	public <T> MetaValue<T> getFirst(Class<T> t, String meta) {
		ArrayList<MetaValue<?>> result = metaValues.stream().filter((m) -> m.id.equalsIgnoreCase(meta)).collect(Collectors.toCollection(ArrayList::new));
		if(result.isEmpty()) return null;
		else return (MetaValue<T>) result.get(0);
	}
	
	/**
	 * 
	 * Gets the first of all found MetaValue objects of this MetaData object with the given ID.
	 * 
	 * @param Data type
	 * @param Meta ID
	 * @return First found MetaValue object with the given ID.
	 */
	@SuppressWarnings("unchecked")
	public <T> MetaValue<T> getFirst(String meta) {
		ArrayList<MetaValue<?>> result = metaValues.stream().filter((m) -> m.id.equalsIgnoreCase(meta)).collect(Collectors.toCollection(ArrayList::new));
		if(result.isEmpty()) return null;
		else return (MetaValue<T>) result.get(0);
	}
	
	/**
	 * 
	 * Gets all MetaValue object of this Meta object which starts with the given ID.
	 * 
	 * @param Meta ID to start with
	 * @return List of all found MetaValues which starts with the given ID.
	 */
	public ArrayList<MetaValue<?>> startsWith(String meta) {
		return metaValues.stream().filter((m) -> m.id.startsWith(meta)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Adds new MetaValue object to this collection.
	 * 
	 * @param MetaValue object
	 */
	public void add(MetaValue<?> meta) {
		metaValues.add(meta);
	}
	
	/**
	 * Adds multiple MetaValue objects to this collection.
	 * 
	 * @param MetaValue objects
	 */
	public void addAll(MetaValue<?>... meta) {
		for(int i = 0; i < meta.length; i++) metaValues.add(meta[i]);
	}
	
	/**
	 * Adds multiple MetaValue objects to this collection.
	 * 
	 * @param MetaValue objects
	 */
	public void addAll(ArrayList<MetaValue<?>> meta) {
		metaValues.addAll(meta);
	}
	
	
	/**
	 * Removes MetaValue object from this collection.
	 * 
	 * @param MetaValue object
	 */
	public void remove(MetaValue<?> meta) {
		metaValues.add(meta);
	}

	/**
	 * 
	 * @param Meta ID
	 * @return Returns true if the given MetaValue is added in this collection.
	 */
	public boolean contains(String id) {
		return !this.get(id).isEmpty();
	}
	
	/**
	 * 
	 * @param MetaValue object
	 * @return Returns true if the given MetaValue is added in this collection.
	 */
	public boolean contains(MetaValue<?> meta) {
		return this.metaValues.contains(meta);
	}
		
	/**
	 * Adds all MetaValue objects of the given MetaData to this collection.
	 * 
	 * @param Meta object
	 */
	public void copyFromMeta(Meta data) {
		metaValues.addAll(data.metaValues);
	}
	
	/**
	 * Adds all MetaValue objects of the given MetaData to this Collection.
	 * 
	 * @param Meta objects
	 */
	public void copyFromMetaAll(Meta... data) {
		for(int i = 0; i < data.length; i++) this.copyFromMeta(data[i]);
	}
	
	
	
	
	
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param type MetValue data type
	 * @param metaID MetaValue ID
	 * @param access MetaValue access level
	 */
	public <T> MetaValue<T> addDefault(MetaType type, String metaID, MetaAccess access) {
		return new MetaValue<T>(type, metaID, access).addTo(this);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param type MetValue data type
	 * @param metaID MetaValue ID
	 */
	public <T> MetaValue<T> addDefault(MetaType type, String metaID) {
		return this.addDefault(type, metaID, MetaAccess.READ_WRITE);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Boolean> newBoolean(String metaID) {
		return this.addDefault(MetaType.BOOLEAN, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Byte> newByte(String metaID) {
		return this.addDefault(MetaType.BYTE, metaID);	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Short> newShort(String metaID) {
		return this.addDefault(MetaType.SHORT, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Integer> newInteger(String metaID) {
		return this.addDefault(MetaType.INTEGER, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Long> newLong(String metaID) {
		return this.addDefault(MetaType.LONG, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Float> newFloat(String metaID) {
		return this.addDefault(MetaType.FLOAT, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Double> newDouble(String metaID) {
		return this.addDefault(MetaType.DOUBLE, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<String> newString(String metaID) {
		return this.addDefault(MetaType.STRING, metaID);
	}
	/**
	 * Creates a new empty MetaValue instance for this Meta
	 * @param metaID MetaValue ID
	 */
	public MetaValue<Meta> newMeta(String metaID) {
		return this.addDefault(MetaType.META, metaID);
	}

	
	
	
	/**
	 * Prints the Meta content
	 */
	public void print() {
		System.out.println("~~~~~~~ Meta ~~~~~~~");
		for(MetaValue<?> meta: this.metaValues) {
			if(meta.type == MetaType.META) ((Meta)meta.getValue()).print();
			else System.out.println("ID: " + meta.id + " Type: " + meta.type.toString() + " Value: " + meta.getValue());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
	}
	
	/**
	 * Saves the Meta object to a JSONArray
	 * @return JSONArray that includes all the MetaValues
	 */
	@SuppressWarnings("unchecked")
	public JSONArray saveToJSON() {
		JSONArray array = new JSONArray();
		
		for(MetaValue<?> meta: this.metaValues) {
			array.add(meta.saveToJSON());
		}
		
		return array;
	}
	
	/**
	 * Loads the Meta object from a JSONArray
	 * @return Content
	 */
	@SuppressWarnings("unchecked")
	public void loadFromJSON(JSONArray jsonMeta) {
		ArrayList<Object> content = (ArrayList<Object>) jsonMeta.stream().collect(Collectors.toCollection(ArrayList::new));
		
		for(Object obj: content) {
			JSONObject jObj = (JSONObject) obj;
			
			try {
				switch(MetaType.getMetaType(jObj.get("value"))) {
				case BOOLEAN:
					new MetaValue<Boolean>(jObj).addTo(this);
					break;
				case BYTE:
					new MetaValue<Byte>(jObj).addTo(this);
					break;
				case DOUBLE:
					new MetaValue<Double>(jObj).addTo(this);
					break;
				case INTEGER:
					new MetaValue<Integer>(jObj).addTo(this);
					break;
				case LONG:
					new MetaValue<Long>(jObj).addTo(this);
					break;
				case SHORT:
					new MetaValue<Short>(jObj).addTo(this);
					break;
				case STRING:
					new MetaValue<String>(jObj).addTo(this);
					break;
				case FLOAT:
					new MetaValue<Float>(jObj).addTo(this);
					break;
				case META:
					new MetaValue<Meta>(jObj).addTo(this);
					break;
				}
			} catch (MetaTypeException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Creates an exact copy of this MetaData object within its MetaValues.
	 */
	@Override
	public Meta clone() {
		Meta meta = new Meta();
		for(MetaValue<?> value: this.metaValues) {
			meta.add(value.clone());
		}
		return meta;
	}
	
}
