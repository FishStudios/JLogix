package com.kneecapdev.jlogix.gui.controls.editorcanvas;

import com.kneecapdev.jlogix.gui.controller.AbstractController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EditorCanvasController extends AbstractController<EditorCanvas> {

    public ChangeListener<Number> sizeChangedHandler = new SizeChangeHandler();

    public EditorCanvasController(EditorCanvas node) {
        super(node);
    }

    private class SizeChangeHandler implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            node.setScaleFactor(Math.min(node.getWidth() / 10D, node.getHeight() / 6D));
            node.reDraw();
        }

    }

}
