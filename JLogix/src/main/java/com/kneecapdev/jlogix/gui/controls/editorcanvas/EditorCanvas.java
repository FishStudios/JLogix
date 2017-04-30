package com.kneecapdev.jlogix.gui.controls.editorcanvas;

import com.kneecapdev.jlogix.api.forms.color.FColor;
import com.kneecapdev.jlogix.api.forms.FormsCanvas;
import com.kneecapdev.jlogix.api.forms.color.FColors;
import com.kneecapdev.jlogix.api.forms.geoforms.Letter.FormsLetter;
import com.kneecapdev.jlogix.api.forms.geoforms.Rectangle;
import com.kneecapdev.jlogix.api.forms.geoforms.Word;
import com.kneecapdev.jlogix.api.forms.util.Forms2JavaFX;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class EditorCanvas extends Canvas {

    private final EditorCanvasFormsController movementController = new EditorCanvasFormsController(this);
    private final EditorCanvasController controller = new EditorCanvasController(this);

    private final ArrayList<FormsCanvas> forms = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private double scaleFactor = 1;

    public EditorCanvas() {
        super();

        widthProperty().addListener(controller.sizeChangedHandler);
        heightProperty().addListener(controller.sizeChangedHandler);

        setOnMousePressed(movementController.mousePressedHandler);
        setOnMouseReleased(movementController.mouseReleasedHandler);
        setOnMouseDragged(movementController.mouseDraggedHandler);
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

    /**
     * Registers a FormsCanvas with the EditorCanvas which then gets painted in the next reDraw cycle
     * @param fc valid FormsCanvas
     */
    public void registerForm(FormsCanvas fc) {
        lock.tryLock();
        forms.add(fc);
        lock.unlock();
    }

    /**
     * Unregisters a FormsCanvas with the EditorCanvas
     * @param fc valid FormsCanvas
     */
    public void unregisterForm(FormsCanvas fc) {
        lock.tryLock();
        forms.remove(fc);
        lock.unlock();
    }

    /**
     * Replaces a FormsCanvas
     * Use this method to replace FormsCanvas objects instead of using unregisterForm() and registerForm() when
     * updating a FormsCanvas (needed so click-handling can be updated in case the Form is currently being dragged)
     * Should only be called from JavaFX-Thread
     * reDraw() has to be called afterwards to show changes
     * @param oldForm old FormsCanvas instance
     * @param newForm new FormsCanvas instance
     */
    public void replaceForm(FormsCanvas oldForm, FormsCanvas newForm) {
        newForm.setX(oldForm.getX());
        newForm.setY(oldForm.getY());
        forms.remove(oldForm);
        forms.add(newForm);
        movementController.formReplaced(oldForm, newForm);
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public ArrayList<FormsCanvas> getForms() {
        return forms;
    }

}
