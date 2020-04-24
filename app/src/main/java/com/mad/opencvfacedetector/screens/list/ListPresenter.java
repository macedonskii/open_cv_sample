package com.mad.opencvfacedetector.screens.list;

import com.mad.opencvfacedetector.App;
import com.mad.opencvfacedetector.base.BasePresenterImpl;
import com.mad.opencvfacedetector.screens.model.database.data.Image;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;

public class ListPresenter extends BasePresenterImpl<ListContract.ListView> implements ListContract.ListPresenter {

    @Inject
    ListContract.ListModel model;

    public ListPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreate() {
        Disposable subscribe = model.loadData().subscribe(getView()::setData, this::handleThrowable);
        compositeDisposable.add(subscribe);
    }

    @Override
    public void onClickImage(Image image) {
        getView().showDetailsScreen(image.getId());
    }
}
