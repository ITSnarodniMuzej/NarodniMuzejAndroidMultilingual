package com.example.windows10.androidmuzej.AudioPlayer;

public class AudioPlayerItem {

    private int fileId;
    private String itemName;

    public AudioPlayerItem(int fileId, String itemName) {
        this.fileId = fileId;
        this.itemName = itemName;
    }

    public int getFileId() {
        return fileId;
    }

    String getItemName() {
        return itemName;
    }
}
