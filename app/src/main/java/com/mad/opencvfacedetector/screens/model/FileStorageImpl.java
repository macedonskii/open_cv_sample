package com.mad.opencvfacedetector.screens.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
    public String saveTmpFile(Mat fileMatrix) {
        Mat dst = new Mat();
//        Imgproc.cvtColor(fileMatrix, dst, Imgproc.2RGB);
        // TODO: 24.04.2020 NEED TO FIX COLORS!
        File file = createFile();
        Imgcodecs.imwrite(file.getAbsolutePath(), fileMatrix);
        return file.getAbsolutePath();
    }

    private File createFile() {
        return new File(contextWeakReference.get().getCacheDir(), System.currentTimeMillis() + ".jpg");
    }

    @Override
    public Bitmap getTmpBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(createFile().getAbsolutePath(), options);
    }

    @Override
    public Bitmap getBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(path, options);
    }
}
