package com.mad.opencvfacedetector.screens.details;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mad.opencvfacedetector.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesHolder> {

    private List<Bitmap> items;

    @NonNull
    @Override
    public ImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImagesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesHolder holder, int position) {
        holder.imageView.setImageBitmap(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<Bitmap> recognizedImages) {
        items = recognizedImages;
        notifyDataSetChanged();
    }

    protected static class ImagesHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageView;

        public ImagesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = ((AppCompatImageView) itemView);
        }
    }
}
