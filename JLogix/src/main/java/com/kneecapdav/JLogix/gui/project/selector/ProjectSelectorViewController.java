package com.kneecapdav.JLogix.gui.project.selector;

import com.kneecapdav.JLogix.API.project.LogixProject;
import com.kneecapdav.JLogix.API.project.ProjectManager;
import com.kneecapdav.JLogix.gui.controller.AbstractViewController;
import com.kneecapdav.JLogix.gui.controls.projecttreeview.ProjectListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public class ProjectSelectorViewController extends AbstractViewController<ProjectSelectorView> {

    public EventHandler<ActionEvent> onCreateClick = new CreateClickHandler();
    public EventHandler<ActionEvent> onOpenClick = new OpenClickHandler();
    public EventHandler<ActionEvent> onDeleteClick = new DeleteClickHandler();

    public ProjectSelectorViewController(ProjectSelectorView control) {
        super(control);
    }

    private class CreateClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Enter Project Name");
            tid.setHeaderText("Project Creation");
            tid.setContentText("Please enter the desired name of the new project:");

            Optional<String> result = tid.showAndWait();
            result.ifPresent(name -> ProjectManager.getInstance().createNewProject(name));
            view.getProjectListView().update();
        }

    }

    private class OpenClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ProjectListView plw = view.getProjectListView();
            if(plw.getSelectionModel().getSelectedItems().size() == 1) {
                LogixProject project = view.getProjectListView().getSelectionModel().getSelectedItem();
            }
            // TODO load project & switch view
        }

    }

    private class DeleteClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ProjectListView plw = view.getProjectListView();
            if(plw.getSelectionModel().getSelectedItems().size() > 0) {
                List<LogixProject> projects = plw.getSelectionModel().getSelectedItems();
                for(LogixProject p : projects) {
                    ProjectManager.getInstance().deleteProject(p);
                }
                plw.update();
            }
        }

    }

}
