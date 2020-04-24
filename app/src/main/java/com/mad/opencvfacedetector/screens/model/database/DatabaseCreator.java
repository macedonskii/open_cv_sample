package com.mad.opencvfacedetector.screens.model.database;

import com.mad.opencvfacedetector.screens.model.database.data.Image;
import com.mad.opencvfacedetector.screens.model.database.data.ImageAndRects;
import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Image.class, RecognizedRect.class}, version = 1)
public abstract class DatabaseCreator extends RoomDatabase {

    public abstract ImagesDao getImageDao();

    public abstract RecognizedRectDao getRecognitionRectDao();

}
