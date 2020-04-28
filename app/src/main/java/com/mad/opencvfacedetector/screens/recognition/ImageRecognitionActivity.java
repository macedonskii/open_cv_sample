package com.mad.opencvfacedetector.screens.recognition;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.base.BaseActivity;
import com.mad.opencvfacedetector.screens.details.DetailsActivity;

import org.opencv.android.JavaCameraView;

public class ImageRecognitionActivity extends BaseActivity implements RecognitionContract.RecognitionView {

    private static final int CAMERA_REQUEST = 1;
    private static final String TAG = ImageRecognitionActivity.class.getSimpleName();


    public static Intent getIntent(Context context) {
        return new Intent(context, ImageRecognitionActivity.class);
    }

    private RecognitionContract.RecognitionPresenter presenter;
    private JavaCameraView cameraView;
    private RecognitionHandler recognitionHandler;
    private OpenCvLoader openCvLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = findViewById(R.id.cameraView);

        presenter = new RecognitionPresenter();
        presenter.attachView(this);

        recognitionHandler = new RecognitionHandler(cameraView, getApplicationContext(), (rgba, rects) -> {
            presenter.onFaceDetected(rgba, rects);
        });
        openCvLoader = new OpenCvLoader(() -> {
            recognitionHandler.onOpenCvLoaded();
            if (checkPermission()) {
                initCamera();
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
        openCvLoader.onResume(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.disableView();
    }

    private void initCamera() {
        cameraView.setCameraPermissionGranted();
        cameraView.enableView();
    }

    @Override
    public void showDetailsScreen(Long imageId) {
        startActivity(DetailsActivity.getIntent(this, imageId));
    }

    @Override
    public void disableCamera() {
        cameraView.disableView();
    }

    @Override
    public RecognitionContract.RecognitionPresenter getPresenter() {
        return presenter;
    }
}
