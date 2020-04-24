package com.mad.opencvfacedetector.screens.model.database.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "recognized_rects",
        foreignKeys = @ForeignKey(entity = Image.class, parentColumns = {"id"}, childColumns = {"image_id"})
)
public class RecognizedRect {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "image_id")
    private long imageId;
    private int x;
    private int y;
    private int width;
    private int height;

    public RecognizedRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
