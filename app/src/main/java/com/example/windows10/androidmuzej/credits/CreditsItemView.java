package com.example.windows10.androidmuzej.credits;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

class CreditsItemView extends RecyclerView.ViewHolder {

    private ImageView ivLogo;
    private TextView tvTitle;
    private RecyclerView rvNames;

    CreditsItemView(@NonNull View view) {
        super(view);

        ivLogo = view.findViewById(R.id.ivCreditsItemLogo);
        tvTitle = view.findViewById(R.id.tvCreditsItemTitle);
        rvNames = view.findViewById(R.id.rvCreditsNames);
    }

    ImageView getIvLogo() {
        return ivLogo;
    }

    TextView getTvTitle() {
        return tvTitle;
    }

    RecyclerView getRvNames() {
        return rvNames;
    }
}
