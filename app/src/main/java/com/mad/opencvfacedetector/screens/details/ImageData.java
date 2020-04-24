package com.mad.opencvfacedetector.screens.details;

import android.graphics.Bitmap;

import java.util.List;

import androidx.annotation.Nullable;

public class ImageData {

    private Long id;
    private Bitmap original;
    private List<Bitmap> recognizedImages;

    public ImageData(Long id, Bitmap original, List<Bitmap> recognizedImages) {
        this.id = id;
        this.original = original;
        this.recognizedImages = recognizedImages;
    }

    public ImageData(Bitmap original, List<Bitmap> recognizedImages) {
        this(null, original, recognizedImages);
    }

    public Bitmap getOriginal() {
        return original;
    }

    public List<Bitmap> getRecognizedImages() {
        return recognizedImages;
    }

    @Nullable
    public Long getId() {
        return id;
    }
}
