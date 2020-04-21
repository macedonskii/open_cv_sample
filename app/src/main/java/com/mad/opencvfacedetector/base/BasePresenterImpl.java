package com.mad.opencvfacedetector.base;

public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private T view;

    @Override
    public T getView() {
        return view;
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
