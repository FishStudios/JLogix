package com.kneecapdav.JLogix.gui.project.selector;

import com.kneecapdav.JLogix.gui.view.GUIView;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ProjectSelectorView extends GUIView {

	private AnchorPane root;
	private Scene scene;
	
	@Override
	public void onShow() {
		Label label = new Label("Project Selector");
		root.getChildren().add(label);
		
		AnchorPane.setBottomAnchor(label, 20D);
		AnchorPane.setTopAnchor(label, 20D);
		AnchorPane.setLeftAnchor(label, 750D);
		AnchorPane.setRightAnchor(label, 750D);
	}

	@Override
	public void onHide() {
	}
	
	@Override
	public void onInit() {
		root = new AnchorPane();
		root.setMinSize(1600, 900);
		root.setMaxSize(1600, 900);
		scene = new Scene(root);
	}

	@Override
	public String getID() {
		return "projectSelector";
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
