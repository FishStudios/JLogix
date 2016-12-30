package com.kneecapdev.JLogix.gui.controls.menubar;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;

public class JLogixMenuBar extends MenuBar {

    private final JLogixMenuBarController controller = new JLogixMenuBarController(this);

    public JLogixMenuBar() {
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu project = new Menu("Project");
        Menu simulation = new Menu("Simulation");
        Menu help = new Menu("Help");
        Menu tools = new Menu("Tools");

        this.getMenus().addAll(file, edit, project, simulation, help, tools);
    }

}
