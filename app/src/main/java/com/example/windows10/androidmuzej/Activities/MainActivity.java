package com.example.windows10.androidmuzej.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

import com.example.windows10.androidmuzej.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring buttons
        ImageButton btnSr = findViewById(R.id.btnSr);
        ImageButton btnEn = findViewById(R.id.btnEn);
        ImageButton btnTr = findViewById(R.id.btnTr);
        ImageButton btnFr = findViewById(R.id.btnFr);
        ImageButton btnRu = findViewById(R.id.btnRu);
        ImageButton btnDe = findViewById(R.id.btnDe);

        //Setting listener
        btnSr.setOnClickListener(this);
        btnEn.setOnClickListener(this);
        btnTr.setOnClickListener(this);
        btnFr.setOnClickListener(this);
        btnRu.setOnClickListener(this);
        btnDe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Getting language code from ImageView
        String language = v.getContentDescription().toString();

        //Defining localisation
        Locale locale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        //Setting local
        conf.setLocale(locale);
        res.updateConfiguration(conf, dm);

        //Starting activity
        Intent refresh = new Intent(this, ChooseRoomActivity.class);
        startActivity(refresh);
    }
}
