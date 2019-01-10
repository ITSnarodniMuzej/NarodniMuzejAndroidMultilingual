package com.example.windows10.androidmuzej;

public class Room {

    int id;
    String title;
    String text;
    String image;
    String audio;

    public Room(int id, String title, String text, String image, String audio) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.image = image;
        this.audio = audio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
