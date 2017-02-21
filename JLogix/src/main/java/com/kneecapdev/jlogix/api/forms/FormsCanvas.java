package com.kneecapdev.jlogix.api.forms;

import java.util.ArrayList;

public class FormsCanvas {

    private final Line[] lines;
    private final Ellipse[] ellipses;

    private FormsCanvas(Line[] lines, Ellipse[] ellipses) {
        this.lines = lines;
        this.ellipses = ellipses;
    }

    public Line[] getLines() {
        return this.lines;
    }

    public Ellipse[] getEllipses() {
        return this.ellipses;
    }

    public static class FormsCanvasBuilder {

        private final ArrayList<Line> lines = new ArrayList<>();
        private final ArrayList<Ellipse> ellipses = new ArrayList<>();

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

        public FormsCanvas build() {
            Line[] lineBuild = new Line[lines.size()];
            Ellipse[] ellipseBuild = new Ellipse[ellipses.size()];

            lines.toArray(lineBuild);
            ellipses.toArray(ellipseBuild);

            return new FormsCanvas(lineBuild, ellipseBuild);
        }

    }

    public static class ModularFormsCanvasBuilder {
        // TODO
    }

}