package com.example.reticule.Controller;

import android.view.MotionEvent;
import android.view.View;

import com.example.reticule.View.Ret;

public class ToucheListener implements View.OnTouchListener {

    float mX;
    float mY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        mX = v.getX();
        mY = v.getY();

        if(v instanceof Ret){
            Ret ret = (Ret) v;
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                mX = ret.getX();
                mY = ret.getY();

                ret.setPos(mX,mY);
            }
        }
        return false;
    }

    public void update(View v, float newX, float newY) {
        mX = newX;
        mY = newY;

    }
}
