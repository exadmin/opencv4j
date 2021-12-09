package com.github.exadmin.opencv4j;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FileUtils {

    /**
     * Reads image from file and converts it to Mat representation
     * @param fileName String file name path
     * @return Mat
     */
    public static Mat loadImageFileToMat(String fileName) {
        return Imgcodecs.imread(fileName);
    }
}
