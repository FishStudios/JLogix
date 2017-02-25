package com.kneecapdev.jlogix.options.gui;

import com.kneecapdev.jlogix.api.config.ConfigManager;
import com.kneecapdev.jlogix.api.lang.LanguageBindings;
import com.kneecapdev.jlogix.utils.AssetManager;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LogixOptionsTab extends Tab{	
	public VBox content;
	
	public LogixOptionsTab(String name) {
		this(name, AssetManager.getInstance().getImage("settings.png"));
		
	}
	
	public LogixOptionsTab(String name, Image icon) {
		Label title = new Label(name);
		LanguageBindings.bind("label_options_"+name.toLowerCase(), title);
		title.setId("title");

		ImageView iconView = new ImageView(icon);
		iconView.setFitHeight(32);
		iconView.setFitWidth(32);
				
		StackPane stp = new StackPane(new Group(iconView));
		stp.setRotate(90);
		this.setGraphic(stp);
		
		Tooltip tp = new Tooltip();
		tp.setText(name);
		LanguageBindings.bind("label_options_"+name.toLowerCase(), tp);
		this.setTooltip(tp);
		
		content = new VBox();
		content.setId("content");
		content.getChildren().add(title);
		
		this.setContent(content);
		addSeparator();
	}	
	
	public void addContent(Node n){
		this.content.getChildren().add(n);
		
	}
	
	public void addLabeledContent(Node n, String captionKey) {
		Label l = new Label(captionKey);
		LanguageBindings.bind(captionKey, l);
		
		AnchorPane row = new AnchorPane();
		row.getChildren().addAll(l,n);
		
		AnchorPane.setLeftAnchor(l, 10.0);
		AnchorPane.setRightAnchor(n, 10.0);
		
		addContent(row);
	}
	
	public CheckBox addCheckbox(String configName, String configKey, String captionKey){
		CheckBox cbox = new CheckBox();
		
		cbox.setSelected(ConfigManager.getInstance().getBoolean(configKey,configName));
		cbox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, String.valueOf(cbox.isSelected()), configName);
			
		});
		
		addLabeledContent(cbox, captionKey);
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
	
	public TextField addTextField(String configName, String configKey, String captionKey){
		TextField txt = new TextField();
		
		txt.setText(ConfigManager.getInstance().getString(configKey, configName));
		txt.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, txt.getText(), configName);
			
		});
		
		addLabeledContent(txt, captionKey);
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

	public ComboBox<String> addCombobox(String configName, String configKey, String[] values, String captionKey){
		ComboBox<String> cmbBox = new ComboBox<>(FXCollections.observableArrayList(values));
		
		cmbBox.setValue(ConfigManager.getInstance().getString(configKey, configName));
		cmbBox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, cmbBox.getValue(), configName);
			
		});
		
		addLabeledContent(cmbBox, captionKey);
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
	
	public ComboBox<Integer> addCombobox(String configName, String configKey, Integer[] values, String captionKey){
		ComboBox<Integer> cmbBox = new ComboBox<>(FXCollections.observableArrayList(values));
		
		cmbBox.setValue(ConfigManager.getInstance().getInt(configKey, configName));
		cmbBox.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, cmbBox.getValue().toString(), configName);
			
		});
		
		addLabeledContent(cmbBox, captionKey);
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
	
	public ColorPicker addColorpicker(String configName, String configKey, String captionKey){
		ColorPicker picker = new ColorPicker(Color.web(ConfigManager.getInstance().getString(configKey, configName)));
		picker.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, ConfigManager.toHashColor(picker.getValue()), configName);
			
		});
		
		addLabeledContent(picker, captionKey);
		return picker;
		
	}
	
	public ColorPicker addColorpicker(String configName, String configKey){
		ColorPicker picker = new ColorPicker(Color.web(ConfigManager.getInstance().getString(configKey, configName)));
		picker.setOnAction((e) -> {
			ConfigManager.getInstance().set(configKey, ConfigManager.toHashColor(picker.getValue()), configName);
			
		});
		
		addContent(picker);
		return picker;
	}
	
	public Separator addSeparator(){
		Separator sep = new Separator(Orientation.HORIZONTAL);
		
		addContent(sep);
		return sep;
	}
	
	public HBox addSeparator(String captionKey) {
		HBox labeledSeparator = new HBox();
		labeledSeparator.setSpacing(3);		
		
		Label l = new Label(captionKey);
		l.setId("h1");
		LanguageBindings.bind(captionKey, l);
		
		Separator leftSeparator = new Separator();
		HBox.setHgrow(leftSeparator, Priority.ALWAYS);
		Separator rightSeparator = new Separator();
		HBox.setHgrow(rightSeparator, Priority.ALWAYS);
		
		labeledSeparator.getChildren().addAll(leftSeparator,l,rightSeparator);
		labeledSeparator.setAlignment(Pos.CENTER);
		
		addContent(labeledSeparator);
		return labeledSeparator;
	}
	
	public Label addTitle(String captionKey){
		Label l = new Label(captionKey);
		l.setId("1");
		LanguageBindings.bind(captionKey, l);
		
		addContent(l);
		return l;
	}
}
