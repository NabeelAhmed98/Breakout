package com.example.nabee.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Class 1 on 5/4/2018.
 */

public class Ball {
    public float ballRadius = 20; // Ball's radius
    public float ballX;
    public float ballY;
    public float ballSpeedX;  // Ball's speed (x,y)
    public float ballSpeedY;
    public float startingX;
    public float startingY;
    RectF ballBounds;      // Needed for Canvas.drawOval
    Paint paint;

    public Ball(float inx, float iny, float dx, float dy) {
        ballX = inx;
        ballY = iny;
        ballSpeedX = dx;
        ballSpeedY = dy;
        startingX = inx;
        startingY = iny;
    }

    public void drawBall(Canvas canvas, int color) {

        ballBounds = new RectF();
        paint = new Paint();
        paint.setColor(color);
        ballBounds.set(ballX, ballY, ballX + ballRadius * 2, ballY + ballRadius * 2);

        canvas.drawOval(ballBounds, paint);
    }

    public boolean collide(Bricks brick) {
        // I am to the right of you
        if (this.ballX > brick.rectWidth) {
            return false;
        }
        // I am to the left of you
        if (this.ballX + ballRadius < brick.rectX) {
            return false;
        }
        // I am on top of you
        if (this.ballY + ballRadius < brick.rectY) {
            return false;
        }
        // I am below you
        if (this.ballY > brick.rectHeight) {
            return false;
        }
        if (!brick.active){
            return false;
        }
        return true;
    }

    public boolean reflect(Slider slider) {
        // I am to the right of you
        if (this.ballX > slider.slideWidth) {
            return false;
        }
        // I am to the left of you
        if (this.ballX + ballRadius < slider.slideX) {
            return false;
        }
        // I am on top of you
        if (this.ballY + ballRadius < slider.slideY) {
            return false;
        }
        // I am below you
        if (this.ballY > slider.slideHeight) {
            return false;
        }
        return true;
    }

    public int update(int xMax, int yMax) {
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        // Detect collision and react
        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax-ballRadius;
            return 0;
        } else if (ballX - ballRadius < 0) {
            ballSpeedX = -ballSpeedX;
            ballX = 0+ballRadius;
            return 0;
        }
        if (ballY + ballRadius < 0) {
            ballSpeedY = -ballSpeedY;
            ballY = 0 + ballRadius;
            return 0;
        } else if (ballY - ballRadius > yMax) {
            ballSpeedY = 0;
            ballSpeedX = 0;
            ballY = startingY;
            ballX = startingX;
            return 1;
        }
        return 0;
    }


}
