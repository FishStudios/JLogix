package com.kneecapdev.jlogix.api.forms;

public abstract class ImmutableLineSet implements LineDerivable {

    protected Line[] lines;

    /**
     * Returns all the lines of the overlying form
     * <strong>Never, under any circumstances modify the array returned by this method</strong>
     * @return all the lines of the overlying form
     */
    @Override
    public Line[] getLines() {
        return lines;
    }

}
