package com.mad.opencvfacedetector.screens.model.database;

import com.mad.opencvfacedetector.screens.model.database.data.Image;
import com.mad.opencvfacedetector.screens.model.database.data.ImageAndRects;
import com.mad.opencvfacedetector.screens.model.database.data.RecognizedRect;

import java.util.List;

public class DatabaseImpl implements Database {

    private ImagesDao imagesDao;
    private RecognizedRectDao recognizedRectDao;

    public DatabaseImpl(ImagesDao imagesDao, RecognizedRectDao recognizedRectDao) {
        this.imagesDao = imagesDao;
        this.recognizedRectDao = recognizedRectDao;
    }

    @Override
    public ImageAndRects getImagesAndRect(long id) {
        return imagesDao.getImageAndRects(id);
    }

    @Override
    public long saveImage(String path, List<RecognizedRect> rects) {
        long imageId = imagesDao.insertImage(new Image(path));
        for (RecognizedRect rect : rects) {
            rect.setImageId(imageId);
        }

        recognizedRectDao.insert(rects);
        return imageId;
    }

    @Override
    public List<ImageAndRects> getImagesAndRects() {
        return imagesDao.getImagesAndRects();
    }

    @Override
    public List<Image> getImagesPaths() {
        return imagesDao.getImages();
    }

    @Override
    public void deleteImage(int id) {
        imagesDao.delete(id);
    }
}
