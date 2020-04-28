package com.mad.opencvfacedetector.base;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() == null) {
            return;
        }

        getPresenter().detachView();
    }
}
