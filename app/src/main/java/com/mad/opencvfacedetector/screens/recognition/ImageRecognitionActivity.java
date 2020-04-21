package com.mad.opencvfacedetector.screens.recognition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.Utils;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class ImageRecognitionActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    private static final Scalar FACE_RECT_COLOR = new Scalar(0, 255, 0, 255);
    private static final String TAG = ImageRecognitionActivity.class.getSimpleName();

    public static Intent getIntent(Context context) {
        return new Intent(context, ImageRecognitionActivity.class);
    }

    private JavaCameraView cameraView;

    private CascadeClassifier cascadeClassifier;

    private LoaderCallbackInterface loaderCallbackInterface = new LoaderCallbackInterface() {
        @Override
        public void onManagerConnected(int status) {
            if (status == LoaderCallbackInterface.SUCCESS) {
                Log.i(TAG, "OpenCV loaded successfully");
                if (cascadeClassifier == null) {
                    String tmp = Utils.getPathFromAssets(ImageRecognitionActivity.this, "haarcascade_frontalface_default.xml");
                    Log.d(TAG, "onManagerConnected: " + tmp);
                    cascadeClassifier = new CascadeClassifier(tmp);
                }
                if (checkPermission()) {
                    initCamera();
                }
            }
        }

        @Override
        public void onPackageInstall(int operation, InstallCallbackInterface callback) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = findViewById(R.id.cameraView);


        cameraView.setCvCameraViewListener(new CameraBridgeViewBase.CvCameraViewListener2() {
            @Override
            public void onCameraViewStarted(int width, int height) {

            }

            @Override
            public void onCameraViewStopped() {

            }

            @Override
            public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
                MatOfRect matOfRect = new MatOfRect();
                cascadeClassifier.detectMultiScale2(inputFrame.gray(), matOfRect, new MatOfInt(1), 1.3, 4, Objdetect.CASCADE_SCALE_IMAGE, new Size(100, 100));
                Mat rgba = inputFrame.rgba();
                Rect[] rects = matOfRect.toArray();
                for (Rect rect : rects) {
                    Imgproc.rectangle(rgba, rect.tl(), rect.br(), FACE_RECT_COLOR, 3);
                }

                return rgba;
            }
        });

        if (savedInstanceState != null) {
            return;
        }

        checkPermission();
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{(Manifest.permission.CAMERA)}, CAMERA_REQUEST);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initCamera();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, loaderCallbackInterface);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            loaderCallbackInterface.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.disableView();
    }

    private void initCamera() {
        Log.d(TAG, "initCamera() called");
        cameraView.setCameraPermissionGranted();
        cameraView.enableView();
    }
}
