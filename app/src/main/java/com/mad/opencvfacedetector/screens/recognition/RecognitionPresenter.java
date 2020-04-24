package com.mad.opencvfacedetector.screens.recognition;

import com.mad.opencvfacedetector.App;
import com.mad.opencvfacedetector.base.BasePresenterImpl;
import com.mad.opencvfacedetector.di.AppComponent;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

public class RecognitionPresenter extends BasePresenterImpl<RecognitionContract.RecognitionView> implements RecognitionContract.RecognitionPresenter {

    @Inject
    RecognitionContract.RecognitionModel model;

    public RecognitionPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onFaceDetected(Mat rgba, Rect[] rects) {
        Disposable subscribe = model.saveTmpImage(rgba, rects).subscribe(() -> getView().showDetailsScreen(), this::handleThrowable);
        compositeDisposable.add(subscribe);
    }
}
