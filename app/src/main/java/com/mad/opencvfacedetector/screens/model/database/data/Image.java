package com.mad.opencvfacedetector.screens.model.database.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "images")
public class Image {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String path;

    public Image() {
    }

    public Image(String path) {
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
