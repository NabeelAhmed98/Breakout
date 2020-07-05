package com.example.nabee.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Class 1 on 5/4/2018.
 */

public class Bricks {

    public float rectX, rectY, rectWidth, rectHeight;
    public RectF brick;
    public Paint paint;
    public int color;
    public boolean active = true;

    public Bricks(float posX, float posY, float width, float height){
        rectX = posX;
        rectY = posY;
        rectWidth = width;
        rectHeight = height;
        color = Color.BLACK;
    }

    public void draw(Canvas canvas, int color){
        if(!active){
            return;
        }
        paint = new Paint();
        brick = new RectF(rectX, rectY, rectWidth, rectHeight);
        paint.setColor(color);
        canvas.drawRect(rectX, rectY, rectWidth, rectHeight, paint);
    }

    public void setColor(int aColor){
        color = aColor;
    }

}
