package com.mad.opencvfacedetector.screens.details;

import com.mad.opencvfacedetector.base.BasePresenter;
import com.mad.opencvfacedetector.base.BaseView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;

public interface DetailsContract {

    interface DetailsView extends BaseView {
        void showImageData(ImageData data);

//        void showImageData(@NonNull Single<ImageData> imageDataSingle);
    }

    interface DetailsPresenter extends BasePresenter<DetailsView> {
        void onCreate(int imageId);
    }

    interface DetailsModel {
        @io.reactivex.rxjava3.annotations.NonNull Single<ImageData> loadImageData(int id);


    }
}
