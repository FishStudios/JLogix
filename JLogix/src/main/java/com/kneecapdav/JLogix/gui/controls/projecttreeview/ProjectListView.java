package com.kneecapdav.JLogix.gui.controls.projecttreeview;

import com.kneecapdav.JLogix.API.project.LogixProject;
import com.kneecapdav.JLogix.API.project.ProjectManager;
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
