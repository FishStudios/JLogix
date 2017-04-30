package com.kneecapdev.jlogix.gui.controls.editorcanvas;

import com.kneecapdev.jlogix.api.forms.FormsCanvas;
import com.kneecapdev.jlogix.api.forms.Line;
import com.kneecapdev.jlogix.gui.controller.AbstractController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class EditorCanvasFormsController extends AbstractController<EditorCanvas> {

    public final EventHandler<MouseEvent> mousePressedHandler = new MousePressedHandler();
    public final EventHandler<MouseEvent> mouseReleasedHandler = new MouseReleasedHandler();
    public final EventHandler<MouseEvent> mouseDraggedHandler = new MouseDraggedHandler();

    private boolean mouseDown = false;
    private FormsCanvas selectedCanvas = null;
    private double curX = 0;
    private double curY = 0;

    public EditorCanvasFormsController(EditorCanvas node) {
        super(node);
    }

    public void formReplaced(FormsCanvas oldForm, FormsCanvas newForm) {
        if(selectedCanvas == oldForm) selectedCanvas = newForm;
    }

    private ArrayList<FormsCanvas> getFormsAtCoords(double x, double y) {
        ArrayList<FormsCanvas> forms = new ArrayList<>(node.getForms());

        Iterator<FormsCanvas> itForms = forms.iterator();
        while (itForms.hasNext()) {
            FormsCanvas fc = itForms.next();

            double x1 = Double.MAX_VALUE, x2 = 0, y1 = Double.MAX_VALUE, y2 = 0;
            for(Line l : fc.getLines()) {
                x1 = Math.min(x1, Math.min((fc.getX() + l.getStart()[0]) * node.getScaleFactor(),
                        (fc.getX() + l.getEnd()[0]) * node.getScaleFactor()));
                x2 = Math.max(x2, Math.max((fc.getX() + l.getStart()[0]) * node.getScaleFactor(),
                        (fc.getX() + l.getEnd()[0]) * node.getScaleFactor()));
                y1 = Math.min(y1, Math.min((fc.getY() + l.getStart()[1]) * node.getScaleFactor(),
                        (fc.getY() + l.getEnd()[1]) * node.getScaleFactor()));
                y2 = Math.max(y2, Math.max((fc.getY() + l.getStart()[1]) * node.getScaleFactor(),
                        (fc.getY() + l.getEnd()[1]) * node.getScaleFactor()));
            }

            if(!(x >= x1 && x <= x2 && y >= y1 && y <= y2)) itForms.remove();
        }
        return forms;
    }

    private class MousePressedHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            mouseDown = true;

            ArrayList<FormsCanvas> canvass = getFormsAtCoords(event.getX(), event.getY());
            if(canvass.size() > 0) {
                selectedCanvas = canvass.get(canvass.size() - 1);
                curX = event.getX();
                curY = event.getY();
            } else selectedCanvas = null;
        }

    }

    private class MouseReleasedHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            mouseDown = false;
        }

    }

    private class MouseDraggedHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if(mouseDown && selectedCanvas != null) {
                double deltaX = event.getX() - curX, deltaY = event.getY() - curY;
                curX = event.getX();
                curY = event.getY();

                deltaX /= node.getScaleFactor();
                deltaY /= node.getScaleFactor();

                selectedCanvas.move(deltaX, deltaY);
                node.reDraw();
            }
        }

    }

}
