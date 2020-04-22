package com.mad.opencvfacedetector.screens.recognition;

import android.content.Context;

import com.mad.opencvfacedetector.Utils;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import java.lang.ref.WeakReference;

public class RecognitionHandler implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final Scalar FACE_RECT_COLOR = new Scalar(0, 255, 0, 255);
    private static final Size FACE_SIZE = new Size(100, 100);


    private CascadeClassifier cascadeClassifier;
    private WeakReference<Context> contextWeakReference;
    private OnRecognitionListener listener;


    public RecognitionHandler(JavaCameraView cameraView, Context context, OnRecognitionListener listener) {
        cameraView.setCvCameraViewListener(this);
        contextWeakReference = new WeakReference<>(context);
        this.listener = listener;
    }

    public void onOpenCvLoaded() {
        if (cascadeClassifier != null) {
            return;
        }

        String tmp = Utils.getPathFromAssets(contextWeakReference.get(), "haarcascade_frontalface_default.xml");
        cascadeClassifier = new CascadeClassifier(tmp);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        if (cascadeClassifier == null) {
            return inputFrame.rgba();
        }

        MatOfRect matOfRect = new MatOfRect();
        cascadeClassifier.detectMultiScale2(inputFrame.gray(), matOfRect, new MatOfInt(1), 1.3, 4, Objdetect.CASCADE_SCALE_IMAGE, FACE_SIZE);
        Mat rgba = inputFrame.rgba();
        Rect[] rects = matOfRect.toArray();
        if (rects.length != 0) {
            listener.onRecognition(rgba, rects);
        }
        for (Rect rect : rects) {
            Imgproc.rectangle(rgba, rect.tl(), rect.br(), FACE_RECT_COLOR, 3);
        }

        return rgba;
    }

    interface OnRecognitionListener {
        void onRecognition(Mat rgba, Rect[] rects);
    }
}
