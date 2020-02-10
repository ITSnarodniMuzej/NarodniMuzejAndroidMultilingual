package com.example.windows10.androidmuzej.credits;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

class CreditsItemNameView extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvRole;

    CreditsItemNameView(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvCreditsName);
        tvRole = itemView.findViewById(R.id.tvCreditsRole);
    }

    TextView getTvName() {
        return tvName;
    }

    TextView getTvRole() {
        return tvRole;
    }
}
