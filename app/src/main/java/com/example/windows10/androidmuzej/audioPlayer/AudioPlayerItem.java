package com.example.windows10.androidmuzej.audioPlayer;

public class AudioPlayerItem {

    private String filePath;
    private String itemName;

    public AudioPlayerItem(String filePath, String itemName) {
        this.filePath = filePath;
        this.itemName = itemName;
    }

    public String getFilePath() {
        return filePath;
    }

    String getItemName() {
        return itemName;
    }
}
