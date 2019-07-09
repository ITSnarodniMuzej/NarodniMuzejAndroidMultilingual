package com.example.windows10.androidmuzej.room;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.windows10.androidmuzej.R;

class PageImageView extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private ConstraintLayout titleLayout;
    private TextView textView;
    private ImageView imageView;

    PageImageView(final View view)
    {
        super(view);
        this.titleLayout = view.findViewById(R.id.textLayout);
        this.textView = view.findViewById(R.id.tvPageImageTitle);
        this.imageView = view.findViewById(R.id.ivPageImage);
        imageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        Fragment prev = activity.getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        //vrednosti bitmap slike i naslov slike
        Bitmap bm = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        String s = "";
        if(!textView.getText().equals("TextView"))
            s = textView.getText().toString();

        Bundle args = new Bundle();
        args.putParcelable("image_bitmap",bm);
        args.putString("title",s);

        //prosledjivanje bundle-a novom dialog fragmentu
        ZoomPhotoFragment dialogFragment = new ZoomPhotoFragment();
        dialogFragment.setArguments(args);
        dialogFragment.show(ft, "dialog");

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
