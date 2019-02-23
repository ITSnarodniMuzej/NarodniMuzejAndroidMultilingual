package com.example.windows10.androidmuzej;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Page {

    private Context context;
    private int pageId;
    private String text;
    private ArrayList<PageImage> pageImages = new ArrayList<>();

    public Page(){}

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setPageImages(ArrayList<PageImage> pageImages) {
        this.pageImages = pageImages;
    }

    public ArrayList<PageImage> getPageImages() {
        return pageImages;
    }

}
