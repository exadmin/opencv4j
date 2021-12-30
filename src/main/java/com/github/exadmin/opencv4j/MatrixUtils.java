package com.github.exadmin.opencv4j;

import com.github.exadmin.opencv4j.enums.ContourApproximationMethod;
import com.github.exadmin.opencv4j.enums.CvType4j;
import com.github.exadmin.opencv4j.enums.RetrievalMode;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

    /**
     * Resizes image and returns new one
     * @param sourceImageMat source image matrix to use a base for the new one
     * @param newWidth width for the new image
     * @param newHeight height for the new image
     * @return new matrix instance
     */
    public static Mat getResizedImage(Mat sourceImageMat, double newWidth, double newHeight) {
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
    public static Mat getBilateralFilteredImage(Mat sourceImage, int diameter, double sigmaColor, double sigmaSpace) {
        Mat newImage = new Mat();
        Imgproc.bilateralFilter(sourceImage, newImage, diameter, sigmaColor, sigmaSpace);
        return newImage;
    }

    /**
     * The Gaussian filter is a low-pass filter that removes the high-frequency components are reduced.
     * @param sourceImage source image matrix to use as base for Gaussian blurring
     * @param kernelWidth width of kernel for Gaussian Blur operation
     * @param kernelHeight height of kernel for Gausiian Blur operation
     * @param sigmaX A variable of the type double representing the Gaussian kernel standard deviation in X direction.
     * @return new matrix insance
     */
    public static Mat getGaussianBlurredImage(Mat sourceImage, double kernelWidth, double kernelHeight, double sigmaX) {
        Mat newImage = new Mat();
        Size kernelSize = new Size(kernelWidth, kernelHeight);
        Imgproc.GaussianBlur(sourceImage, newImage, kernelSize, sigmaX);
        return newImage;
    }

    /**
     * Edges detection using Canny algorithm
     * @param sourceImage source image matrix to read
     * @param threshold1
     * @param threshold2
     * @param apertureSize
     * @return new matrix instance
     */
    public static Mat getEdgesUsingCanny(Mat sourceImage, double threshold1, double threshold2, int apertureSize) {
        if (apertureSize != 3 && apertureSize != 5 && apertureSize != 7) {
            throw new IllegalArgumentException("apertureSize must have 3, 5 or 7 int value");
        }
        Mat newImage = new Mat();
        Imgproc.Canny(sourceImage, newImage, threshold1, threshold2, apertureSize);

        return newImage;
    }

    public static List<MatOfPoint> findContours(Mat srcImage, RetrievalMode mode, ContourApproximationMethod method) {
        List<MatOfPoint> contourPoints = new ArrayList<>();

        Mat hierarchy = new Mat();

        // findCountours is destructive method (c) https://www.pyimagesearch.com/2014/04/21/building-pokedex-python-finding-game-boy-screen-step-4-6/
        // so let's copy original matrix to the temporary one
        Mat srcImageCopy = new Mat();
        srcImage.copyTo(srcImageCopy);

        Imgproc.findContours(srcImageCopy, contourPoints, hierarchy, mode.getValue(), method.getValue());
        hierarchy.release();

        return contourPoints;
    }

    /**
     * Resized source image and created new resized one.
     * Note! Method is not implemented fully.
     * @param sourceImage
     * @param prefWidth
     * @param prefHeight
     * @param keepAspectRatio
     * @return
     */
    public static Mat resizeImage(Mat sourceImage, int prefWidth, int prefHeight, boolean keepAspectRatio) {
        int srcWidth  = sourceImage.width();
        int srcHeight = sourceImage.height();

        if (keepAspectRatio) {
            double ratio = (double) srcWidth / (double) prefWidth;
            double newHeight = srcHeight / ratio;

            Mat resizedImage = new Mat();
            Size newSize = new Size(prefWidth, newHeight);
            Imgproc.resize(sourceImage, resizedImage, newSize);

            return resizedImage;
        }

        throw new IllegalStateException("Method is not implemented for this set of parameters");
    }

    public static Mat getCopy(Mat sourceMatrix) {
        Mat copy= new Mat();
        sourceMatrix.copyTo(copy);
        return copy;
    }

    /**
     * Approximates a curve or a polygon with another curve/polygon with less vertices so that the distance between them
     * is less or equal to the specified precision.
     * It uses the Douglas-Peucker algorithm <http://en.wikipedia.org/wiki/Ramer-Douglas-Peucker_algorithm>
     * @param curve input vector of 2D points
     * @param approximationPrecision Parameter specifying the approximation accuracy.
     *                               This is the maximum distance between the original curve and its approximation.
     * @param isClosedPolygon If true, the approximated curve is closed (its first and last vertices are connected).
     *                        Otherwise, it is not closed.
     * @return vector of 2D floating points as result of approximation.
     */
    public static MatOfPoint2f approximatePolygon(MatOfPoint curve, double approximationPrecision, boolean isClosedPolygon) {
        MatOfPoint2f curve2f = new MatOfPoint2f();
        curve.convertTo(curve2f, CvType.CV_32FC2);
        return approximatePolygon(curve2f, approximationPrecision, isClosedPolygon);
    }

    public static MatOfPoint2f approximatePolygon(MatOfPoint2f curve, double approximationPrecision, boolean isClosedPolygon) {
        MatOfPoint2f resultCurve2f = new MatOfPoint2f();
        Imgproc.approxPolyDP(curve, resultCurve2f, approximationPrecision, isClosedPolygon);

        return resultCurve2f;
    }

    public static MatOfPoint2f approximatePolygon(MatOfPoint curve, int approxPerimeterMaxLengthInPercentage, boolean isClosedPolygon) {
        MatOfPoint2f curve2f = new MatOfPoint2f();
        curve.convertTo(curve2f, CvType.CV_32FC2);

        double perimeterLength = Imgproc.arcLength(curve2f, isClosedPolygon);
        double approxPerimeterLength = perimeterLength * approxPerimeterMaxLengthInPercentage / 100.0;

        return approximatePolygon(curve2f, approxPerimeterLength, isClosedPolygon);
    }

    public static MatOfPoint convert(MatOfPoint2f curve2f, CvType4j cvType) {
        MatOfPoint curve = new MatOfPoint();
        curve2f.convertTo(curve, cvType.getValue());

        return curve;
    }
}
