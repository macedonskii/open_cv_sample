package com.mad.opencvfacedetector.screens.recognition;

import com.mad.opencvfacedetector.App;
import com.mad.opencvfacedetector.base.BasePresenterImpl;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;

public class RecognitionPresenter extends BasePresenterImpl<RecognitionContract.RecognitionView> implements RecognitionContract.RecognitionPresenter {

    @Inject
    RecognitionContract.RecognitionModel model;
    private boolean processRun;

    public RecognitionPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onFaceDetected(Mat rgba, Rect[] rects) {
        if (processRun) {
            return;
        }
        processRun = true;

        Disposable subscribe = model.saveTmpImage(rgba, rects)
                .doFinally(() -> processRun = false)
                .subscribe(imageId -> {
                    getView().disableCamera();
                    getView().showDetailsScreen(imageId);
                }, this::handleThrowable);
        compositeDisposable.add(subscribe);
    }

}
