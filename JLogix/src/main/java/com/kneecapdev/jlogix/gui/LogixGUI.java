package com.kneecapdev.jlogix.gui;

import com.kneecapdev.jlogix.api.config.ConfigManager;
import com.kneecapdev.jlogix.api.events.EventManager;
import com.kneecapdev.jlogix.api.events.EventState;
import com.kneecapdev.jlogix.api.lang.LanguageManager;
import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.api.module.loader.ModuleManager;
import com.kneecapdev.jlogix.console.LogixConsole;
import com.kneecapdev.jlogix.gui.editor.EditorView;
import com.kneecapdev.jlogix.gui.events.GUICloseEvent;
import com.kneecapdev.jlogix.gui.events.GUICreateEvent;
import com.kneecapdev.jlogix.gui.project.selector.ProjectSelectorView;
import com.kneecapdev.jlogix.gui.view.GUIManager;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogixGUI extends Application {

	public static final String TITLE = "Logix";
	public static final String VERSION = "InDev-1.0.0";
	public static final int COPYRIGHT_YEAR = 2017;
	
	public static LogixGUI instance;
	
	public Stage stage;
	
	public static void main(String[] args) {
		LanguageManager.getInstance();
		
		ModuleManager.getInstance().moduleLoader.loadAll();
		ModuleManager.getInstance().enableAll();
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		stage = primaryStage;
		
		stage.getIcons().add(new Image(LogixGUI.class.getResourceAsStream("/images/logix_logo.png")));
		
		LogixLogger.debug(this, "[GUI] PRE load");
		EventManager.getInstance().fire(new GUICreateEvent(this, EventState.PRE));
		ConfigManager.getInstance();
		
		
		primaryStage.setTitle(TITLE + "-" + VERSION + "-" + COPYRIGHT_YEAR);
		
		primaryStage.show();
		
		primaryStage.setOnCloseRequest((event) -> {
			LogixConsole.getInstance().terminate();
			EventManager.getInstance().fire(new GUICloseEvent(LogixGUI.this));
		});
		
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
		GUIManager.getInstance().register(new EditorView());
		
		GUIManager.getInstance().changeView("editor");
	}
	
}
