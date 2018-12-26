package com.example.windows10.androidmuzej;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvNaslov = (TextView) findViewById(R.id.tvNaslov);

        //DODELA VREDNOSTI ImageButton OBJEKTU
        ImageButton buttonSrpski = findViewById(R.id.btnSrpski);
        ImageButton buttonEngleski = findViewById(R.id.btnEngleski);

        //EVENT LISTENER ZA DUGME
        buttonSrpski.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(v.getContext(),Navigacija.class);

                        //ODABIR JEZIKA
                        Locale locale = new Locale("sr");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.setLocale(locale);
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                        startActivity(i);

                    }
                }
        );
        buttonEngleski.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(v.getContext(),Navigacija.class);

                        //ODABIR JEZIKA
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.setLocale(locale);
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                        startActivity(i);
                    }
                }
        );



    }

}
