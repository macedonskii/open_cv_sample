package com.mad.opencvfacedetector.screens.recognition;

import com.mad.opencvfacedetector.base.BasePresenter;
import com.mad.opencvfacedetector.base.BaseView;

public interface RecognitionContract {

    interface RecognitionView extends BaseView {
        void showDetailsScreen();
    }

    interface RecognitionPresenter extends BasePresenter<RecognitionView> {

        void onImageCaptured(
                // TODO: 21.04.2020 OnImageCaptured input!
        );
    }

    interface RecognitionModel {
        void saveTmpImage(
                // TODO: 21.04.2020 OnImageCaptured input!
        );
    }
}
