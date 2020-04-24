package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import com.mad.opencvfacedetector.screens.details.ImageData;

import org.opencv.core.Rect;

import java.util.ArrayList;

public class BitmapUtilsImpl implements BitmapUtils {

    @Override
    public ImageData provideRecognizedImages(Bitmap bitmap, Rect[] rects) {
        // TODO: 22.04.2020 LATER!
        return new ImageData(bitmap, new ArrayList<>());
    }
}
