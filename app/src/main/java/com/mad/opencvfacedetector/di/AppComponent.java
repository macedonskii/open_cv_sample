package com.mad.opencvfacedetector.di;

import android.content.Context;

import com.mad.opencvfacedetector.screens.details.DetailPresenter;
import com.mad.opencvfacedetector.screens.list.ListPresenter;
import com.mad.opencvfacedetector.screens.recognition.RecognitionPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ModelModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(RecognitionPresenter recognitionPresenter);

    void inject(DetailPresenter detailPresenter);

    void inject(ListPresenter listPresenter);

}
