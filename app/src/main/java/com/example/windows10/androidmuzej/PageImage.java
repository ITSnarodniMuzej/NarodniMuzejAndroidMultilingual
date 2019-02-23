package com.example.windows10.androidmuzej;

import android.graphics.drawable.Drawable;

public class PageImage {
    private String imageTitle;
    private Drawable image;

    public PageImage(Drawable image)
    {
        this.image = image;
    }

    public PageImage(String imageTitle, Drawable image) {
        this.imageTitle = imageTitle;
        this.image = image;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    String getImageTitle() {
        return imageTitle;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    Drawable getImage() {
        return image;
    }
}
