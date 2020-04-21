package com.mad.opencvfacedetector.screens.recognition;

import org.opencv.android.JavaCameraView;
import org.opencv.objdetect.CascadeClassifier;

import java.lang.ref.WeakReference;

public class RecognitionHandler {

    private CascadeClassifier cascadeClassifier;
    private WeakReference<JavaCameraView> cameraViewWeakReference;

    public RecognitionHandler(JavaCameraView cameraView) {
        this.cascadeClassifier = cascadeClassifier;
        this.cameraViewWeakReference = new WeakReference<>(cameraView);
    }
}
