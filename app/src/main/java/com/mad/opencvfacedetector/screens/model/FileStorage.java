package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import org.opencv.core.Mat;

public interface FileStorage {

    void saveTmpFile(Mat fileMatrix);

    Bitmap getTmpBitmap();


}
