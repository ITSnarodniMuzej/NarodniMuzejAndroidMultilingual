package com.example.windows10.androidmuzej.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.windows10.androidmuzej.R;

public class ChooseRoomActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);

        //<editor-fold desc="Declaring buttons">
        //First floor
        ImageButton btnRoom1Up = findViewById(R.id.btnRoom1Up);
        ImageButton btnRoom1Down = findViewById(R.id.btnRoom1Down);
        ImageButton btnRoom2 = findViewById(R.id.btnRoom2);
        ImageButton btnRoom3 = findViewById(R.id.btnRoom3);
        ImageButton btnRoom4Up = findViewById(R.id.btnRoom4Up);
        ImageButton btnRoom4Down = findViewById(R.id.btnRoom4Down);
        ImageButton btnUp = findViewById(R.id.btnUp);

        //Second floor
        ImageButton btnDown = findViewById(R.id.btnDown);
        ImageButton btnRoom5Up = findViewById(R.id.btnRoom5Up);
        ImageButton btnRoom5Down = findViewById(R.id.btnRoom5Down);
        ImageButton btnRoom6 = findViewById(R.id.btnRoom6);
        ImageButton btnRoom7 = findViewById(R.id.btnRoom7);
        ImageButton btnRoom8 = findViewById(R.id.btnRoom8);
        ImageButton btnRoom9Up = findViewById(R.id.btnRoom9Up );
        ImageButton btnRoom9Down = findViewById(R.id.btnRoom9Down);
        //</editor-fold>

        //<editor-fold desc="Setting listener">
        //First floor
        btnRoom1Up.setOnClickListener(this);
        btnRoom1Down.setOnClickListener(this);
        btnRoom2.setOnClickListener(this);
        btnRoom3.setOnClickListener(this);
        btnRoom4Up.setOnClickListener(this);
        btnRoom4Down.setOnClickListener(this);
        btnUp.setOnClickListener(this);

        //Second floor
        btnDown.setOnClickListener(this);
        btnRoom5Up.setOnClickListener(this);
        btnRoom5Down.setOnClickListener(this);
        btnRoom6.setOnClickListener(this);
        btnRoom7.setOnClickListener(this);
        btnRoom8.setOnClickListener(this);
        btnRoom9Up.setOnClickListener(this);
        btnRoom9Down.setOnClickListener(this);
        //</editor-fold>
    }

    @Override
    public void onClick(View v) {
        String roomNumber = v.getContentDescription().toString();

        if(roomNumber.equals("stairs"))
        {
            Log.i("Room number", "Floor change");

            ConstraintLayout firstFloor = findViewById(R.id.firstFloorLayout);
            ConstraintLayout secondFloor = findViewById(R.id.secondFloorLayout);

            //Saving current visibilities
            int firstFloorVisibility = firstFloor.getVisibility();
            int secondFloorVisibility = secondFloor.getVisibility();

            //Switching visibilities
            firstFloor.setVisibility(secondFloorVisibility);
            secondFloor.setVisibility(firstFloorVisibility);
        }
        else
        {
            Intent intent = new Intent(ChooseRoomActivity.this, RoomActivity.class);
            intent.putExtra("roomNumber", roomNumber);
            startActivity(intent);
        }
    }
}
