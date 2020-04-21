package com.mad.opencvfacedetector.screens.main;

import android.os.Bundle;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.screens.list.ListActivity;
import com.mad.opencvfacedetector.screens.recognition.ImageRecognitionActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btnList).setOnClickListener(v -> startActivity(ListActivity.getIntent(this)));
        findViewById(R.id.btnStart).setOnClickListener(v -> startActivity(ImageRecognitionActivity.getIntent(this)));
    }
}
