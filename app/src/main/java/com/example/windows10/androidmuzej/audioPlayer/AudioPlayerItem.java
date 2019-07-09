package com.example.windows10.androidmuzej.audioPlayer;

import java.io.File;

public class AudioPlayerItem {

    private File file;
    private String itemName;

    public AudioPlayerItem(File file, String itemName) {
        this.file = file;
        this.itemName = itemName;
    }

    public File getFile() {
        return file;
    }

    String getItemName() {
        return itemName;
    }
}
