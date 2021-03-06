package com.github.exadmin.opencv4j;

import org.opencv.core.Scalar;

import java.util.Random;

public class ColorUtils {
    private static final Random rng = new Random(12345);

    public static Scalar getColor(double red, double green, double blue) {
        // Original 3 channel color has BGR order
        // (c) https://stackoverflow.com/questions/29301903/how-to-specify-a-color-using-scalar-class
        return new Scalar(blue, green, red);
    }

    public static Scalar getRandomColor() {
        return new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
    }
}
