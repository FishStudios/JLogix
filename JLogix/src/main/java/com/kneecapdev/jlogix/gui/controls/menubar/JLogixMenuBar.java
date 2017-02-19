package com.kneecapdev.jlogix.gui.controls.menubar;

import com.kneecapdev.jlogix.api.lang.LanguageBindings;
import com.kneecapdev.jlogix.console.LogixConsole;
import com.kneecapdev.jlogix.gui.credits.AboutGUI;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class JLogixMenuBar extends MenuBar {

    private final JLogixMenuBarController controller = new JLogixMenuBarController(this);

    public JLogixMenuBar() {
        Menu file = new Menu("File");
        	LanguageBindings.bind("menu_file", file);
        Menu edit = new Menu("Edit");
    		LanguageBindings.bind("menu_edit", edit);
        Menu project = new Menu("Project");
    		LanguageBindings.bind("menu_project", project);
        Menu simulation = new Menu("Simulation");
    		LanguageBindings.bind("menu_simulation", simulation);
        Menu help = new Menu("Help");
    		LanguageBindings.bind("menu_help", help);
    		MenuItem about = new MenuItem("About");
    			LanguageBindings.bind("menuitem_about", about);
    		about.setOnAction((e) -> {
    			new AboutGUI();
    		});
    	help.getItems().add(about);
        Menu tools = new Menu("Tools");
    		LanguageBindings.bind("menu_tools", tools);
        	MenuItem console = new MenuItem("Console");
        		LanguageBindings.bind("menuitem_console", console);
        	console.setOnAction((e) -> {
        		LogixConsole.getInstance().toggle();
        	});
        	console.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        tools.getItems().add(console);

        this.getMenus().addAll(file, edit, project, simulation, help, tools);
    }

}
