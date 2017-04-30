package com.kneecapdev.jlogix.api.forms;

import java.util.ArrayList;

/**
 * Canvas containing one ore multiple geometric constructs/forms
 * Can be moved by changing x/y coordinates
 * Contained lines/ellipses/other forms cannot be changed directly/are immutable
 * To build a FormsCanvas or change an existing one refer to FormsCanvasBuilder for static canvases or
 * ModularFormsCanvasBuilder for dynamically changing canvases
 */
public class FormsCanvas {

    private final Line[] lines;
    private final Ellipse[] ellipses;
    private double x;
    private double y;

    private FormsCanvas(Line[] lines, Ellipse[] ellipses, double x, double y) {
        this.lines = lines;
        this.ellipses = ellipses;
        this.x = x;
        this.y = y;
    }

    public Line[] getLines() {
        return this.lines;
    }

    public Ellipse[] getEllipses() {
        return this.ellipses;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Helper class to create static FormsCanvas instances
     */
    public static class FormsCanvasBuilder {

        private final ArrayList<Line> lines = new ArrayList<>();
        private final ArrayList<Ellipse> ellipses = new ArrayList<>();

        public FormsCanvasBuilder() {
        }

        public void addObject(Line l) {
            lines.add(l);
        }

        public void addObject(LineDerivable ld) {
            for (Line line : ld.getLines()) {
                addObject(line);
            }
        }

        public void addObject(Ellipse e) {
            ellipses.add(e);
        }

        public FormsCanvasBuilder withObject(Line l) {
            addObject(l);
            return this;
        }

        public FormsCanvasBuilder withObject(LineDerivable ld) {
            addObject(ld);
            return this;
        }

        public FormsCanvasBuilder withObject(Ellipse e) {
            addObject(e);
            return this;
        }

        /**
         * Finishes the current progress and creates a FormsCanvas
         * @return FormsCanvas instance with the previously added forms
         */
        public FormsCanvas build() {
            Line[] lineBuild = new Line[lines.size()];
            Ellipse[] ellipseBuild = new Ellipse[ellipses.size()];

            lines.toArray(lineBuild);
            ellipses.toArray(ellipseBuild);

            return new FormsCanvas(lineBuild, ellipseBuild, 0, 0);
        }

    }

    /**
     * Helper class to create more often dynamically changing FormsCanvas instances
     */
    public static class ModularFormsCanvasBuilder {
        // TODO
    }

}