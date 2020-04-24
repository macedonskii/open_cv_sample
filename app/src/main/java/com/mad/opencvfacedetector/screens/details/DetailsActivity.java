package com.mad.opencvfacedetector.screens.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.base.BaseActivity;

import androidx.annotation.Nullable;

public class DetailsActivity extends BaseActivity implements DetailsContract.DetailsView {

    private static final String EXTRA_ID = "ID";
    private ImageView content;

    public static Intent getIntent(Context context) {
        return new Intent(context, DetailsActivity.class);
    }

    public static Intent getIntent(Context context, int imageId) {
        return getIntent(context).putExtra(EXTRA_ID, imageId);
    }

    private DetailsContract.DetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        content = findViewById(R.id.ivContent);

        presenter = new DetailPresenter();
        presenter.attachView(this);
        presenter.onCreate(getIntent().getIntExtra(EXTRA_ID, -1));
    }

    @Override
    public void showImageData(ImageData data) {
        content.setImageBitmap(data.getOriginal());
    }
}
