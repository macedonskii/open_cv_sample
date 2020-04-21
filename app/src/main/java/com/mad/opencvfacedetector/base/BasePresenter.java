package com.mad.opencvfacedetector.base;

public interface BasePresenter<T extends BaseView> {

    T getView();

    void attachView(T view);

    void detachView( );

}
