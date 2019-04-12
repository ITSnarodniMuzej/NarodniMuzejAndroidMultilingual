package com.example.windows10.androidmuzej.AudioPlayer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

class AudioPlayerItemView extends RecyclerView.ViewHolder {

    private TextView itemSelectedNumber;
    private TextView itemSelectedName;
    private TextView itemUnselectedNumber;
    private TextView itemUnselectedName;

    AudioPlayerItemView(View view)
    {
        super(view);
        this.itemSelectedNumber = view.findViewById(R.id .tvItemSelectedNumber);
        this.itemSelectedName = view.findViewById(R.id.tvItemSelectedName);
        this.itemUnselectedNumber = view.findViewById(R.id .tvItemUnselectedNumber);
        this.itemUnselectedName = view.findViewById(R.id.tvItemUnselectedName);
    }

    TextView getItemSelectedNumber() {
        return itemSelectedNumber;
    }

    TextView getItemSelectedName() {
        return itemSelectedName;
    }

    TextView getItemUnselectedNumber() {
        return itemUnselectedNumber;
    }

    TextView getItemUnselectedName() {
        return itemUnselectedName;
    }
}
