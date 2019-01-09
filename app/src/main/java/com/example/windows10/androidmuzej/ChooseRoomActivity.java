package com.example.windows10.androidmuzej;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class ChooseRoomActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);

        //Declaring buttons
        ImageButton btnRoom1Up = findViewById(R.id.btnRoom1Up);
        ImageButton btnRoom1Down = findViewById(R.id.btnRoom1Down);
        ImageButton btnRoom2 = findViewById(R.id.btnRoom2);
        ImageButton btnRoom3 = findViewById(R.id.btnRoom3);
        ImageButton btnRoom4Up = findViewById(R.id.btnRoom4Up);
        ImageButton btnRoom4Down = findViewById(R.id.btnRoom4Down);
        ImageButton btnUp = findViewById(R.id.btnUp);

        //Setting listener
        btnRoom1Up.setOnClickListener(this);
        btnRoom1Down.setOnClickListener(this);
        btnRoom2.setOnClickListener(this);
        btnRoom3.setOnClickListener(this);
        btnRoom4Up.setOnClickListener(this);
        btnRoom4Down.setOnClickListener(this);
        btnUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String roomNumber = v.getContentDescription().toString();

        if(roomNumber.equals("5"))
        {
            Log.i("Room number", "Second floor");
        }
        else
        {
            Log.i("Room number", "Drugi sprat");
//            Intent intent = new Intent(ChooseRoomActivity.this, RoomActivity.class);
//            intent.putExtra("roomNumber", roomNumber);
//            startActivity(intent);
        }
    }
}
