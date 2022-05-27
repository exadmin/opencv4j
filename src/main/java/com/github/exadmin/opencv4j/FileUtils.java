package com.github.exadmin.opencv4j;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FileUtils {

    /**
     * Reads image from file and converts it to Mat representation
     * @param fileName String file name path
     * @return Mat
     */
    public static Mat loadImageFileToMat(String fileName) {
        return Imgcodecs.imread(fileName);
    }

    public static boolean saveImageToFile(Image fxImage, String fileName) {
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(fxImage, null), "png", new File(fileName));
            return true;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return false;
        }
    }
}
