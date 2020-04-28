package com.mad.opencvfacedetector.screens.list;

import com.mad.opencvfacedetector.screens.model.database.data.Image;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

public class ImageDataSource extends PositionalDataSource<Image> {

    private DataProvider provider;

    public ImageDataSource(DataProvider provider) {
        this.provider = provider;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Image> callback) {
        provider.loadData(params.requestedStartPosition, params.pageSize, list -> {
            callback.onResult(list, params.requestedStartPosition);
        });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Image> callback) {
        provider.loadData(params.startPosition, params.loadSize, callback::onResult);
    }

    public interface DataCallback {
        void onDataLoaded(List<Image> list);
    }

    public interface DataProvider {
        void loadData(int startPosition, int count, DataCallback callback);
    }


}
