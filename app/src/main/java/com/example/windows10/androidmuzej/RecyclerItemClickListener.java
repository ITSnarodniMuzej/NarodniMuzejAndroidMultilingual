package com.example.windows10.androidmuzej;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    public interface OnItemClickListener
    {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener listener;
    private int previousMotionEvent = 0;

    private float downPos = 0;
    private float upPos = 0;

    public RecyclerItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View view = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downPos = motionEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                upPos = motionEvent.getX();
                break;
        }

        boolean click = downPos == upPos;

        if(view != null && listener != null && click)
        {
            listener.onItemClick(view, recyclerView.getChildAdapterPosition(view));
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
