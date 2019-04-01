package com.example.windows10.androidmuzej;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class PageImageAdapter extends RecyclerView.Adapter<PageImageAdapter.PageView>{

    private ArrayList<PageImage> images;
    private Context context;

    public PageImageAdapter(Context context, ArrayList<PageImage> resource) {
        this.context = context;
        this.images = resource;
    }

    @NonNull
    @Override
    public PageImageAdapter.PageView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_image_layout, parent, false);

        return new PageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageImageAdapter.PageView pageView, int position) {

        PageImage pageImage = images.get(position);

        if(pageImage.getImageTitle() == null || pageImage.getImageTitle().isEmpty())
        {
            pageView.titleLayout.setVisibility(View.GONE);
        }
        else
        {
            pageView.titleLayout.setVisibility(View.VISIBLE);
            pageView.textView.setText(images.get(position).getImageTitle());
        }

        pageView.imageView.setImageDrawable(images.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class PageView extends RecyclerView.ViewHolder
    {
        private ConstraintLayout titleLayout;
        private TextView textView;
        private ImageView imageView;

        PageView(View view)
        {
            super(view);
            this.titleLayout = view.findViewById(R.id.textLayout);
            this.textView = view.findViewById(R.id.tvPageImageTitle);
            this.imageView = view.findViewById(R.id.ivPageImage);
        }
    }
}
