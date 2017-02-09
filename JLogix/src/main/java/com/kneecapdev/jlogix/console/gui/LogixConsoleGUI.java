package com.kneecapdev.jlogix.console.gui;

import com.kneecapdev.jlogix.console.commands.CommandParser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogixConsoleGUI {

	private Stage stage;
	
	private boolean isOpen;
	
	private VBox rootPane;
	
	private TextArea area;
	private TextField field;
	
	public LogixConsoleGUI() {
		stage = new Stage();
		stage.setTitle("JLogix Console");
		
		stage.setOnCloseRequest((e) -> {
			stage.hide();
			isOpen = false;
		});
		
		rootPane = new VBox();
		rootPane.setPrefSize(600, 400);
		Scene scene = new Scene(rootPane);
		
		area = new TextArea();
		area.setEditable(false);
		area.textProperty().addListener(new ChangeListener<Object>() {
		    @Override
		    public void changed(ObservableValue<?> observable, Object oldValue,
		            Object newValue) {
		    	area.setScrollTop(Double.MAX_VALUE);
		    }
		});
		VBox.setVgrow(area, Priority.ALWAYS);
		
		field = new TextField();
		field.setOnAction((e) -> {
			if(field.getText() != null && !field.getText().equalsIgnoreCase("")) {
				CommandParser.getInstance().parse(field.getText());
				field.setText("");
			}
		});
		
		rootPane.getChildren().add(area);
		rootPane.getChildren().add(field);
		
		field.requestFocus();

		stage.setScene(scene);
	}
	
	public boolean isOpen() {
		return this.isOpen;
	}
	
	public void show() {
		if(isOpen) return;
		stage.show();
		isOpen = true;
	}
	
	public void hide() {
		if(!isOpen) return;
		stage.hide();
		isOpen = false;
	}
	
	public void terminate() {
		stage.close();
	}
	
	public TextArea getTextArea() {
		return this.area;
	}
	
}
