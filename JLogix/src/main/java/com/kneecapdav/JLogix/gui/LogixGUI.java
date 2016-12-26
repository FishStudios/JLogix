package com.kneecapdav.JLogix.gui;

import com.kneecapdav.JLogix.API.events.EventManager;
import com.kneecapdav.JLogix.API.events.EventState;
import com.kneecapdav.JLogix.API.log.LogixLogger;
import com.kneecapdav.JLogix.API.module.loader.ModuleManager;
import com.kneecapdav.JLogix.gui.events.GUICloseEvent;
import com.kneecapdav.JLogix.gui.events.GUICreateEvent;
import com.kneecapdav.JLogix.gui.project.selector.ProjectSelectorView;
import com.kneecapdav.JLogix.gui.view.GUIManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class LogixGUI extends Application {

	public static final String TITLE = "Logix";
	public static final String VERSION = "InDev-1.0.0";
	public static final int COPYRIGHT_YEAR = 2017;
	
	public static LogixGUI instance;
	
	public Stage stage;
	
	public static void main(String[] args) {
		ModuleManager.getInstance().moduleLoader.loadAll();
		ModuleManager.getInstance().enableAll();
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		stage = primaryStage;
		
		LogixLogger.debug(this, "[GUI] PRE load");
		EventManager.getInstance().fire(new GUICreateEvent(this, EventState.PRE));
		
		primaryStage.setTitle(TITLE + "-" + VERSION + "-" + COPYRIGHT_YEAR);
		
		primaryStage.show();
		
		primaryStage.setOnCloseRequest((event) -> EventManager.getInstance().fire(new GUICloseEvent(LogixGUI.this)));
		
		loadViews();
		
		LogixLogger.debug(this, "[GUI] POST load");
		EventManager.getInstance().fire(new GUICreateEvent(this, EventState.POST));
	}
	
	private void loadViews() {
		GUIManager.getInstance().register(new ProjectSelectorView());
		
		GUIManager.getInstance().changeView("projectSelector");
	}
	
}
