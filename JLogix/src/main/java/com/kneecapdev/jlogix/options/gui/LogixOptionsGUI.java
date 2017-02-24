package com.kneecapdev.jlogix.options.gui;

import com.kneecapdev.jlogix.gui.LogixGUI;
import com.kneecapdev.jlogix.utils.AssetManager;

import javafx.geometry.Side;
import javafx.scene.Scene;
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
		rootPane.setSpacing(10);
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setSide(Side.LEFT);
		tabPane.setPrefSize(700, 800);
		tabPane.setStyle("fx-background-color: #F3F3F3;");
		
		//General
		LogixOptionsTab tabGeneral = new LogixOptionsTab("General",AssetManager.getInstance().getImage("option-general.png"));

		//Theme
		LogixOptionsTab tabTheme = new LogixOptionsTab("Theme", AssetManager.getInstance().getImage("option-theme.png"));
		
		tabTheme.addColorpicker("main", "colors.true", "colors_true");
		tabTheme.addColorpicker("main", "colors.false", "colors_false");
		tabTheme.addColorpicker("main", "colors.unknown", "colors_unknown");
		tabTheme.addColorpicker("main", "colors.error", "colors_error");
		tabTheme.addColorpicker("main", "colors.multiple", "colors.multiple");
		tabTheme.addColorpicker("main", "colors.incompatible", "colors.incompatible");
				
		//Language		
		LogixOptionsTab tabLanguage = new LogixOptionsTab("Language", AssetManager.getInstance().getImage("option-language.png"));
		
		
		//Development
		LogixOptionsTab tabDev = new LogixOptionsTab("Dev", AssetManager.getInstance().getImage("option-dev.png"));
		
		//Modules
		LogixOptionsTab tabModules = new LogixOptionsTab("Mod", AssetManager.getInstance().getImage("option-mod.png"));
		
		//IO
		LogixOptionsTab tabIO = new LogixOptionsTab("IO", AssetManager.getInstance().getImage("option-io.png"));
		
		//Simulation
		LogixOptionsTab tabSim = new LogixOptionsTab("Sim");
		
		tabSim.addTextField("main", "bitwidth.max");
		
		//TabPane
		tabPane.setTabMinHeight(100);
		tabPane.setTabMaxHeight(100);
		
		tabPane.getTabs().addAll(tabGeneral,tabTheme,tabLanguage,tabDev,tabModules,tabIO,tabSim);
		
		//RootPane
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
