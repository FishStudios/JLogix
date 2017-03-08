package com.kneecapdev.jlogix.api.forms.util;

import com.kneecapdev.jlogix.api.forms.FormsCanvas;
import com.kneecapdev.jlogix.api.forms.Line;
import javafx.scene.canvas.GraphicsContext;

public class Forms2JavaFX {

    public static void formsCanvasToCanvas(FormsCanvas fc, GraphicsContext gc, double scaleFactor) {
        for(Line l : fc.getLines()) {
            gc.strokeLine(l.getStart()[0] * scaleFactor, l.getStart()[1] * scaleFactor, l.getEnd()[0] * scaleFactor, l.getEnd()[1] * scaleFactor);
        }
    }

}
