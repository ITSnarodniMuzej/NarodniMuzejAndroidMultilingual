package com.example.windows10.androidmuzej.credits;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows10.androidmuzej.R;

import java.util.ArrayList;

public class CreditsItemAdapter extends RecyclerView.Adapter<CreditsItemView> {

    private Context context;
    private ArrayList<CreditsItem> items;


    public CreditsItemAdapter(Context context, ArrayList<CreditsItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CreditsItemView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.credits_item, parent, false);

        return new CreditsItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsItemView view, int i) {
        CreditsItem item = items.get(i);

        if(item != null) {

            if(item.getLogo() == null)
                view.getIvLogo().setVisibility(View.GONE);
            else
                view.getIvLogo().setImageDrawable(item.getLogo());

            view.getTvTitle().setText(item.getTitle());

            CreditsItemNamesAdapter adapter = new CreditsItemNamesAdapter(context, item.getNames());

            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            view.getRvNames().setLayoutManager(layoutManager);
            view.getRvNames().setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
