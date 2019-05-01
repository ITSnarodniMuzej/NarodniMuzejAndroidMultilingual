package com.example.windows10.androidmuzej.audioPlayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows10.androidmuzej.R;

import java.util.ArrayList;

public class AudioPlayerAdapter extends RecyclerView.Adapter<AudioPlayerItemView>{

    private ArrayList<AudioPlayerItem> items;
    private Context context;

    public AudioPlayerAdapter(Context context, ArrayList<AudioPlayerItem> items)
    {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public AudioPlayerItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.audio_player_item_layout, parent, false);

        return new AudioPlayerItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioPlayerItemView itemView, int position) {
        AudioPlayerItem item = items.get(position);

        position += 1;

        String itemNumber = position+".";

        itemView.getItemSelectedNumber().setText(itemNumber);
        itemView.getItemSelectedName().setText(item.getItemName());

        itemView.getItemUnselectedNumber().setText(itemNumber);
        itemView.getItemUnselectedName().setText(item.getItemName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
