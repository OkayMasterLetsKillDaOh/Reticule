package com.example.reticule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.reticule.Controller.ToucheListener;
import com.example.reticule.View.Ret;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ret retView = new Ret(this);
        retView.setOnTouchListener(new ToucheListener());
    }

}