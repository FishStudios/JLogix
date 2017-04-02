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
    private int x;
    private int y;

    private FormsCanvas(Line[] lines, Ellipse[] ellipses, int x, int y) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Helper class to create static FormsCanvas instances
     */
    public static class FormsCanvasBuilder {

        private final ArrayList<Line> lines = new ArrayList<>();
        private final ArrayList<Ellipse> ellipses = new ArrayList<>();
        private int x;
        private int y;

        public FormsCanvasBuilder() {
            this.x = 0;
            this.y = 0;
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

            return new FormsCanvas(lineBuild, ellipseBuild, x, y);
        }

        /**
         * Sets the x/y coordinates for the whole canvas
         * @param x x coordinate
         * @param y y coordinate
         */
        public void setCoords(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    /**
     * Helper class to create more often dynamically changing FormsCanvas instances
     */
    public static class ModularFormsCanvasBuilder {
        // TODO
    }

}