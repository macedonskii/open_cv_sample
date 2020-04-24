package com.mad.opencvfacedetector.di;

import android.content.Context;

import com.mad.opencvfacedetector.screens.model.database.Database;
import com.mad.opencvfacedetector.screens.model.database.DatabaseCreator;
import com.mad.opencvfacedetector.screens.model.database.DatabaseImpl;
import com.mad.opencvfacedetector.screens.model.database.ImagesDao;
import com.mad.opencvfacedetector.screens.model.database.RecognizedRectDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public DatabaseCreator getDatabaseCreator(Context context) {
        return Room.databaseBuilder(context, DatabaseCreator.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public ImagesDao getImagesDao(DatabaseCreator creator) {
        return creator.getImageDao();
    }

    @Singleton
    @Provides
    public RecognizedRectDao getRecognizedRectDao(DatabaseCreator creator) {
        return creator.getRecognitionRectDao();
    }

    @Singleton
    @Provides
    public Database getDatabase(RecognizedRectDao recognizedRectDao, ImagesDao imagesDao) {
        return new DatabaseImpl(imagesDao, recognizedRectDao);
    }


}
