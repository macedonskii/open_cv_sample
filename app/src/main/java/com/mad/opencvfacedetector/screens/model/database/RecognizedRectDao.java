package com.mad.opencvfacedetector.screens.model.database;

import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface RecognizedRectDao {

    @Insert
    void insert(List<RecognizedRect> list);

}
