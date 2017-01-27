package com.kneecapdev.jlogix.gui.controls.edtiorcanvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class EditorCanvas extends Canvas {

    public EditorCanvas() {
        super();

        widthProperty().addListener(event -> reDraw());
        heightProperty().addListener(event -> reDraw());
    }

    public void reDraw() {
        getGraphicsContext2D().setFill(Color.YELLOW);
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());
    }

}
