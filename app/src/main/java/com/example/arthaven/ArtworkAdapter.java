package com.example.arthaven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ArtworkAdapter extends BaseAdapter {
    private Context context;
    private int[] artworkImages;

    public ArtworkAdapter(Context context, int[] artworkImages) {
        this.context = context;
        this.artworkImages = artworkImages;
    }

    @Override
    public int getCount() {
        return artworkImages.length;
    }

    @Override
    public Object getItem(int position) {
        return artworkImages[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.artwork_item, parent, false);
        }

        ImageView artworkImageView = convertView.findViewById(R.id.artworkImageView);
        artworkImageView.setImageResource(artworkImages[position]);

        return convertView;
    }
}
