package com.mad.opencvfacedetector.screens.model.database.data;


import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;


public class ImageAndRects {

    @Embedded
    private Image image;

    @Relation(parentColumn = "id", entityColumn = "image_id")
    private List<RecognizedRect> rects;

    public Image getImage() {
        return image;
    }

    public List<RecognizedRect> getRects() {
        return rects;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setRects(List<RecognizedRect> rects) {
        this.rects = rects;
    }
}
