package com.mad.opencvfacedetector.screens.model.database;

import com.mad.opencvfacedetector.screens.model.database.data.Image;
import com.mad.opencvfacedetector.screens.model.database.data.ImageAndRects;
import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import java.util.List;

public interface Database {

    List<ImageAndRects> getImagesAndRects();
    ImageAndRects getImagesAndRect(int id);

    long saveImage(String path, List<RecognizedRect> rects);

    List<Image> getImagesPaths();

    void deleteImage(int id);

}
