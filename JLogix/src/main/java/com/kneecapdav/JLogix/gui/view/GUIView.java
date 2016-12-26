package com.kneecapdav.JLogix.gui.view;

import javafx.scene.Scene;

public abstract class GUIView {

	public void onInit() {};
	
	public abstract void onShow();
	public abstract void onHide();
	
	public abstract Scene getScene();
	
	public abstract String getID();
	
}
