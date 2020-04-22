package com.mad.opencvfacedetector.screens.model;

import android.content.Context;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.lang.ref.WeakReference;

public class FileStorageImpl implements FileStorage {

    private WeakReference<Context> contextWeakReference;

    public FileStorageImpl(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    @Override
    public void saveTmpFile(Mat fileMatrix) {
        File cacheDir = contextWeakReference.get().getCacheDir();
        Imgcodecs.imwrite(new File(cacheDir, "tmp.jpg").getAbsolutePath(),fileMatrix);
    }
}
