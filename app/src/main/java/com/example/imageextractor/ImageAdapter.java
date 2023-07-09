package com.example.imageextractor;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.imageextractor.databinding.ItemImageBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private final List<DetailImage> list;

    public ImageAdapter(List<DetailImage> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageBinding binding = ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ItemImageBinding binding;
        public ImageViewHolder(@NonNull ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setData(DetailImage detailImage) {
            Glide.with(binding.getRoot().getContext()).load(detailImage.getSourceLink()).into(binding.imageView);
            binding.altTextView.setText(detailImage.getAlt());
            binding.classNameTextView.setText(detailImage.getClassName());
        }
    }
}

