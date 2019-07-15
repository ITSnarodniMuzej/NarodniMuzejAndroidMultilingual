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

    public RecyclerItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View view = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        boolean click = previousMotionEvent == 0 && motionEvent.getAction() == 1;

        if(view != null && listener != null && click)
        {
            listener.onItemClick(view, recyclerView.getChildAdapterPosition(view));
        }
        previousMotionEvent = motionEvent.getAction();

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
