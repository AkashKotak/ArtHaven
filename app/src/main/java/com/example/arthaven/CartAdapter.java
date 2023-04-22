package com.example.arthaven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Artwork> cartItems;

    public CartAdapter(List<Artwork> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Artwork currentItem = cartItems.get(position);

        Glide.with(holder.itemView.getContext())
                .load(currentItem.getImageId())
                .into(holder.artworkImage);

        holder.artworkTitle.setText(currentItem.getTitle());
        holder.artworkPrice.setText(currentItem.getPrice());
        holder.artworkDescription.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView artworkImage;
        TextView artworkTitle;
        TextView artworkPrice;
        TextView artworkDescription;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            artworkImage = itemView.findViewById(R.id.cart_artwork_image);
            artworkTitle = itemView.findViewById(R.id.cart_artwork_title);
            artworkPrice = itemView.findViewById(R.id.cart_artwork_price);
            artworkDescription = itemView.findViewById(R.id.cart_artwork_description);
        }
    }
}
