package com.mad.opencvfacedetector.screens.recognition;

import android.content.Context;
import android.util.Log;

import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class OpenCvLoader {

    private LoaderCallback callback;

    public OpenCvLoader(LoaderCallback callback) {
        this.callback = callback;
    }

    void onResume(Context context) {
        if (OpenCVLoader.initDebug()) {
            loaderCallbackInterface.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, context, loaderCallbackInterface);
        }
    }

    private LoaderCallbackInterface loaderCallbackInterface = new LoaderCallbackInterface() {
        @Override
        public void onManagerConnected(int status) {
            if (status == LoaderCallbackInterface.SUCCESS) {
                callback.onOpenCvLoaded();
            }
        }

        @Override
        public void onPackageInstall(int operation, InstallCallbackInterface callback) {

        }
    };

    interface LoaderCallback {
        void onOpenCvLoaded();
    }
}
