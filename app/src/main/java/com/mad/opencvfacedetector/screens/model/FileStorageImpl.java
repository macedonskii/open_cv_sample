package com.mad.opencvfacedetector.screens.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.lang.ref.WeakReference;

public class FileStorageImpl implements FileStorage {

    private WeakReference<Context> contextWeakReference;

    public FileStorageImpl(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    @Override
    public void saveTmpFile(Mat fileMatrix) {
        Mat dst = new Mat();
//        Imgproc.cvtColor(fileMatrix, dst, Imgproc.2RGB);
        // TODO: 24.04.2020 NEED TO FIX COLORS!
        Imgcodecs.imwrite(getTmpFile().getAbsolutePath(), dst);
    }

    private File getTmpFile() {
        return new File(contextWeakReference.get().getCacheDir(), "tmp.jpg");
    }

    @Override
    public Bitmap getTmpBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        return BitmapFactory.decodeFile(getTmpFile().getAbsolutePath(), options);
        return BitmapFactory.decodeFile(getTmpFile().getAbsolutePath());
    }
}
