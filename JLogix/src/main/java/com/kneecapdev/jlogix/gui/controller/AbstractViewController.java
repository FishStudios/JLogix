package com.kneecapdev.jlogix.gui.controller;


import com.kneecapdev.jlogix.gui.view.GUIView;

/**
 * Controller class for GUIView
 * Used to handle events (such as clicking on a button)
 * @param <T> Class extending GUIView
 */
public class AbstractViewController<T extends GUIView> {

    protected final T view;

    public AbstractViewController(T view) {
        this.view = view;
    }

    /**
     * Returns the view which is controlled by this object
     * @return the view which is controlled by this object
     */
    public T getView() {
        return this.view;
    }

}
