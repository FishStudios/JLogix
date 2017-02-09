package com.kneecapdev.jlogix.api.forms;

import java.util.HashSet;

public abstract class LineSet extends HashSet<Line> implements LineDerivable {

    @Override
    public Line[] getLines() {
        return this.toArray(new Line[this.size()]);
    }

}
