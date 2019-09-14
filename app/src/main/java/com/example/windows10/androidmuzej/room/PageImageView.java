package com.example.windows10.androidmuzej.room;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

class PageImageView extends RecyclerView.ViewHolder
{
    private ConstraintLayout titleLayout;
    private TextView tvTitle;
    private ImageView ivImage;

    PageImageView(final View view)
    {
        super(view);
        this.titleLayout = view.findViewById(R.id.textLayout);
        this.tvTitle = view.findViewById(R.id.tvPageImageTitle);
        this.ivImage = view.findViewById(R.id.ivPageImage);
    }

    ConstraintLayout getTitleLayout() {
        return titleLayout;
    }

    TextView getTvTitle() {
        return tvTitle;
    }

    ImageView getIvImage() {
        return ivImage;
    }

}
