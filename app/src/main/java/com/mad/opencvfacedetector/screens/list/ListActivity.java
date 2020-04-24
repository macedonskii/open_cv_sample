package com.mad.opencvfacedetector.screens.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.base.BaseActivity;
import com.mad.opencvfacedetector.screens.details.DetailsActivity;
import com.mad.opencvfacedetector.screens.model.database.data.Image;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends BaseActivity implements ListContract.ListView {

    public static Intent getIntent(Context context) {
        return new Intent(context, ListActivity.class);
    }


    private RecyclerView recyclerView;
    private ImagesListAdapter imagesAdapter;
    private ListContract.ListPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenter();

        recyclerView = findViewById(R.id.rvImages);
        imagesAdapter = new ImagesListAdapter(presenter::onClickImage);

        recyclerView.setAdapter(imagesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));


        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void setData(List<Image> list) {
        imagesAdapter.setItems(list);
    }

    @Override
    public void showDetailsScreen(long id) {
        startActivity(DetailsActivity.getIntent(this, id));
    }
}
