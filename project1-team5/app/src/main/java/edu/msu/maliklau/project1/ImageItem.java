package edu.msu.maliklau.project1;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;

    public ImageItem(Bitmap image, String title) {
        super();
        this.image = image;
        // setAdjustViewBounds(true)
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
