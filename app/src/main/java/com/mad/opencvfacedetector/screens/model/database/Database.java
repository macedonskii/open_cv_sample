package com.mad.opencvfacedetector.screens.model.database;

import com.mad.opencvfacedetector.screens.model.database.data.Image;
import com.mad.opencvfacedetector.screens.model.database.data.ImageAndRects;
import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import java.util.List;

public interface Database {

    List<ImageAndRects> getImagesAndRects();
    ImageAndRects getImagesAndRect(long id);

    long saveImage(String path, List<RecognizedRect> rects);

    List<Image> getImages();

    void deleteImage(int id);

}
