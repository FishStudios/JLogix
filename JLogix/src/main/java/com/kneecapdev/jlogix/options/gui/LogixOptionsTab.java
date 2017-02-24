package com.kneecapdev.jlogix.options.gui;

import com.kneecapdev.jlogix.api.config.ConfigManager;
import com.kneecapdev.jlogix.api.lang.LanguageBindings;
import com.kneecapdev.jlogix.utils.AssetManager;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LogixOptionsTab extends Tab{	
	public VBox content;
	
	public LogixOptionsTab(String name) {
		this(name, AssetManager.getInstance().getImage("settings.png"));
		
	}
	
	public LogixOptionsTab(String name, Image icon) {
		ImageView iconView = new ImageView(icon);
		iconView.setFitHeight(48);
		iconView.setFitWidth(48);
		
		StackPane stp = new StackPane(new Group(iconView));
		stp.setRotate(90);
		this.setGraphic(stp);
		
		Label label = new Label("name");
		LanguageBindings.bind("label_options_"+name.toLowerCase(), label);
		label.setStyle("-fx-font-size: 20pt; -fx-font-family:'Segoe UI Semibold'; fx-text-fill: black;  ");
		
		content = new VBox();
		content.setPadding(new Insets(25,50,50,25));
		content.setSpacing(10);
		content.getChildren().add(label);
		this.setContent(content);
		
	}	
	
	public void addContent(Node n){
		this.content.getChildren().add(n);
		
	}
	
	public CheckBox addCheckbox(String configName, String configKey, String caption){
		LanguageBindings.bind("caption", content);
		CheckBox cbox = new CheckBox(caption);
		
		
		cbox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, String.valueOf(cbox.isSelected()), configName);
			
		});
		
		addContent(cbox);
		return cbox;
	}
	
	public CheckBox addCheckbox(String configName, String configKey){
		CheckBox cbox = new CheckBox();
		
		cbox.setSelected(ConfigManager.getInstance().getBoolean(configKey,configName));
		cbox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, String.valueOf(cbox.isSelected()), configName);
			
		});
		
		addContent(cbox);
		return cbox;
	}
	
	public TextField addTextField(String configName, String configKey, String caption){
		LanguageBindings.bind("caption", content);
		TextField txt = new TextField(caption);
		
		txt.setText(ConfigManager.getInstance().getString(configKey, configName));
		txt.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, txt.getText(), configName);
			
		});
		
		addContent(txt);
		return txt;
	}
	
	public TextField addTextField(String configName, String configKey){
		TextField txt = new TextField();
		
		txt.setText(ConfigManager.getInstance().getString(configKey, configName));
		txt.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, txt.getText(), configName);
			
		});
		
		addContent(txt);
		return txt;
	}
	
	public ListView<String> addList(String configName, String configKey, String[] values, String caption){
		LanguageBindings.bind("caption", content);
		ListView<String> list = new ListView<>(FXCollections.observableArrayList(values));
		
		//TODO changing config Values
		
		content.getChildren().addAll(new Label(caption),list);
		return list;
	}
	
	public ListView<String> addList(String configName, String configKey, String[] values){
		ListView<String> list = new ListView<>(FXCollections.observableArrayList(values));
		
		//TODO changing config Values
		
		addContent(list);
		return list;
	}
	
	public ListView<int[]> addList(String configName, String configKey, int[] values, String caption){
		LanguageBindings.bind("caption", content);
		ListView<int[]> list = new ListView<>(FXCollections.observableArrayList(values));
		
		//TODO changing config Values
		
		content.getChildren().addAll(new Label(caption), list);
		return list;
	}
	
	public ListView<int[]> addList(String configName, String configKey, int[] values){
		ListView<int[]> list = new ListView<>(FXCollections.observableArrayList(values));
		
		//TODO changing config Values
		
		addContent(list);
		return list;
	}
	
	public ComboBox<String> addCombobox(String configName, String configKey, String[] values, String caption){
		LanguageBindings.bind("caption", content);
		ComboBox<String> cmbBox = new ComboBox<>(FXCollections.observableArrayList(values));
		
		cmbBox.setValue(ConfigManager.getInstance().getString(configKey, configName));
		cmbBox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, cmbBox.getValue(), configName);
			
		});
		
		content.getChildren().addAll(new Label(caption), cmbBox);
		return cmbBox;
	}
	
	public ComboBox<String> addCombobox(String configName, String configKey, String[] values){
		ComboBox<String> cmbBox = new ComboBox<>(FXCollections.observableArrayList(values));
		
		cmbBox.setValue(ConfigManager.getInstance().getString(configKey, configName));
		cmbBox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, cmbBox.getValue(), configName);
			
		});
		
		addContent(cmbBox);
		return cmbBox;
		
	}
	
	public ComboBox<Integer> addCombobox(String configName, String configKey, Integer[] values, String caption){
		LanguageBindings.bind("caption", content);
		ComboBox<Integer> cmbBox = new ComboBox<>(FXCollections.observableArrayList(values));
		
		cmbBox.setValue(ConfigManager.getInstance().getInt(configKey, configName));
		cmbBox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, cmbBox.getValue().toString(), configName);
			
		});
		
		content.getChildren().addAll(new Label(caption), cmbBox);
		return cmbBox;
	}
	
	public ComboBox<Integer> addCombobox(String configName, String configKey, Integer[] values){
		ComboBox<Integer> cmbBox = new ComboBox<>(FXCollections.observableArrayList(values));
		
		cmbBox.setValue(ConfigManager.getInstance().getInt(configKey, configName));
		cmbBox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, cmbBox.getValue().toString(), configName);
			
		});
		
		addContent(cmbBox);
		return cmbBox;
		
	}
	
	public ColorPicker addColorpicker(String configName, String configKey, String caption){
		ColorPicker picker = new ColorPicker(Color.web(ConfigManager.getInstance().getString(configKey, configName)));
		picker.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, "#" + picker.getValue().hashCode(), configName);
			
		});
		
		content.getChildren().addAll(new Label(caption), picker);
		return picker;
		
	}
	
	public ColorPicker addColorpicker(String configName, String configKey){
		ColorPicker picker = new ColorPicker(Color.web(ConfigManager.getInstance().getString(configKey, configName)));
		picker.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, "#" + picker.getValue().hashCode(), configName);
			
		});
		
		addContent(picker);
		return picker;
	}
}
