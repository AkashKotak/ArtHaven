package com.example.arthaven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends AppCompatActivity {

    private RecyclerView userPostsGrid;
    private ImageGridAdapter imageGridAdapter;
    private List<Integer> imageResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_profile);

        userPostsGrid = findViewById(R.id.user_posts_grid);
        userPostsGrid.setLayoutManager(new GridLayoutManager(this, 3));

        // Sample image resources
        imageResources = new ArrayList<>();
        imageResources.add(R.drawable.portfolio_1);
        imageResources.add(R.drawable.portfolio_2);
        imageResources.add(R.drawable.portfolio_3);
        imageResources.add(R.drawable.portfolio_4);
        imageResources.add(R.drawable.portfolio_5);
        imageResources.add(R.drawable.portfolio_6);
        // Add more images to the list...

        imageGridAdapter = new ImageGridAdapter(imageResources);
        userPostsGrid.setAdapter(imageGridAdapter);
    }

    private class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ImageGridViewHolder> {

        private List<Integer> images;

        ImageGridAdapter(List<Integer> images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ImageGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview, parent, false);
            return new ImageGridViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageGridViewHolder holder, int position) {
            holder.bind(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        class ImageGridViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            ImageGridViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
            }

            void bind(Integer imageResource) {
                imageView.setImageResource(imageResource);
            }
        }
    }

}


