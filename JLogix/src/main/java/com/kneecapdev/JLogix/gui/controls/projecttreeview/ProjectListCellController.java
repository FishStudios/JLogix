package com.kneecapdev.JLogix.gui.controls.projecttreeview;

import com.kneecapdev.JLogix.API.project.LogixProject;
import com.kneecapdev.JLogix.API.project.ProjectManager;
import com.kneecapdev.JLogix.gui.controller.AbstractController;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ProjectListCellController extends AbstractController<ListCell<LogixProject>> {

    public EventHandler<MouseEvent> onClick = new OnClickHandler();

    public ProjectListCellController(ListCell<LogixProject> node) {
        super(node);
    }

    private class OnClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                LogixProject project = node.getItem();
                ProjectManager.getInstance().switchProject(project, ProjectManager.getInstance().hasOpenProject());
                // TODO switch view
            }
        }

    }

}
