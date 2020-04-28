package com.mad.opencvfacedetector.screens.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.base.BaseActivity;
import com.mad.opencvfacedetector.screens.details.DetailsActivity;
import com.mad.opencvfacedetector.screens.model.database.data.Image;

import java.util.List;
import java.util.concurrent.Executor;

import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;
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
        presenter.attachView(this);

        recyclerView = findViewById(R.id.rvImages);
        imagesAdapter = new ImagesListAdapter(presenter::onClickImage);

        recyclerView.setAdapter(imagesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        ImageDataSource imageDataSource = new ImageDataSource(presenter::provideData);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        @SuppressLint("WrongThread") PagedList<Image> build = new PagedList.Builder<>(imageDataSource, config)
                .setFetchExecutor(Runnable::run)
                .setNotifyExecutor(Runnable::run)
                .build();

        imagesAdapter.submitList(build);
    }

    @Override
    public void showDetailsScreen(long id) {
        startActivity(DetailsActivity.getIntent(this, id));
    }

    @Override
    public ListContract.ListPresenter getPresenter() {
        return presenter;
    }
}
