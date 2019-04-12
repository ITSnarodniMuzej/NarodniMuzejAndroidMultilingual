package com.example.windows10.androidmuzej.Room;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

class PageImageView extends RecyclerView.ViewHolder
{
    private ConstraintLayout titleLayout;
    private TextView textView;
    private ImageView imageView;

    PageImageView(View view)
    {
        super(view);
        this.titleLayout = view.findViewById(R.id.textLayout);
        this.textView = view.findViewById(R.id.tvPageImageTitle);
        this.imageView = view.findViewById(R.id.ivPageImage);
    }

    ConstraintLayout getTitleLayout() {
        return titleLayout;
    }

    TextView getTextView() {
        return textView;
    }

    ImageView getImageView() {
        return imageView;
    }
}
