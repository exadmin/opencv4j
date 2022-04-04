package com.github.exadmin.opencv4j;

import javafx.embed.swing.SwingFXUtils;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import java.awt.image.BufferedImage;

public class ImageUtils {
    public static java.awt.Image convertToAwtImage(Mat matrix) {
        return HighGui.toBufferedImage(matrix);
    }

    public static javafx.scene.image.Image convertToFxImage(Mat matrix) {
        java.awt.Image awtImage = convertToAwtImage(matrix);
        return SwingFXUtils.toFXImage((BufferedImage) awtImage, null);
    }
}
