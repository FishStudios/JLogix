package com.kneecapdev.jlogix.options.gui;

import java.io.File;

import com.kneecapdev.jlogix.api.lang.Language;
import com.kneecapdev.jlogix.api.lang.LanguageBindings;
import com.kneecapdev.jlogix.api.lang.LanguageManager;
import com.kneecapdev.jlogix.gui.LogixGUI;
import com.kneecapdev.jlogix.utils.AssetManager;
import com.kneecapdev.jlogix.utils.FileUtils;

import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogixOptionsGUI {
private Stage stage;
	
	private boolean isOpen;
	
	private VBox rootPane;
		
	public LogixOptionsGUI() {
		stage = new Stage();
		stage.getIcons().add(new Image(LogixGUI.class.getResourceAsStream("/images/logix_logo.png")));
		stage.setTitle("JLogix Options");
		stage.setMinWidth(500);
		stage.setMinHeight(600);
		
		stage.setOnCloseRequest((e) -> {
			stage.hide();
			isOpen = false;
		});
		
		rootPane = new VBox();
		rootPane.getStylesheets().add("stylesheets/default_frameOptions.css");
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setSide(Side.LEFT);
		
		/*
		 * =======TABS====== * 
		 */
		
		//General
		LogixOptionsTab tabGeneral = new LogixOptionsTab("General",AssetManager.getInstance().getImage("option-general.png"));
		
		//Theme
		LogixOptionsTab tabTheme = new LogixOptionsTab("Theme", AssetManager.getInstance().getImage("option-theme.png"));
		
		tabTheme.addSeparator("states_colors");
		tabTheme.addColorpicker("main", "colors.true", "colors_true");
		tabTheme.addColorpicker("main", "colors.false", "colors_false");
		tabTheme.addColorpicker("main", "colors.unknown", "colors_unknown");
		tabTheme.addColorpicker("main", "colors.error", "colors_error");
		tabTheme.addColorpicker("main", "colors.multiple", "colors_multiple");
		tabTheme.addColorpicker("main", "colors.incompatible", "colors_incompatible");
				
		//Language		
		LogixOptionsTab tabLanguage = new LogixOptionsTab("Language", AssetManager.getInstance().getImage("option-language.png"));
		
		ComboBox<Language> cmbBoxLang = new ComboBox<>(FXCollections.observableArrayList(LanguageManager.getInstance().listLanguages()));
		cmbBoxLang.setValue(LanguageManager.getInstance().getCurrentLanguage());
		cmbBoxLang.setOnAction((e) -> {
			LanguageManager.getInstance().changeLanguage(cmbBoxLang.getValue().getID());
			
		});
		tabLanguage.addLabeledContent(cmbBoxLang, "lang_current");
		
		Button btnRefreshLang = new Button("btn_submit");
		LanguageBindings.bind("btn_submit", btnRefreshLang);
		btnRefreshLang.setOnAction((e)->{
			LanguageManager.getInstance().resetFiles();
			LanguageManager.getInstance().reload();
			
		});
		tabLanguage.addLabeledContent(btnRefreshLang, "lang_refresh");
				
		//Development
		LogixOptionsTab tabDev = new LogixOptionsTab("Dev", AssetManager.getInstance().getImage("option-dev.png"));
		
		Button btnDelLocal = new Button("del_local");
		LanguageBindings.bind("del_local", btnDelLocal);
		btnDelLocal.setOnAction((e) -> {
			FileUtils.deleteDirectory(new File(System.getenv("APPDATA") + "\\Logix"));
			
		});
		
		tabDev.addContent(btnDelLocal);
				
		//Simulation
		LogixOptionsTab tabSim = new LogixOptionsTab("Sim",AssetManager.getInstance().getImage("option-sim.png"));
		
		tabSim.addTextField("main", "bitwidth.max", "bitwidth_max");
		
		/*
		 * =======TABS-END====== * 
		 */
		
		//TabPane
		tabPane.getTabs().addAll(tabGeneral,tabSim,tabLanguage,tabTheme,tabDev);
		
		rootPane.getChildren().addAll(new LogixOptionsHeader(),tabPane);		
		VBox.setVgrow(tabPane, Priority.ALWAYS);
		
		Scene scene = new Scene(rootPane);
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
}
