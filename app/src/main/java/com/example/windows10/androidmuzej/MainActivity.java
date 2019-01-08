package com.example.windows10.androidmuzej;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DODELA VREDNOSTI ImageButton OBJEKTU
        ImageButton btnSrpski = findViewById(R.id.btnSr);
        ImageButton btnEngleski = findViewById(R.id.btnEngleski);
        ImageButton btnTurski = findViewById(R.id.btnTurski);
        ImageButton btnFrancuski = findViewById(R.id.btnFrancuski);
        ImageButton btnRuski = findViewById(R.id.btnRuski);
        ImageButton btnNemacki = findViewById(R.id.btnNemacki);


        btnSrpski.setOnClickListener(this);
        btnEngleski.setOnClickListener(this);
        btnTurski.setOnClickListener(this);
        btnFrancuski.setOnClickListener(this);
        btnRuski.setOnClickListener(this);
        btnNemacki.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Getting language code from ImageView
        String language = v.getContentDescription().toString();

        Locale locale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(locale);
        res.updateConfiguration(conf, dm);

        Intent refresh = new Intent(this, ChooseRoomActivity.class);
        startActivity(refresh);
    }
}
