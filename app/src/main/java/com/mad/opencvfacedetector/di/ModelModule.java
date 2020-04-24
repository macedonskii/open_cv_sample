package com.mad.opencvfacedetector.di;

import android.content.Context;

import com.mad.opencvfacedetector.screens.details.DetailsContract;
import com.mad.opencvfacedetector.screens.list.ListContract;
import com.mad.opencvfacedetector.screens.model.BitmapUtils;
import com.mad.opencvfacedetector.screens.model.BitmapUtilsImpl;
import com.mad.opencvfacedetector.screens.model.FileStorage;
import com.mad.opencvfacedetector.screens.model.FileStorageImpl;
import com.mad.opencvfacedetector.screens.model.LocalStorage;
import com.mad.opencvfacedetector.screens.model.LocalStorageImpl;
import com.mad.opencvfacedetector.screens.model.Model;
import com.mad.opencvfacedetector.screens.model.database.Database;
import com.mad.opencvfacedetector.screens.recognition.RecognitionContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {


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
    public BitmapUtils getBitmapUtils() {
        return new BitmapUtilsImpl();
    }

    @Provides
    @Singleton
    public Model getModel(FileStorage fileStorage, LocalStorage localStorage, BitmapUtils utils, Database database) {
        return new Model(fileStorage, localStorage, utils, database);
    }

    @Provides
    @Singleton
    public RecognitionContract.RecognitionModel getRecognitionModel(Model model) {
        return model;
    }

    @Provides
    @Singleton
    public DetailsContract.DetailsModel getDetailsModel(Model model) {
        return model;
    }

    @Provides
    @Singleton
    public ListContract.ListModel getListModel(Model model) {
        return model;
    }


}
