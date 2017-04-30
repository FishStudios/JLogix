package com.kneecapdev.jlogix.gui.controls.editorcanvas;

import com.kneecapdev.jlogix.api.forms.FormsCanvas;
import com.kneecapdev.jlogix.api.forms.color.ColorProvider;
import com.kneecapdev.jlogix.api.forms.color.FColor;
import com.kneecapdev.jlogix.api.forms.color.FColors;
import com.kneecapdev.jlogix.api.forms.geoforms.Letter;
import com.kneecapdev.jlogix.api.forms.geoforms.Rectangle;
import com.kneecapdev.jlogix.api.forms.geoforms.Word;
import javafx.application.Platform;

public class EditorTest {

    public static void addTestStuff(EditorCanvas ec) {
        FormsCanvas fc = new FormsCanvas.FormsCanvasBuilder()
                .withObject(new Rectangle(0, 0, .8, .8, FColors.BLUE))
                .build();

        FormsCanvas fc2 = new FormsCanvas.FormsCanvasBuilder()
                .withObject(new Rectangle( 0, 0, .25, .25, 45, new FColor(200, 50, 100, 1)))
                .build();

        fc.move(1, 1);
        fc2.move(1.375, 1.375);

        ec.registerForm(fc);
        ec.registerForm(fc2);

        Thread yfg = new YoloFormGenerator(ec);
        yfg.setDaemon(true);
        yfg.start();

        Thread rrg = new RotatingRectangleGenerator(ec);
        rrg.setDaemon(true);
        rrg.start();
    }

    private static class RotatingRectangleGenerator extends Thread {

        private final EditorCanvas ec;

        public RotatingRectangleGenerator(EditorCanvas ec) {
            super();
            this.ec = ec;
        }

        @Override
        public void run() {
            FormsCanvas fcRectangle = new FormsCanvas.FormsCanvasBuilder()
                    .withObject(new Rectangle( 0, 0, .5, .5, 0, FColors.GREEN))
                    .build();
            fcRectangle.move(3.5, 6);

            ec.registerForm(fcRectangle);

            double scale = .5, delta = .05, rotation = 0;
            while (!Thread.interrupted()) {

                rotation = rotation + 5 % 360;

                FormsCanvas fcRectangleNew = new FormsCanvas.FormsCanvasBuilder()
                        .withObject(new Rectangle( 0, 0, .5 * scale, .5 * scale, rotation, FColors.GREEN))
                        .build();
                FormsCanvas fcRectangleOld = fcRectangle;
                fcRectangle = fcRectangleNew;

                Platform.runLater(() -> {
                    ec.replaceForm(fcRectangleOld, fcRectangleNew);
                    ec.reDraw();
                });

                scale += delta;
                if(scale <= .5) delta *= -1;
                else if(scale >= 5) delta *= -1;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

    }

    private static class YoloFormGenerator extends Thread {

        private final EditorCanvas ec;

        public YoloFormGenerator(EditorCanvas ec) {
            super();
            this.ec = ec;
        }

        @Override
        public void run() {
            FormsCanvas fcYolo = new FormsCanvas.FormsCanvasBuilder()
                    .withObject(new Word(.125, FColors.FUCHSIA, Letter.FormsLetter.Y, Letter.FormsLetter.O, Letter.FormsLetter.L, Letter.FormsLetter.O))
                    .build();
            fcYolo.move(2.5, 2.5);

            ec.registerForm(fcYolo);

            double scale = .5, delta = .05, colorProgress = 0;
            while (!Thread.interrupted()) {
                colorProgress += 0.01;
                if(colorProgress > 1) colorProgress = 0;

                FormsCanvas fcYoloNew = new FormsCanvas.FormsCanvasBuilder()
                        .withObject(new Word(scale, rainbowGradient(colorProgress), Letter.FormsLetter.Y, Letter.FormsLetter.O, Letter.FormsLetter.L, Letter.FormsLetter.O))
                        .build();
                FormsCanvas fcYoloOld = fcYolo;
                fcYolo = fcYoloNew;

                Platform.runLater(() -> {
                    ec.replaceForm(fcYoloOld, fcYoloNew);
                    ec.reDraw();
                });

                scale += delta;
                if(scale <= .5) delta *= -1;
                else if(scale >= 2) delta *= -1;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private ColorProvider rainbowGradient(double progress) {
            double div = (Math.abs(progress % 1) * 6);
            int ascending = (int) ((div % 1) * 255);
            int descending = 255 - ascending;

            switch ((int) div) {
                case 0:
                    return new FColor(255, ascending, 0, 1);
                case 1:
                    return new FColor(descending, 255, 0, 1);
                case 2:
                    return new FColor(0, 255, ascending, 1);
                case 3:
                    return new FColor(0, descending, 255, 1);
                case 4:
                    return new FColor(ascending, 0, 255, 1);
                default: // case 5:
                    return new FColor(255, 0, descending, 1);
            }
        }

    }

}
