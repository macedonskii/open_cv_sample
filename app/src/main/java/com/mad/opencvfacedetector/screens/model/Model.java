package com.mad.opencvfacedetector.screens.model;

import android.graphics.Bitmap;

import com.mad.opencvfacedetector.screens.details.DetailsContract;
import com.mad.opencvfacedetector.screens.details.ImageData;
import com.mad.opencvfacedetector.screens.list.ListContract;
import com.mad.opencvfacedetector.screens.model.database.Database;
import com.mad.opencvfacedetector.screens.model.database.data.Image;
import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;
import com.mad.opencvfacedetector.screens.recognition.RecognitionContract;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Model implements RecognitionContract.RecognitionModel, DetailsContract.DetailsModel, ListContract.ListModel {

    private FileStorage fileStorage;
    private LocalStorage localStorage;
    private BitmapUtils utils;
    private Database database;

    public Model(FileStorage fileStorage, LocalStorage localStorage, BitmapUtils bitmapUtils, Database database) {
        this.fileStorage = fileStorage;
        this.localStorage = localStorage;
        this.database = database;
        utils = bitmapUtils;
    }

    @Override
    public Single<Long> saveTmpImage(Mat rgba, Rect[] rects) {
        return Single.just(rgba)
                .map(fileStorage::saveFile)
                .zipWith(Observable.fromArray(rects).map(rect -> new RecognizedRect(rect.x, rect.y, rect.width, rect.height)).toList(), database::saveImage)
                .compose(applySchedulers())
                ;
    }

    @Override
    public @NonNull Single<ImageData> loadImageData(long id) {
        return Single.just(id)
                .map(database::getImagesAndRect)
                .map(data -> {
                    Bitmap bitmap = fileStorage.getBitmap(data.getImage().getPath());
                    List<Bitmap> list = utils.provideRecognizedImages(bitmap, data.getRects());
                    return new ImageData(data.getImage().getId(), bitmap, list);
                })
                .compose(applySchedulers())
                ;
    }

    @Override
    public Single<List<Image>> loadData() {
        return Single.just(database)
                .map(Database::getImages)
                .compose(applySchedulers())
                ;
    }

    public <T> SingleTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
