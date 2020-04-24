package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import com.mad.opencvfacedetector.screens.details.ImageData;

import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

public class BitmapUtilsImpl implements BitmapUtils {

    @Override
    public ImageData provideRecognizedImages(Bitmap bitmap, Rect[] rects) {
        List<Bitmap> recognizedImages = new ArrayList<>();
        for (Rect rect : rects) {
            recognizedImages.add(Bitmap.createBitmap(bitmap, rect.x, rect.y, rect.width, rect.height));
        }

        return new ImageData(bitmap, recognizedImages);
    }
}
