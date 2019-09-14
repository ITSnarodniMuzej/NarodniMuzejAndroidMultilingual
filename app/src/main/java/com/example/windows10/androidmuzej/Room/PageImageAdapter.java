package com.example.windows10.androidmuzej.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.windows10.androidmuzej.R;

import java.util.ArrayList;

public class PageImageAdapter extends RecyclerView.Adapter<PageImageView>{

    private ArrayList<PageImage> images;
    private Context context;
    private boolean bigImage;

    public PageImageAdapter(Context context, ArrayList<PageImage> resource, boolean bigImage) {
        this.context = context;
        this.images = resource;
        this.bigImage = bigImage;
    }

    @NonNull
    @Override
    public PageImageView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(bigImage)
            view = LayoutInflater.from(context).inflate(R.layout.page_image_layout_big, parent, false);
        else
            view = LayoutInflater.from(context).inflate(R.layout.page_image_layout, parent, false);

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
            pageView.getTvTitle().setText(pageImage.getImageTitle());

        }

        pageView.getIvImage().setImageDrawable(pageImage.getImage());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}
