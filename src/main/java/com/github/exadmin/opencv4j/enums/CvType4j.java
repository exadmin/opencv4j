package com.github.exadmin.opencv4j.enums;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;

public class CvType4j<M extends Mat> {
    // https://docs.opencv.org/2.4/modules/core/doc/basic_structures.html

    public static final CvType4j<MatOfPoint> CV_8bit_UnsignedInt        = new CvType4j<>(CvType.CV_8U, MatOfPoint.class);
    public static final CvType4j<MatOfPoint> CV_8bit_SignedInt          = new CvType4j<>(CvType.CV_8S, MatOfPoint.class);
    public static final CvType4j<MatOfPoint> CV_16bit_UnsignedInt       = new CvType4j<>(CvType.CV_16U, MatOfPoint.class);
    public static final CvType4j<MatOfPoint> CV_16bit_SignedInt         = new CvType4j<>(CvType.CV_16S, MatOfPoint.class);
    public static final CvType4j<MatOfPoint> CV_32bit_SignedInt         = new CvType4j<>(CvType.CV_32S, MatOfPoint.class);

    public static final CvType4j<MatOfPoint2f> CV_32bit_Float           = new CvType4j<>(CvType.CV_32F, MatOfPoint2f.class);
    public static final CvType4j<MatOfPoint2f> CV_64bit_Float           = new CvType4j<>(CvType.CV_64F, MatOfPoint2f.class);
    public static final CvType4j<MatOfPoint2f> CV_32bit_Float_2Channels = new CvType4j<>(CvType.CV_32FC2, MatOfPoint2f.class);

    protected CvType4j(int value, Class<M> clazz) {
        this.value = value;
        this.clazz = clazz;
    }

    private final int value;
    private final Class<M> clazz;

    public int getValue() {
        return value;
    }

    public M getReturnInstance() {
        try {
            return (M) clazz.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Error while calling default constructor for the class " + clazz, ex);
        }
    }
}
