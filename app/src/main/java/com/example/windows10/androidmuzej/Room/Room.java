package com.example.windows10.androidmuzej.room;

import android.graphics.drawable.Drawable;

import com.example.windows10.androidmuzej.audioPlayer.AudioPlayerItem;

import java.util.ArrayList;

public class Room {

    private int roomNumber;
    private String title = "";
    private Drawable logo;
    private ArrayList<Page> pages = new ArrayList<>();
    private ArrayList<AudioPlayerItem> audioItems = new ArrayList<>();


    public Room(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public ArrayList<AudioPlayerItem> getAudioItems() {
        return audioItems;
    }

}
