package com.example.windows10.androidmuzej.Room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.windows10.androidmuzej.R;

import java.util.ArrayList;

public class PageImageAdapter extends RecyclerView.Adapter<PageImageView>{

    private ArrayList<PageImage> images;
    private Context context;

    public PageImageAdapter(Context context, ArrayList<PageImage> resource) {
        this.context = context;
        this.images = resource;
    }

    @NonNull
    @Override
    public PageImageView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_image_layout, parent, false);

        return new PageImageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageImageView pageView, int position) {

        PageImage pageImage = images.get(position);

        if(pageImage.getImageTitle() == null || pageImage.getImageTitle().isEmpty())
        {
            pageView.getTitleLayout().setVisibility(View.GONE);
        }
        else
        {
            pageView.getTitleLayout().setVisibility(View.VISIBLE);
            pageView.getTextView().setText(images.get(position).getImageTitle());
        }

        pageView.getImageView().setImageDrawable(images.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}