package com.kneecapdev.JLogix.gui.controls.projecttreeview;

import com.kneecapdev.JLogix.API.project.LogixProject;
import com.kneecapdev.JLogix.API.project.ProjectManager;

import javafx.scene.control.ListView;

public class ProjectListView extends ListView<LogixProject> {

    public ProjectListView() {
        this.getStyleClass().add("project-list-view");
        this.setCellFactory(new ProjectListViewCellFactory());
        this.update();
    }

    public void update() {
        getItems().clear();
        getItems().addAll(ProjectManager.getInstance().projects);
    }

}
