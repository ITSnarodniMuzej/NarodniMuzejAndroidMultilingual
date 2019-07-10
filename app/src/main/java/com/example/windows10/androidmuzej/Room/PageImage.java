package com.example.windows10.androidmuzej.room;

import android.graphics.drawable.Drawable;

public class PageImage {
    private String imageTitle;
    private String imageDetail;
    private Drawable image;

    public PageImage(Drawable image, String title, String detail)
    {
        this.image = image;
        this.imageTitle = title;
        this.imageDetail = detail;
    }

    String getImageTitle() {
        return imageTitle;
    }

    String getImageDetail() { return  imageDetail; }

    Drawable getImage() {
        return image;
    }
}
