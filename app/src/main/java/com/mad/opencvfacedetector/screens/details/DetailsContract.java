package com.mad.opencvfacedetector.screens.details;

import com.mad.opencvfacedetector.base.BasePresenter;
import com.mad.opencvfacedetector.base.BaseView;

public interface DetailsContract {

    interface DetailsView extends BaseView {
        void showImageData(
                // TODO: 21.04.2020 METHOD PARAMETERS!
        );
    }

    interface DetailsPresenter extends BasePresenter<DetailsView> {
        void onCreate(int imageId);
    }

    interface DetailsModel {
        void loadImageData(int id);
    }
}
