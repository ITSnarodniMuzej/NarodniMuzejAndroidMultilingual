package com.example.windows10.androidmuzej;

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

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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

    public void setPageImages(ArrayList<PageImage> pageImages) {
        this.pageImages = pageImages;
    }

    public ArrayList<PageImage> getPageImages() {
        return pageImages;
    }

}
