package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import com.mad.opencvfacedetector.screens.details.ImageData;

import org.opencv.core.Rect;

public interface BitmapUtils {

    ImageData provideRecognizedImages(Bitmap bitmap, Rect[] rects);
}
