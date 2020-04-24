package com.mad.opencvfacedetector.screens.model;

import com.mad.opencvfacedetector.screens.details.DetailsContract;
import com.mad.opencvfacedetector.screens.details.ImageData;
import com.mad.opencvfacedetector.screens.recognition.RecognitionContract;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Model implements RecognitionContract.RecognitionModel, DetailsContract.DetailsModel {

    private FileStorage fileStorage;
    private LocalStorage localStorage;
    private BitmapUtils utils;

    public Model(FileStorage fileStorage, LocalStorage localStorage, BitmapUtils bitmapUtils) {
        this.fileStorage = fileStorage;
        this.localStorage = localStorage;
        utils = bitmapUtils;
    }

    @Override
    public Completable saveTmpImage(Mat rgba, Rect[] rects) {
        return Completable.fromRunnable(() -> {
            fileStorage.saveTmpFile(rgba);
            localStorage.saveTmpRects(rects);
        });
    }

    @Override
    public @NonNull Single<ImageData> loadImageData(int id) {
        if (id < 0) {
            return Single.just(fileStorage)
                    .map(FileStorage::getTmpBitmap)
                    .zipWith(Single.just(localStorage).map(LocalStorage::loadTmpRects),
                            (bitmap, rects) -> utils.provideRecognizedImages(bitmap, rects));
        }


        return null;
    }
}
