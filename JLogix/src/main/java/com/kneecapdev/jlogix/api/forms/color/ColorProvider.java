package com.kneecapdev.jlogix.api.forms.color;

public interface ColorProvider {

    /**
     * Returns double[4] array of color parameters
     * double[0] = r
     * double[1] = g
     * double[2] = b
     * double[3] = a
     * @return double[4] array of color parameters (see method summary)
     */
    double[] getColor();

    /**
     * Returns the alpha channel of the specified color (0-1)
     * @return alpha channel of the specified color (0-1)
     */
    double getAlpha();

    /**
     * Returns the red parameter of the specified color (0-1)
     * @return the red parameter of the specified color (0-1)
     */
    double getR();

    /**
     * Returns the green parameter of the specified color (0-1)
     * @return the green parameter of the specified color (0-1)
     */
    double getG();

    /**
     * Returns the blue parameter of the specified color (0-1)
     * @return the blue parameter of the specified color (0-1)
     */
    double getB();

}
