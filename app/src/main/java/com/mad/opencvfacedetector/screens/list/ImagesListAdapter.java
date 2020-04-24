package com.mad.opencvfacedetector.screens.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mad.opencvfacedetector.R;
import com.mad.opencvfacedetector.screens.model.database.data.Image;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ImageListHolder> {

    private ClickListener listener;
    private List<Image> items;

    public ImagesListAdapter(ClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageListHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    protected static class ImageListHolder extends RecyclerView.ViewHolder {
        public ImageListHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Image image, ClickListener listener) {
            Glide.with(itemView)
                    .load(image.getPath())
                    .into(((AppCompatImageView) itemView));

            itemView.setOnClickListener(v -> listener.onClick(image));
        }
    }

    public void setItems(List<Image> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public interface ClickListener {
        void onClick(Image image);
    }
}
