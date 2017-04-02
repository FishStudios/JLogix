package com.kneecapdev.jlogix.api.forms.util;

import com.kneecapdev.jlogix.api.forms.FormsCanvas;
import com.kneecapdev.jlogix.api.forms.Line;
import com.kneecapdev.jlogix.api.forms.color.ColorProvider;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Forms2JavaFX {

    /**
     * Paints a FormsCanvas form to a JavaFX GraphicsContext with scale
     * @param fc FormsCanvas to be painted
     * @param gc JavaFX GraphicsContext
     * @param scaleFactor scale factor (usually computed by EditorCanvas instance)
     */
    public static void formsCanvasToCanvas(FormsCanvas fc, GraphicsContext gc, double scaleFactor) {
        int xOff = fc.getX();
        int yOff = fc.getY();
        for(Line l : fc.getLines()) {
            gc.setStroke(colorProviderToFX(l.getColor()));
            gc.strokeLine(xOff + l.getStart()[0] * scaleFactor, yOff + l.getStart()[1] * scaleFactor,
                    xOff + l.getEnd()[0] * scaleFactor, yOff + l.getEnd()[1] * scaleFactor);
        }
    }

    /**
     * Returns a JavaFX Color from ColorProvider interface provided values
     * @param cp colorprovider object instance
     * @return JavaFX Color from ColorProvider interface provided values
     */
    public static Color colorProviderToFX(ColorProvider cp) {
        return new Color(cp.getR(), cp.getG(), cp.getB(), cp.getAlpha());
    }

}
