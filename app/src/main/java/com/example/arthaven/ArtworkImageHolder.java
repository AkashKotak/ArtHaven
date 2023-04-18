package com.example.arthaven;

import java.util.List;

public class ArtworkImageHolder {

        private static ArtworkImageHolder instance;
        private List<Integer> artworkImages;

        public static ArtworkImageHolder getInstance() {
            if (instance == null) {
                instance = new ArtworkImageHolder();
            }
            return instance;
        }

        public List<Integer> getArtworkImages() {
            return artworkImages;
        }

        public void setArtworkImages(List<Integer> artworkImages) {
            this.artworkImages = artworkImages;
        }
}

