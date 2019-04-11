package com.example.windows10.androidmuzej.AudioPlayer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

public class AudioItemView extends RecyclerView.ViewHolder {

    private TextView itemNumber;
    private ImageView audioLogo;
    private TextView itemName;

    AudioItemView(View view)
    {
        super(view);
        this.itemNumber = view.findViewById(R.id.tvItemNumber);
        this.audioLogo = view.findViewById(R.id.ivAudioLogo);
        this.itemName = view.findViewById(R.id.tvItemName);
    }
}
