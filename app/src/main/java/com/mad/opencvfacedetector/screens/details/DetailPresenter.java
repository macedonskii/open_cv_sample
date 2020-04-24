package com.mad.opencvfacedetector.screens.details;

import com.mad.opencvfacedetector.App;
import com.mad.opencvfacedetector.base.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;

public class DetailPresenter extends BasePresenterImpl<DetailsContract.DetailsView> implements DetailsContract.DetailsPresenter {

    @Inject
    DetailsContract.DetailsModel model;

    public DetailPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreate(int imageId) {
        Disposable subscribe = model.loadImageData(imageId).subscribe(imageDataSingle -> {getView().showImageData(imageDataSingle);}, this::handleThrowable);
        compositeDisposable.add(subscribe);
    }
}
