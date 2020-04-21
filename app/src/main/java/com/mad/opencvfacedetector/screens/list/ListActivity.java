package com.mad.opencvfacedetector.screens.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mad.opencvfacedetector.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, ListActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

}
