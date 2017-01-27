package com.kneecapdev.jlogix.gui.editor;

import com.kneecapdev.jlogix.gui.StyleManager;
import com.kneecapdev.jlogix.gui.controls.edtiorcanvas.EditorCanvas;
import com.kneecapdev.jlogix.gui.controls.menubar.JLogixMenuBar;
import com.kneecapdev.jlogix.gui.view.GUIView;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class EditorView extends GUIView {

    private Scene scene;
    private EditorCanvas canvas;
    private VBox rightContainer;
    private ListView nodes;

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
        root.setPrefSize(1000, 600);

        StyleManager.instance.applyCss(scene);

        JLogixMenuBar menuBar = new JLogixMenuBar();
        root.getChildren().add(menuBar);

        HBox mainContent = new HBox();
        mainContent.setStyle("-fx-background-color: black;");
        VBox.setVgrow(mainContent, Priority.ALWAYS);
        root.getChildren().add(mainContent);

        // TODO Replace ListView with specific control
        nodes = new ListView();
        nodes.setStyle("-fx-background-color: green;");
        nodes.prefWidthProperty().bind(scene.widthProperty().multiply(.2));
        mainContent.getChildren().add(nodes);
        nodes.getItems().add("Hello World");

        canvas = new EditorCanvas();
        HBox.setHgrow(canvas, Priority.ALWAYS);
        VBox.setVgrow(canvas, Priority.ALWAYS);
        mainContent.getChildren().add(canvas);
        canvas.reDraw();

        rightContainer = new VBox();
        rightContainer.setStyle("-fx-background-color: blue;");
        rightContainer.prefWidthProperty().bind(scene.widthProperty().multiply(.2));

        scene.widthProperty().addListener(event -> resizeCanvas());
        scene.heightProperty().addListener(event -> resizeCanvas());
    }

    private void resizeCanvas() {
        canvas.setWidth(rightContainer.isVisible() ? scene.getWidth() * .6 : scene.getWidth() * .8);
        canvas.setHeight(scene.getHeight());
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public String getID() {
        return "editor";
    }

}
