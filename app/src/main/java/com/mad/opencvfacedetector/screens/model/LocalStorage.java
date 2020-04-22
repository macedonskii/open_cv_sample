package com.mad.opencvfacedetector.screens.model;

import org.opencv.core.Rect;

public interface LocalStorage {

    void saveTmpRects(Rect[] rects);

    Rect[] loadTmpRects();

}
