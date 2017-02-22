package com.kneecapdev.jlogix.utils;

import java.util.HashMap;

import com.kneecapdev.jlogix.gui.LogixGUI;

import javafx.scene.image.Image;

public class AssetManager {

	private static AssetManager instance;
	
	private HashMap<String, Image> images;
	
	private AssetManager() {
		images = new HashMap<>();
	}
	
	public Image getImage(String name) {
		if(images.containsKey(name)) {
			return images.get(name);
		} else {
			Image img = new Image(LogixGUI.class.getResourceAsStream("/images/" + name));
			images.put(name, img);
			return img;
		}
	}
	
	public void clear() {
		images.clear();
	}
	
	public static AssetManager getInstance() {
		if(instance == null) instance = new AssetManager();
		return instance;
	}
	
}
