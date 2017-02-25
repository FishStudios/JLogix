package com.kneecapdev.jlogix.options.gui;

import com.kneecapdev.jlogix.api.lang.LanguageBindings;
import com.kneecapdev.jlogix.utils.AssetManager;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LogixOptionsHeader extends AnchorPane{
	
	public LogixOptionsHeader(){
		Label headerLabel = new Label("Options");
		LanguageBindings.bind("label_options", headerLabel);
		headerLabel.setId("header");
		
		this.setMinSize(500, 64);
		this.setStyle(
			      "-fx-background-color: #232D31;"
				+ "-fx-effect: dropshadow(three-pass-box, black, 5, 0, 0, 2);"
				 
				);
		this.setMaxHeight(64);
				
		ImageView icon = new ImageView(AssetManager.getInstance().getImage("settings.png"));
		icon.setFitHeight(48);
		icon.setFitWidth(48);
		
		this.getChildren().addAll(headerLabel, icon);
		
		AnchorPane.setBottomAnchor(headerLabel, 16.0);
		AnchorPane.setTopAnchor(headerLabel, 16.0);
		AnchorPane.setLeftAnchor(headerLabel, 16.0);
		
		AnchorPane.setBottomAnchor(icon, 8.0);
		AnchorPane.setTopAnchor(icon, 8.0);
		AnchorPane.setRightAnchor(icon, 8.0);

		
	}	
}
