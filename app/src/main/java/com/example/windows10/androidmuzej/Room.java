package com.example.windows10.androidmuzej;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Room {

    private int id;
    private String title = "";
    private String text = "";
    private Drawable logo;
    private ArrayList<Drawable> image = new ArrayList<>();

    private Context context;

    public Room(Context context, int id)
    {
        this.context = context;
        this.id = id;

        createRoom(id);
    }

    private void createRoom(int id)
    {
        switch (id)
        {
            case 1:
                createRoom1();
                break;
        }
    }

    private void createRoom1()
    {
        this.title = context.getString(R.string.app_name);
        this.text = context.getString(R.string.app_name);
        this.logo = context.getDrawable(R.drawable.logo);

        Drawable image1 = context.getDrawable(R.drawable.logo);
        this.image.add(image1);
    }
}
