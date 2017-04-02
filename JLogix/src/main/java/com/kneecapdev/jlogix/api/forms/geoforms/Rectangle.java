package com.kneecapdev.jlogix.api.forms.geoforms;

import com.kneecapdev.jlogix.api.forms.color.ColorProvider;
import com.kneecapdev.jlogix.api.forms.color.FColor;
import com.kneecapdev.jlogix.api.forms.ImmutableLineSet;
import com.kneecapdev.jlogix.api.forms.Line;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

public class Rectangle extends ImmutableLineSet {

    /**
     * Creates a new Rectangle 
     * @param x x-axis of top left corner of the rectangle
     * @param y y-axis of top left corner of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param color color of the whole rectangle
     */
    public Rectangle(double x, double y, double width, double height, ColorProvider color) {
        lines = new Line[4];
        lines[0] = new Line(x, y, x + width, y, color);
        lines[1] = new Line(x, y, x, y + height, color);
        lines[2] = new Line(x + width, y, x + width, y + height, color);
        lines[3] = new Line(x, y + height, x + width, y + height, color);
    }

    /**
     * Creates a new Rectangle rotated by the specified amount of degrees
     * @param x x-axis of the center of the rectangle
     * @param y y-axis of the center of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param rotation rotation of the rectangle in degrees
     * @param color color of the whole rectangle
     */
    public Rectangle(double x, double y, double width, double height, double rotation, ColorProvider color) {
        rotation = Math.toRadians(rotation);
        lines = new Line[4];
        double[] ul = {x + (width / 2) * cos(rotation) - (height / 2) * sin(rotation), y + (height / 2) * cos(rotation) + (width / 2) * sin(rotation)};
        double[] ur = {x - (width / 2) * cos(rotation) - (height / 2) * sin(rotation), y + (height / 2) * cos(rotation) - (width / 2) * sin(rotation)};
        double[] bl = {x + (width / 2) * cos(rotation) + (height / 2) * sin(rotation), y - (height / 2) * cos(rotation) + (width / 2) * sin(rotation)};
        double[] br = {x - (width / 2) * cos(rotation) + (height / 2) * sin(rotation), y - (height / 2) * cos(rotation) - (width / 2) * sin(rotation)};
        lines[0] = new Line(ul[0], ul[1], ur[0], ur[1], color);
        lines[1] = new Line(ur[0], ur[1], br[0], br[1], color);
        lines[2] = new Line(br[0], br[1], bl[0], bl[1], color);
        lines[3] = new Line(bl[0], bl[1], ul[0], ul[1], color);
    }

}
