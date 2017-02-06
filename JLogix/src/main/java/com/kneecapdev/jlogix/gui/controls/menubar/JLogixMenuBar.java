package com.kneecapdev.jlogix.gui.controls.menubar;

import com.kneecapdev.jlogix.console.LogixConsole;

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
        Menu edit = new Menu("Edit");
        Menu project = new Menu("Project");
        Menu simulation = new Menu("Simulation");
        Menu help = new Menu("Help");
        Menu tools = new Menu("Tools");
        	MenuItem console = new MenuItem("Console");
        	console.setOnAction((e) -> {
        		LogixConsole.getInstance().toggle();
        	});
        	console.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        tools.getItems().add(console);

        this.getMenus().addAll(file, edit, project, simulation, help, tools);
    }

}
