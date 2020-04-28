package com.mad.opencvfacedetector.screens.model.database;

import com.mad.opencvfacedetector.screens.model.database.data.Image;
import com.mad.opencvfacedetector.screens.model.database.data.ImageAndRects;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface ImagesDao {

    @Transaction
    @Query("SELECT * FROM images")
    List<ImageAndRects> getImagesAndRects();

    @Transaction
    @Query("SELECT * FROM images WHERE id = :id")
    ImageAndRects getImageAndRects(long id);

    @Query("SELECT * FROM images")
    List<Image> getImages();

    @Insert
    long insertImage(Image image);


    @Query("DELETE FROM images WHERE id = :imageId")
    void delete(int imageId);

    @Query("SELECT * FROM images LIMIT :pageSize OFFSET :pageIndex")
    List<Image> getImages(int pageSize, int pageIndex);
}
