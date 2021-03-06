package com.example.windows10.androidmuzej.activities;

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

        fullscreen();

        //<editor-fold desc="Declaring buttons">
        //First floor
        ImageButton btnRoom1Up = findViewById(R.id.btnRoom1Up);
        ImageButton btnRoom1Down = findViewById(R.id.btnRoom1Down);
        ImageButton btnRoom2Up = findViewById(R.id.btnRoom2Up);
        ImageButton btnRoom2Down = findViewById(R.id.btnRoom2Down);
        ImageButton btnRoom3 = findViewById(R.id.btnRoom3);
        ImageButton btnRoom4 = findViewById(R.id.btnRoom4);
        ImageButton btnUp = findViewById(R.id.btnUp);
        ImageButton btnBack = findViewById(R.id.btnBackLanguage);

        //Second floor
        ImageButton btnDown = findViewById(R.id.btnDown);
        ImageButton btnRoom5Up = findViewById(R.id.btnRoom5Up);
        ImageButton btnRoom5Down = findViewById(R.id.btnRoom5Down);
        ImageButton btnRoom6Up = findViewById(R.id.btnRoom6Up);
        ImageButton btnRoom6Down = findViewById(R.id.btnRoom6Down);
        ImageButton btnRoom7 = findViewById(R.id.btnRoom7);
        ImageButton btnRoom8 = findViewById(R.id.btnRoom8);
        ImageButton btnRoom9 = findViewById(R.id.btnRoom9);
        //</editor-fold>

        //<editor-fold desc="Setting listener">
        //First floor
        btnRoom1Up.setOnClickListener(this);
        btnRoom1Down.setOnClickListener(this);
        btnRoom4.setOnClickListener(this);
        btnRoom3.setOnClickListener(this);
        btnRoom2Up.setOnClickListener(this);
        btnRoom2Down.setOnClickListener(this);
        btnUp.setOnClickListener(this);

        //Second floor
        btnDown.setOnClickListener(this);
        btnRoom5Up.setOnClickListener(this);
        btnRoom5Down.setOnClickListener(this);
        btnRoom6Up.setOnClickListener(this);
        btnRoom6Down.setOnClickListener(this);
        btnRoom7.setOnClickListener(this);
        btnRoom8.setOnClickListener(this);
//        btnRoom9.setOnClickListener(this);
        //</editor-fold>
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String roomNumber = v.getContentDescription().toString();

        if(roomNumber.equals("stairs"))
        {
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
            try {
                Integer.parseInt(roomNumber);

                Intent intent = new Intent(ChooseRoomActivity.this, RoomActivity.class);
                intent.putExtra("roomNumber", roomNumber);
                startActivity(intent);

            } catch (NumberFormatException e) {
                Log.e(getClass().getSimpleName(), e.getMessage());
            }


        }
    }

    public void fullscreen()
    {
        final View view = getWindow().getDecorView();

        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        //Hide navigation bar when visibility has changed
        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                view.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN
                );

            }
        });
    }
}
