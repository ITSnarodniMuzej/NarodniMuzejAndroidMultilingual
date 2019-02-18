package com.example.windows10.androidmuzej;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent = getIntent();
        int roomNumber = intent.getIntExtra("roomNumber", -1);

        Room room = new Room(this, roomNumber);
    }
}
