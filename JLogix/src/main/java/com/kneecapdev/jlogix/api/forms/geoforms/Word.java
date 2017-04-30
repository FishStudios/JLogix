package com.kneecapdev.jlogix.api.forms.geoforms;

import com.kneecapdev.jlogix.api.forms.ImmutableLineSet;
import com.kneecapdev.jlogix.api.forms.Line;
import com.kneecapdev.jlogix.api.forms.color.ColorProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class Word extends ImmutableLineSet {

    public Word(double scale, ColorProvider color, Letter.FormsLetter... letters) {
        ArrayList<Line> linesList = new ArrayList<>();

        int i = 0;
        for(Letter.FormsLetter fl : letters) {
            Arrays.stream(new Letter(fl, scale, color, .375 * i, 0).getLines()).forEach(linesList::add);
            i++;
        }

        this.lines = new Line[linesList.size()];
        linesList.toArray(this.lines);
    }

}
