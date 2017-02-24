package com.kneecapdev.jlogix.options.gui;

import com.kneecapdev.jlogix.api.lang.LanguageBindings;
import com.kneecapdev.jlogix.utils.AssetManager;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LogixOptionsHeader extends AnchorPane{
	
	public LogixOptionsHeader(){
		Label headerLabel = new Label("Options");
		LanguageBindings.bind("label_options", headerLabel);
		headerLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 30));
		headerLabel.setTextFill(Paint.valueOf("#8EC536"));

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
