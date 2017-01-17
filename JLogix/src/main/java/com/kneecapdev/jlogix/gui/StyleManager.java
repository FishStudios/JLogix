package com.kneecapdev.jlogix.gui;

import javafx.scene.Scene;

import java.io.File;

public class StyleManager {

    public static StyleManager instance = new StyleManager();

    private File currentStyleSheet;

    private StyleManager() {
        this.currentStyleSheet = new File("resources/stylesheets/default.css");
    }

    public void applyCss(Scene scene) {
        scene.getStylesheets().add("file:///" + currentStyleSheet.getAbsolutePath().replace("\\", "/"));
    }

}
