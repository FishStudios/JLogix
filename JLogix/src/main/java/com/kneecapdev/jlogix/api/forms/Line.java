package com.kneecapdev.jlogix.api.forms;

import com.kneecapdev.jlogix.api.forms.color.ColorProvider;

public class Line implements ModularDrawable {

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final ColorProvider color;

    /**
     * Creates a new line instance with the specified coordinates
     * @param x1 x-axis of the start coordinate
     * @param y1 y-axis of the start coordinate
     * @param x2 x-axis of the end coordinate
     * @param y2 y-axis of the end coordinate
     */
    public Line(double x1, double y1, double x2, double y2, ColorProvider color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public double[] getStart() {
        return new double[]{x1, y1};
    }

    public double[] getEnd() {
        return new double[]{x2, y2};
    }

    public ColorProvider getColor() {
        return color;
    }

}
