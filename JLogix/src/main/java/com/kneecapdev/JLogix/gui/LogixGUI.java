package com.kneecapdev.JLogix.gui;

import com.kneecapdev.JLogix.API.config.ConfigManager;
import com.kneecapdev.JLogix.API.events.EventManager;
import com.kneecapdev.JLogix.API.events.EventState;
import com.kneecapdev.JLogix.API.log.LogixLogger;
import com.kneecapdev.JLogix.API.module.loader.ModuleManager;
import com.kneecapdev.JLogix.gui.events.GUICloseEvent;
import com.kneecapdev.JLogix.gui.events.GUICreateEvent;
import com.kneecapdev.JLogix.gui.project.selector.ProjectSelectorView;
import com.kneecapdev.JLogix.gui.view.GUIManager;

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
		ConfigManager.getInstance();
		
		primaryStage.setTitle(TITLE + "-" + VERSION + "-" + COPYRIGHT_YEAR);
		
		primaryStage.show();
		
		primaryStage.setOnCloseRequest((event) -> EventManager.getInstance().fire(new GUICloseEvent(LogixGUI.this)));
		
		loadViews();
		
		LogixLogger.debug(this, "[GUI] POST load");
		EventManager.getInstance().fire(new GUICreateEvent(this, EventState.POST));
		
		/*boolean save = true;

		if(save) {

			LogixProject project = ProjectManager.getInstance().createNewProject("TestProject");
			LogixCanvas canvas = project.createNewCanvas("TestCanvas");

			GateAND and = new GateAND();
			and.addConnector(new LogixConnector("Connector1", Type.INPUT));
			and.addConnector(new LogixConnector("Connector2", Type.INPUT));
			and.addConnector(new LogixConnector("Connector3", Type.OUTPUT));

			canvas.add(and);

			project.save();
		} else {

			LogixProject project = ProjectManager.getInstance().getProject("TestProject");
			
			project.load();
			
			LogixCanvas canvas = project.getCanvas("TestCanvas");

			for(Element e: canvas.elements) {
				if(!(e instanceof LogixComponent)) continue;
				LogixComponent c = (LogixComponent) e;
				
				System.out.println(c.getInputCount() + ", " + c.getOutputCount());
			}

		}*/
	}
	
	private void loadViews() {
		GUIManager.getInstance().register(new ProjectSelectorView());
		
		GUIManager.getInstance().changeView("projectSelector");
	}
	
}
