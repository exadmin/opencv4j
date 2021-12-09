package com.github.exadmin.opencv4j;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageUtils {

    /**
     * Resizes image and returns new one
     * @param sourceImageMat source image matrix to use a base for the new one
     * @param newWidth width for the new image
     * @param newHeight height for the new image
     * @return new matrix instance
     */
    public static Mat getResizedImage(Mat sourceImageMat, int newWidth, int newHeight) {
        Mat newImage = new Mat();
        Size newSize = new Size(newWidth, newHeight);

        Imgproc.resize(sourceImageMat, newImage, newSize);
        return newImage;
    }

    /**
     * Returns new image matrix which is gray-scaled
     * @param sourceImageMat base image matrix to use
     * @return new matrix instance
     */
    public static Mat getGrayScaledImage(Mat sourceImageMat) {
        Mat newImage = new Mat();
        Imgproc.cvtColor(sourceImageMat, newImage, Imgproc.COLOR_BGR2GRAY);
        return newImage;
    }

    /**
     * Bilateral filtering essentially applies a 2D Gaussian (weighted) blur to the image, while also considering
     * the variation in intensities of neighboring pixels to minimize the blurring near edges.
     * @param sourceImage source image matrix to use
     * @param diameter defines the diameter of the pixel neighborhood used for filtering
     * @param sigmaColor defines the spatial extent of the kernel, in both the x and y directions
     * @param sigmaSpace defines the one-dimensional Gaussian distribution, which specifies the degree to which
     *                   differences in pixel intensity can be tolerated
     * @return new matrix instance
     */
    public static Mat getBilateralFilteredImage(Mat sourceImage, int diameter, int sigmaColor, int sigmaSpace) {
        Mat newImage = new Mat();
        Imgproc.bilateralFilter(sourceImage, newImage, diameter, sigmaColor, sigmaSpace);
        return newImage;
    }
}
