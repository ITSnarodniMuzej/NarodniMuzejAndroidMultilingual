package com.example.windows10.androidmuzej.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.example.windows10.androidmuzej.R;
import com.example.windows10.androidmuzej.credits.CreditsItem;
import com.example.windows10.androidmuzej.credits.CreditsItemAdapter;
import com.example.windows10.androidmuzej.credits.CreditsItemName;
import com.example.windows10.androidmuzej.credits.CreditsItemNamesAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        fullscreen();

        final ConstraintLayout creditsLayout = findViewById(R.id.creditsLayout);
        fillCredits(creditsLayout);


        ImageButton btnCredits = findViewById(R.id.btnCredits);
        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_in);

                creditsLayout.setVisibility(View.VISIBLE);
                creditsLayout.startAnimation(slideUp);
            }
        });

        ImageButton btnCreditsClose = findViewById(R.id.btnCreditsClose);
        btnCreditsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_out);

                creditsLayout.setVisibility(View.GONE);
                creditsLayout.startAnimation(slideDown);
            }
        });
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

    private void fillCredits(ConstraintLayout creditsLayout)
    {
        int creditsPartiesId = getResources().getIdentifier("credits_parties", "array", getPackageName());
        String[] creditsParties = getResources().getStringArray(creditsPartiesId);

        ArrayList<CreditsItem> items = new ArrayList<>();

        for (String creditsParty : creditsParties) {

            int partyNamesId = getResources().getIdentifier(creditsParty+"_names", "array", getPackageName());
            String[] partyNames = getResources().getStringArray(partyNamesId);

            int partyRolesId = getResources().getIdentifier(creditsParty+"_roles", "array", getPackageName());
            String[] partyRoles = getResources().getStringArray(partyRolesId);

            ArrayList<CreditsItemName> names = new ArrayList<>();
            for(int i=0;i<partyNames.length;i++)
            {
                names.add(new CreditsItemName(partyNames[i], partyRoles[i]));
            }

            int partyTitleId = getResources().getIdentifier(creditsParty+"_title", "string", getPackageName());
            String partyTitle = getResources().getString(partyTitleId);

            int partyTitleLogoId = getResources().getIdentifier(creditsParty+"_logo", "drawable", getPackageName());
            Drawable partyLogo = null;
            try {
                partyLogo = getDrawable(partyTitleLogoId);
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), e.getMessage());
            }

            items.add(new CreditsItem(partyLogo, partyTitle, names));
        }

        CreditsItemAdapter itemAdapter = new CreditsItemAdapter(this, items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView rvCreditsItems = creditsLayout.findViewById(R.id.rvCreditsItems);
        rvCreditsItems.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        rvCreditsItems.addItemDecoration(divider);
        rvCreditsItems.setAdapter(itemAdapter);
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

    private void requestPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_READ_EXTERNAL_STORAGE_RESULT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_READ_EXTERNAL_STORAGE_RESULT) {
            if (permissions.length == 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
            }
        }
    }
}
