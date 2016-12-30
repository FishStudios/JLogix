package com.kneecapdev.JLogix.gui.project.selector;

import com.kneecapdev.JLogix.gui.StyleManager;
import com.kneecapdev.JLogix.gui.controls.menubar.JLogixMenuBar;
import com.kneecapdev.JLogix.gui.controls.projecttreeview.ProjectListView;
import com.kneecapdev.JLogix.gui.view.GUIView;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ProjectSelectorView extends GUIView {

	private Scene scene;
	private ProjectListView projectListView;
	private final ProjectSelectorViewController controller = new ProjectSelectorViewController(this);
	
	@Override
	public void onShow() {

	}

	@Override
	public void onHide() {
	}
	
	@Override
	public void onInit() {
		VBox root = new VBox();
		scene = new Scene(root);

		StyleManager.instance.applyCss(scene);

		JLogixMenuBar menuBar = new JLogixMenuBar();
		root.getChildren().add(menuBar);

		HBox mainContent = new HBox();
		VBox.setVgrow(mainContent, Priority.ALWAYS);
		root.getChildren().add(mainContent);

		VBox leftWrapper = new VBox();
		leftWrapper.prefWidthProperty().bind(root.widthProperty().multiply(.5D));
		mainContent.getChildren().add(leftWrapper);

		HBox buttonContainer = new HBox();
		buttonContainer.getStyleClass().add("button-bar-project-selector");
		buttonContainer.spacingProperty().bind(leftWrapper.widthProperty().multiply(.25).divide(2));
		leftWrapper.getChildren().add(buttonContainer);

		Button create = new Button("Create");
		create.prefWidthProperty().bind(leftWrapper.widthProperty().multiply(.25));
		create.setOnAction(controller.onCreateClick);

		Button open = new Button("Open");
		open.prefWidthProperty().bind(leftWrapper.widthProperty().multiply(.25));
		open.setOnAction(controller.onOpenClick);

		Button delete = new Button("Delete");
		delete.prefWidthProperty().bind(leftWrapper.widthProperty().multiply(.25));
		delete.setOnAction(controller.onDeleteClick);

		buttonContainer.getChildren().addAll(create, open, delete);

		projectListView = new ProjectListView();
		VBox.setVgrow(projectListView, Priority.ALWAYS);
		leftWrapper.getChildren().add(projectListView);

		HBox rightWrapper = new HBox();
		rightWrapper.getStyleClass().addAll("box-padding", "align-center");
		rightWrapper.prefWidthProperty().bind(root.widthProperty().multiply(.5D));
		mainContent.getChildren().add(rightWrapper);

		Label todo = new Label("\"Hier kann noch iwas yolo stehen ka.\" - Domi");
		rightWrapper.getChildren().add(todo);
	}

	@Override
	public String getID() {
		return "projectSelector";
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	public ProjectListView getProjectListView() {
		return projectListView;
	}

}
