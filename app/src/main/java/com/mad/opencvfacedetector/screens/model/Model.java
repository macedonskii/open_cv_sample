package com.mad.opencvfacedetector.screens.model;

import com.mad.opencvfacedetector.screens.recognition.RecognitionContract;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class Model implements RecognitionContract.RecognitionModel {

    private FileStorage fileStorage;
    private LocalStorage localStorage;

    public Model(FileStorage fileStorage, LocalStorage localStorage) {
        this.fileStorage = fileStorage;
        this.localStorage = localStorage;
    }

    @Override
    public Completable saveTmpImage(Mat rgba, Rect[] rects) {
        return Completable.fromRunnable(() -> {
            fileStorage.saveTmpFile(rgba);
            localStorage.saveTmpRects(rects);
        });
    }
}
