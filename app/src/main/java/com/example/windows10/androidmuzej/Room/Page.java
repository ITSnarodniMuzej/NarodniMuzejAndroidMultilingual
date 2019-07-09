package com.example.windows10.androidmuzej.room;

import java.util.ArrayList;

public class Page {

    private int pageNumber;
    private String title = "";
    private String text = "";
    private ArrayList<PageImage> pageImages = new ArrayList<>();

    public Page(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ArrayList<PageImage> getPageImages() {
        return pageImages;
    }

}
