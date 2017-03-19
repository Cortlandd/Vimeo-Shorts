package com.app.shortoftheweek.classes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class VideoRecyclerView extends RecyclerView {
    private boolean mScrollable;

    // TODO: Create comment here
    public VideoRecyclerView(Context context) {
        this(context, null);
    }

    // TODO: Create comment here
    public VideoRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // TODO: Create comment here
    public VideoRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScrollable = false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // Make RecyclerView inside MainActivity scrollable
        return !mScrollable || super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO: Create comment here
        super.onLayout(changed, l, t, r, b);

        // TODO: Create comment here
        for (int i = 0; i < getChildCount(); i++) {
            //animate(getChildAt(i), i);

            if (i == getChildCount() - 1) {
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollable = true;
                    }
                }, i * 100);
            }
        }
    }

    // @Removed
    // Animation when fetching videos in MainActivity
//    private void animate(View view, final int pos) {
//        view.animate().cancel();
//        view.setTranslationY(100);
//        view.setAlpha(0);
//        view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(pos * 100);
//    }
}
