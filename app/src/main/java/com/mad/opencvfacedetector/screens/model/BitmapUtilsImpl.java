package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import java.util.ArrayList;
import java.util.List;

public class BitmapUtilsImpl implements BitmapUtils {

    @Override
    public List<Bitmap> provideRecognizedImages(Bitmap bitmap, List<RecognizedRect> list) {
        List<Bitmap> recognizedImages = new ArrayList<>();
        for (RecognizedRect rect : list) {
            recognizedImages.add(Bitmap.createBitmap(bitmap, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight()));
        }

        return recognizedImages;
    }
}
