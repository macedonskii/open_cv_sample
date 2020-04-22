package com.mad.opencvfacedetector.base;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private T view;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        compositeDisposable.clear();
    }

    protected boolean handleThrowable(Throwable throwable) {
        throwable.printStackTrace();
        return true;
    }
}
