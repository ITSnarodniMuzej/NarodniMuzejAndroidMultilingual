package com.example.windows10.androidmuzej.room;

import android.graphics.drawable.Drawable;

public class PageImage {
    private String imageTitle;
    private Drawable image;

    public PageImage(Drawable image, String title)
    {
        this.image = image;
        this.imageTitle = title;
    }

    String getImageTitle() {
        return imageTitle;
    }

    Drawable getImage() {
        return image;
    }
}
