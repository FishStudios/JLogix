package com.kneecapdev.jlogix.gui.editor;

import com.kneecapdev.jlogix.gui.StyleManager;
import com.kneecapdev.jlogix.gui.controls.menubar.JLogixMenuBar;
import com.kneecapdev.jlogix.gui.view.GUIView;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class EditorView extends GUIView {

    private Scene scene;

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
