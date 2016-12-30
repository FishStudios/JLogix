package com.kneecapdev.JLogix.gui.controls.projecttreeview;

import com.kneecapdev.JLogix.API.project.LogixProject;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ProjectListViewCellFactory implements Callback<ListView<LogixProject>, ListCell<LogixProject>> {

    @Override
    public ListCell<LogixProject> call(ListView<LogixProject> param) {
        return new ListCell<LogixProject>() {

            @Override
            protected void updateItem(LogixProject project, boolean empty) {
                super.updateItem(project, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setText(project.getName());
                }
            }

        };
    }

}
