package com.example.windows10.androidmuzej.credits;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows10.androidmuzej.R;

import java.util.ArrayList;

public class CreditsItemNamesAdapter extends RecyclerView.Adapter<CreditsItemNameView> {

    private ArrayList<CreditsItemName> names;
    private Context context;

    CreditsItemNamesAdapter(Context context, ArrayList<CreditsItemName> names)
    {
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public CreditsItemNameView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.credits_item_names, parent, false);
        return new CreditsItemNameView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsItemNameView view, int i) {
        CreditsItemName name = names.get(i);

        view.getTvName().setText(name.getName());
        view.getTvRole().setText(name.getRole());
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
