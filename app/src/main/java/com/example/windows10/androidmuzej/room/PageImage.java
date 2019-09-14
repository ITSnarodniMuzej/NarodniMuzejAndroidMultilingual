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

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageDetail() { return  imageDetail; }

    public Drawable getImage() {
        return image;
    }
}
