package com.kneecapdev.jlogix.api.forms.geoforms;

import com.kneecapdev.jlogix.api.forms.ImmutableLineSet;
import com.kneecapdev.jlogix.api.forms.Line;
import com.kneecapdev.jlogix.api.forms.color.ColorProvider;

public class Letter extends ImmutableLineSet {

    public enum FormsLetter {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
    }

    public Letter(FormsLetter fl, double scale, ColorProvider color) {
        this(fl, scale, color, 0, 0);
    }

    public Letter(FormsLetter fl, double scale, ColorProvider color, double xOffset, double yOffset) {
        switch (fl) {
            case A:
                break;
            case B:
                break;
            case C:
                break;
            case D:
                break;
            case E:
                break;
            case F:
                break;
            case G:
                break;
            case H:
                break;
            case I:
                break;
            case J:
                break;
            case K:
                break;
            case L:
                lines = new Line[2];
                lines[0] = new Line(0 + xOffset, 0 + yOffset, 0 + xOffset, .5 + yOffset, color, scale);
                lines[1] = new Line(0 + xOffset, .5 + yOffset, .3 + xOffset, .5 + yOffset, color, scale);
                break;
            case M:
                break;
            case N:
                break;
            case O:
                lines = new Line[4];
                lines[0] = new Line(0 + xOffset, 0 + yOffset, .3 + xOffset, 0 + yOffset, color, scale);
                lines[1] = new Line(.3 + xOffset, 0 + yOffset, .3 + xOffset, .5 + yOffset, color, scale);
                lines[2] = new Line(.3 + xOffset, .5 + yOffset, 0 + xOffset, .5 + yOffset, color, scale);
                lines[3] = new Line(0 + xOffset, .5 + yOffset, 0 + xOffset, 0 + yOffset, color, scale);
                break;
            case P:
                break;
            case Q:
                break;
            case R:
                break;
            case S:
                break;
            case T:
                break;
            case U:
                break;
            case V:
                break;
            case W:
                break;
            case X:
                break;
            case Y:
                lines = new Line[3];
                lines[0] = new Line(.15D + xOffset, .5 + yOffset, .15D + xOffset, .2 + yOffset, color, scale);
                lines[1] = new Line(0 + xOffset, 0 + yOffset, .15D + xOffset, .2 + yOffset, color, scale);
                lines[2] = new Line(.3 + xOffset, 0 + yOffset, .15D + xOffset, .2 + yOffset, color, scale);
                break;
            case Z:
                break;
        }
    }

}
