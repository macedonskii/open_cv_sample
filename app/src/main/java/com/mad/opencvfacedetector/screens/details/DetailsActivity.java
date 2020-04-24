package com.mad.opencvfacedetector.screens.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailsActivity extends BaseActivity implements DetailsContract.DetailsView {

    private static final String EXTRA_ID = "ID";
    private ImageView ivContent;
    private ImagesAdapter imagesAdapter;

    public static Intent getIntent(Context context) {
        return new Intent(context, DetailsActivity.class);
    }

    public static Intent getIntent(Context context, long imageId) {
        return getIntent(context).putExtra(EXTRA_ID, imageId);
    }

    private DetailsContract.DetailsPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ivContent = findViewById(R.id.ivContent);
        recyclerView = findViewById(R.id.rvImages);

        imagesAdapter = new ImagesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        recyclerView.setAdapter(imagesAdapter);

        presenter = new DetailPresenter();
        presenter.attachView(this);
        presenter.onCreate(getIntent().getIntExtra(EXTRA_ID, -1));
    }

    @Override
    public void showImageData(ImageData data) {
        ivContent.setImageBitmap(data.getOriginal());
        imagesAdapter.setItems(data.getRecognizedImages());
    }
}
