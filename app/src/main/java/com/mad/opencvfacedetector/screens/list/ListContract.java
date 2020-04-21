package com.mad.opencvfacedetector.screens.list;

import com.mad.opencvfacedetector.base.BasePresenter;
import com.mad.opencvfacedetector.base.BaseView;

public interface ListContract {

    interface ListView extends BaseView {
        void setData(
                // TODO: 21.04.2020 method parameters
        );
    }

    interface ListPresenter extends BasePresenter<ListView> {
        void onCreate();
    }

    interface ListModel {
        void loadData();
    }
}
