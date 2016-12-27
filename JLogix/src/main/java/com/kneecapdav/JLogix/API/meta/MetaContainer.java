package com.kneecapdav.JLogix.API.meta;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class MetaContainer {

	public ArrayList<Meta> metaData = new ArrayList<>();
	
	/**
	 * Adds a Meta object to this container
	 * 
	 * @param data object
	 */
	public void add(Meta data) {
		metaData.add(data);
	}
	
	/**
	 * Removes a Meta object from this container
	 * 
	 * @param uuid UUID of the represented MetaData object
	 */
	public void remove(String uuid) {
		remove(UUID.fromString(uuid));
	}
	
	/**
	 * Removes a Meta object from this container
	 * 
	 * @param uuid of the represented MetaData object
	 */
	public void remove(UUID uuid) {
		Meta data = get(uuid);
		if(data != null) metaData.remove(data);
	}
	
	/**
	 * Removes a Meta object from this container
	 * 
	 * @param meta object
	 */
	public void remove(Meta meta) {
		metaData.remove(meta);
	}
	
	/**
	 * Checks if the given Meta object is added in this container
	 * 
	 * @param meta object
	 * @return boolean describing if this container contains the given Meta object
	 */
	public boolean contains(Meta meta) {
		return metaData.contains(meta);
	}
	
	/**
	 * Checks if the given Meta object is added in this container
	 * 
	 * @param uuid UUID of the represented Meta object
	 * @return boolean describing if this container contains the given Meta object
	 */
	public boolean contains(String uuid) {
		return contains(UUID.fromString(uuid));
	}
	
	/**
	 * Checks if the given Meta object is added in this container
	 * 
	 * @param uuid of the represented Meta object
	 * @return boolean
	 */
	public boolean contains(UUID uuid) {
		ArrayList<Meta> result = metaData.stream().filter((data) -> data.uuid.toString().equals(uuid.toString())).collect(Collectors.toCollection(ArrayList::new));
		return !result.isEmpty();
	}
	
	/**
	 * Gets the Meta with the given UUID of this container
	 * 
	 * @param uuid UUID of the represented Meta object
	 * @return Meta object
	 */
	public Meta get(String uuid) {
		return get(UUID.fromString(uuid));
	}
	
	/**
	 * Gets the Meta with the given UUID of this container
	 * 
	 * @param uuid of the represented Meta object
	 * @return Meta object
	 */	
	public Meta get(UUID uuid) {
		ArrayList<Meta> result = metaData.stream().filter((data) -> data.uuid.toString().equals(uuid.toString())).collect(Collectors.toCollection(ArrayList::new));
		if(result.isEmpty()) return null;
		else return result.get(0);
	}
	
}
