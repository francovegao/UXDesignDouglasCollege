package com.example.viewdemo;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomTouchListener implements View.OnTouchListener {
    GestureDetector gestureDetector; //needs context and GestureListener
    Context context;

    public CustomTouchListener(Context context) {
        this.context = context;
        gestureDetector = new GestureDetector(context,
                            new CustomGestureListener());
    }

    public class CustomGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            float SWIPE_DIST_THRESHOLD = 10;
            float SWIPE_VEL_THRESHOLD = 20;

            float XOff = e2.getX() - e1.getX();
            float YOff = e2.getY() - e1.getY();

            if (Math.abs(XOff) > Math.abs(YOff)
                    && Math.abs(XOff) > SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //horizontal swipe
                if (XOff > 0){
                    //right Swipe
                    onRightSwipe();
                } else {
                    onLeftSwipe();
                }

            } else if (Math.abs(YOff) > Math.abs(XOff)
                    && Math.abs(YOff) > SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD) {
                //vertical swipe
                if (YOff > 0){
                    onDownSwipe();
                } else {
                    onUpSwipe();
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            //return super.onDown(e); //default base class returns false
            return true;
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onUpSwipe() {
    }

    public void onDownSwipe() {
    }

    public void onLeftSwipe() {
    }

    public void onRightSwipe() {
    }

    public void onSingleClick() {
    }

    public void onDoubleClick() {
    }

    public void onLongClick() {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //return false;
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
