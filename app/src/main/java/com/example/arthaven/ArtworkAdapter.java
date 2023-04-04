package com.example.arthaven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder> {

    private List<Integer> artworkImages;

    public ArtworkAdapter(List<Integer> artworkImages) {
        this.artworkImages = artworkImages;
    }

    @NonNull
    @Override
    public ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artwork_item, parent, false);
        return new ArtworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewHolder holder, int position) {
        Context context = holder.artworkImageView.getContext();
        int customWidth = 2000;
        int customHeight = 2000;
        Glide.with(context)
                .load(artworkImages.get(position))
                .override(customWidth, customHeight) // Set custom width and height
                .centerCrop() // Maintain aspect ratio while resizing
                .into(holder.artworkImageView);
    }

    @Override
    public int getItemCount() {
        return artworkImages.size();
    }

    class ArtworkViewHolder extends RecyclerView.ViewHolder {
        ImageView artworkImageView;

        ArtworkViewHolder(@NonNull View itemView) {
            super(itemView);
            artworkImageView = itemView.findViewById(R.id.artworkImageView);
        }
    }
}
