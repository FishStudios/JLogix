package com.kneecapdev.jlogix.api.forms.color;

/**
 * Immutable color storage class for FormsAPI
 */
public class FColor implements ColorProvider {

    private final double a;
    private final double r;
    private final double g;
    private final double b;

    /**
     * Creates a new immutable color for the FormsAPI
     * @param r red parameter (0-255)
     * @param g green parameter (0-255)
     * @param b blue parameter (0-255)
     * @param alpha alpha [opacity] parameter (0-1)
     */
    public FColor(int r, int g, int b, double alpha) {
        this.a = alpha;
        this.r = r / 255D;
        this.g = g / 255D;
        this.b = b / 255D;
    }

    /**
     * Creates a new immutable color for the FormsAPI
     * @param r red parameter (0-1)
     * @param g green parameter (0-1)
     * @param b blue parameter (0-1)
     * @param alpha alpha [opacity] parameter (0-1)
     */
    public FColor(double r, double g, double b, double alpha) {
        this.a = alpha;
        this.r = r;
        this.g = g;
        this.b = b;
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

    @Override
    public double[] getColor() {
        return new double[]{r, g, b, a};
    }

}