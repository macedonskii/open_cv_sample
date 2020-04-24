package com.mad.opencvfacedetector.screens.list;

import com.mad.opencvfacedetector.base.BasePresenter;
import com.mad.opencvfacedetector.base.BaseView;
import com.mad.opencvfacedetector.screens.model.database.data.Image;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ListContract {

    interface ListView extends BaseView {
        void setData(List<Image> list);

        void showDetailsScreen(long id);
    }

    interface ListPresenter extends BasePresenter<ListView> {
        void onCreate();

        void onClickImage(Image image);
    }

    interface ListModel {
        Single<List<Image>> loadData();
    }
}
