package com.example.windows10.androidmuzej.room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10.androidmuzej.R;

public class ZoomPhotoFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_layout, container, false);

        //providna pozadina
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //resenje za bug pojavljivanja navigation bar-a u full screen modu
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getDialog().getWindow().getDecorView().setSystemUiVisibility(getActivity().getWindow().getDecorView().getSystemUiVisibility());
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                //clear flag
                getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

                WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                wm.updateViewLayout(getDialog().getWindow().getDecorView(), getDialog().getWindow().getAttributes());
            }
        });

        Bundle bundle = getArguments();

        //preuzimanje slike  od recyclerview-a
        Bitmap bitmapimage = bundle.getParcelable("image_bitmap");
        ImageView image = v.findViewById(R.id.imageDialogFragment);
        image.setImageBitmap(bitmapimage);

        //preuzimanje naslova slike od recyclerview-a
        String detail = bundle.getString("detail");
        TextView tvTitle = v.findViewById(R.id.imageTitleDialogFragment);
        tvTitle.setText(detail);

        //zatvaranje prozora
        ImageButton close = v.findViewById(R.id.buttonCloseDialog);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder mBuild=new AlertDialog.Builder(getActivity());
        mBuild.setView(v);
        mBuild.create();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null)
        {
            dialog.getWindow().setWindowAnimations(R.style.MyAnimation_Window);
        }
    }

    @Override
    public void onPause(){
        getDialog().dismiss();
        super.onPause();


    }

}
