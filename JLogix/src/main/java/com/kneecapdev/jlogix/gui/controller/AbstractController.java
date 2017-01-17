package com.kneecapdev.jlogix.gui.controller;

import javafx.scene.Node;

/**
 * Controller class for JavaFX Nodes
 * Used to handle events (such as clicking on a button)
 * @param <T> Class extending JavaFX Node
 */
public class AbstractController<T extends Node> {

    protected final T node;

    public AbstractController(T node) {
        this.node = node;
    }

    /**
     * Returns the node which is controlled by this object
     * @return the node which is controlled by this object
     */
    public T getNode() {
        return node;
    }

}
