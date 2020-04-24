package com.mad.opencvfacedetector.screens.recognition;

import com.mad.opencvfacedetector.base.BasePresenter;
import com.mad.opencvfacedetector.base.BaseView;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import io.reactivex.rxjava3.core.Single;

public interface RecognitionContract {

    interface RecognitionView extends BaseView {

        void showDetailsScreen(Long imageId);

        void disableCamera();

    }

    interface RecognitionPresenter extends BasePresenter<RecognitionView> {


        void onFaceDetected(Mat rgba, Rect[] rects);

    }

    interface RecognitionModel {
        Single<Long> saveTmpImage(Mat rgba, Rect[] rects);
    }
}
