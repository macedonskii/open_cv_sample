package com.mad.opencvfacedetector.screens.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import org.opencv.core.Rect;

public class LocalStorageImpl implements LocalStorage {

    private static final String PREFERENCES_NAME = "Make_some_name";
    private static final String TAG_ARRAY = "ARRAY";
    private SharedPreferences preferences;

    public LocalStorageImpl(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveTmpRects(Rect[] rects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rect rect : rects) {
            stringBuilder.append(rect.x).append(":").append(rect.y).append(":").append(rect.width).append(":").append(rect.height).append(":");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        preferences.edit().putString(TAG_ARRAY, stringBuilder.toString()).commit();
    }

    @Override
    public Rect[] loadTmpRects() {
        String string = preferences.getString(TAG_ARRAY, null);
        if (string == null) {
            return new Rect[0];
        }

        String[] split = string.split(":");
        Rect[] rects = new Rect[split.length / 4];
        for (int i = 0; i < split.length; i += 4) {
            rects[i / 4] = new Rect(Integer.parseInt(split[i]), Integer.parseInt(split[i + 1]), Integer.parseInt(split[i + 2]), Integer.parseInt(split[i + 3]));
        }

        return rects;
    }
}
