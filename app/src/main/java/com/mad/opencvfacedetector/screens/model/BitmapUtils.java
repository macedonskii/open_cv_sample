package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import java.util.List;

public interface BitmapUtils {

    List<Bitmap> provideRecognizedImages(Bitmap bitmap, List<RecognizedRect> list);
}
