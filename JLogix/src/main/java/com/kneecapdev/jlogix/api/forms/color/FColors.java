package com.kneecapdev.jlogix.api.forms.color;

/**
 * Enum for predefined colors for easier development
 */
public enum FColors implements ColorProvider {
    BLACK(0, 0, 0, 1),
    WHITE(255, 255, 255, 1),
    RED(255, 0, 0, 1),
    GREEN(0, 255, 0, 1),
    BLUE(0, 0, 255, 1);

    private final double a;
    private final double r;
    private final double g;
    private final double b;

    FColors(int r, int g, int b, double alpha) {
        this.a = alpha;
        this.r = r / 255D;
        this.g = g / 255D;
        this.b = b / 255D;
    }

    @Override
    public double[] getColor() {
        return new double[]{r, g, b, a};
    }

    @Override
    public double getAlpha() {
        return a;
    }

    @Override
    public double getR() {
        return r;
    }

    @Override
    public double getG() {
        return g;
    }

    @Override
    public double getB() {
        return b;
    }

}