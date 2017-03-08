package com.kneecapdev.jlogix.gui.controls.edtiorcanvas;

import com.kneecapdev.jlogix.api.forms.FormsCanvas;
import com.kneecapdev.jlogix.api.forms.geoforms.Rectangle;
import com.kneecapdev.jlogix.api.forms.util.Forms2JavaFX;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class EditorCanvas extends Canvas {

    private final ArrayList<FormsCanvas> forms = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private double scaleFactor = 1;

    public EditorCanvas() {
        super();

        FormsCanvas fc = new FormsCanvas.FormsCanvasBuilder()
                .withObject(new Rectangle(.1, .1, .8, .8))
                .build();
        FormsCanvas fc2 = new FormsCanvas.FormsCanvasBuilder()
                .withObject(new Rectangle( .4, .4, .2, .2, 45))
                .build();

        registerForm(fc);
        registerForm(fc2);

        widthProperty().addListener(event -> sizeChanged());
        heightProperty().addListener(event -> sizeChanged());
    }

    /**
     * Executes a complete redraw of the editor canvas
     */
    public void reDraw() {
        lock.tryLock();

        GraphicsContext gc = getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.BLACK);
        for (FormsCanvas fc : forms) {
            Forms2JavaFX.formsCanvasToCanvas(fc, gc, scaleFactor);
        }

        lock.unlock();
    }

    private void sizeChanged() {
        scaleFactor = Math.min(getWidth() / 100D, getHeight() / 60D);

        reDraw();
    }

    /**
     * Registers a FormsCanvas with the EditorCanvas which then gets painted in the next reDraw cycle
     * @param fc valid FormsCanvas
     */
    public void registerForm(FormsCanvas fc) {
        lock.tryLock();

        lock.unlock();
    }

    /**
     * Unregisters a FormsCanvas with the EditorCanvas
     * @param fc valid FormsCanvas
     */
    public void unregisterForm(FormsCanvas fc) {
        lock.tryLock();

        lock.unlock();
    }

}
