package com.example.nabee.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Class 1 on 5/4/2018.
 */

public class Slider {
    public float slideX, slideY, slideWidth, slideHeight;
    public RectF slider;
    public Paint paint;

    public Slider(float posX, float posY, float width, float height){
        slideX = posX;
        slideY = posY;
        slideWidth = width;
        slideHeight = height;

    }

    public void drawSlider(Canvas canvas, int color){

        paint = new Paint();
        slider = new RectF(slideX, slideY, slideWidth, slideHeight);
        paint.setColor(color);
        canvas.drawRect(slideX, slideY, slideWidth, slideHeight, paint);
    }
}
