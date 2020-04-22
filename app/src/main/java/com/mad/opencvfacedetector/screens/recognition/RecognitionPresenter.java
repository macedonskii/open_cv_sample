package com.mad.opencvfacedetector.screens.recognition;

import com.mad.opencvfacedetector.base.BasePresenterImpl;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

public class RecognitionPresenter extends BasePresenterImpl<RecognitionContract.RecognitionView> implements RecognitionContract.RecognitionPresenter {

    private RecognitionContract.RecognitionModel model;

    @Override
    public void onFaceDetected(Mat rgba, Rect[] rects) {
        Disposable subscribe = model.saveTmpImage(rgba, rects).subscribe(() -> getView().showDetailsScreen(), this::handleThrowable);
        compositeDisposable.add(subscribe);
    }
}
