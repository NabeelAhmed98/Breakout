package com.example.nabee.breakout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BreakoutView breakoutView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        breakoutView = new BreakoutView(this);
        setContentView(breakoutView);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        int code = event.getKeyCode();
        breakoutView.KeyStruck(code);
        return super.onKeyDown(keyCode,event);
    }

}
