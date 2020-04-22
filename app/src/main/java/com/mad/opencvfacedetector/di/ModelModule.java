package com.mad.opencvfacedetector.di;

import android.content.Context;

import com.mad.opencvfacedetector.screens.model.FileStorage;
import com.mad.opencvfacedetector.screens.model.FileStorageImpl;
import com.mad.opencvfacedetector.screens.model.LocalStorage;
import com.mad.opencvfacedetector.screens.model.LocalStorageImpl;
import com.mad.opencvfacedetector.screens.model.Model;
import com.mad.opencvfacedetector.screens.recognition.RecognitionContract;

import javax.inject.Singleton;

import dagger.Provides;

public class ModelModule {

    private FileStorage storage;
    private LocalStorage localStorage;


    @Provides
    @Singleton
    public FileStorage getStorage(Context context) {
        return new FileStorageImpl(context);
    }

    @Provides
    @Singleton
    public LocalStorage getLocalStorage(Context context) {
        return new LocalStorageImpl(context);
    }

    @Provides
    @Singleton
    public Model getModel(FileStorage fileStorage, LocalStorage localStorage) {
        return new Model(fileStorage, localStorage);
    }

    @Provides
    @Singleton
    public RecognitionContract.RecognitionModel getLocalStorage(Model model) {
        return model;
    }


}
