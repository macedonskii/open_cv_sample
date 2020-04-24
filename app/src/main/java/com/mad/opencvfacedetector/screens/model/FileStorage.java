package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import org.opencv.core.Mat;

public interface FileStorage {

    String saveFile(Mat fileMatrix);

    Bitmap getTmpBitmap();

    Bitmap getBitmap(String path);


}
