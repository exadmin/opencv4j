package com.github.exadmin.opencv4j.enums;

import org.opencv.core.CvType;

public enum CvType4j {
    // https://docs.opencv.org/2.4/modules/core/doc/basic_structures.html
    CV_8bit_UnsignedInt(CvType.CV_8U),
    CV_8bit_SignedInt(CvType.CV_8S),
    CV_16bit_UnsignedInt(CvType.CV_16U),
    CV_16bit_SignedInt(CvType.CV_16S),
    CV_32bit_SignedInt(CvType.CV_32S),
    CV_32bit_Float(CvType.CV_32F),
    CV_64bit_Float(CvType.CV_64F),
    CV_32bit_Float_2Channels(CvType.CV_32FC2);

    CvType4j(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }
}
