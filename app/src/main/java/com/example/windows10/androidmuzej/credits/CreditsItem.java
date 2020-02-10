package com.example.windows10.androidmuzej.credits;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class CreditsItem {

    private Drawable logo;
    private String title;
    private ArrayList<CreditsItemName> names;

    public CreditsItem(Drawable logo, String title, ArrayList<CreditsItemName> names) {
        this.logo = logo;
        this.title = title;
        this.names = names;
    }

    Drawable getLogo() {
        return logo;
    }

    String getTitle() {
        return title;
    }

    ArrayList<CreditsItemName> getNames() {
        return names;
    }
}
