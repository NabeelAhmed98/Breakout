package com.example.nabee.breakout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Class 1 on 5/4/2018.
 */

public class BreakoutView extends View {
    private int xMax;
    private int yMax;
    Bricks[] bricks;
    float rectX, rectY, rectWidth, rectHeight, spacing;
    float slideX, slideY, slideWidth, slideHeight;
    float ballX, ballY, ballSpeedX, ballSpeedY;
    float speedCyan, speedBlue, speedGreen, speedYellow, speedRed;
    Slider slider;
    Ball ball;
    boolean game = false;


    public BreakoutView(Context context) {
        super(context);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        bricks = new Bricks[100];
        rectX = 0;
        rectY = 100;
        rectWidth = width / 10;
        spacing = rectWidth / 8;
        rectHeight = 25;

        slideX = (width / 2) - (rectWidth + spacing);
        slideY = height - 500;
        slideWidth = (rectWidth + spacing) * 2;
        slideHeight = 25;

        ballX = width / 2 - 20;
        ballY = slideY - 40;
        ballSpeedX = 0;
        ballSpeedY = 0;

        speedCyan = 15;
        speedBlue = 17;
        speedGreen = 21;
        speedYellow = 27;
        speedRed = 35;


        for (int i = 0; i < bricks.length; i++) {
            if (i % 10 == 0) {
                rectX = 0;
                rectY += rectHeight + spacing;

                bricks[i] = new Bricks(rectX, rectY, rectX + rectWidth, rectY + rectHeight);
            } else {
                bricks[i] = new Bricks(rectX, rectY, rectX + rectWidth, rectY + rectHeight);
                rectX += rectWidth + spacing;
            }
        }

        slider = new Slider(slideX, slideY, slideX + slideWidth, slideY + slideHeight);
        ball = new Ball(ballX, ballY, ballSpeedX, ballSpeedY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < bricks.length; i++) {
            if (i < bricks.length) {
                bricks[i].draw(canvas, Color.CYAN);
                bricks[i].setColor(Color.CYAN);
            }
            if (i < 80) {
                bricks[i].draw(canvas, Color.BLUE);
                bricks[i].setColor(Color.BLUE);
            }
            if (i < 60) {
                bricks[i].draw(canvas, Color.GREEN);
                bricks[i].setColor(Color.GREEN);
            }
            if (i < 40) {
                bricks[i].draw(canvas, Color.YELLOW);
                bricks[i].setColor(Color.YELLOW);
            }
            if (i < 20) {
                bricks[i].draw(canvas, Color.RED);
                bricks[i].setColor(Color.RED);
            }
        }
        slider.drawSlider(canvas, Color.GRAY);

        ball.drawBall(canvas, Color.BLACK);

        update();

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
        }

        invalidate();  // Force a re-draw
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        xMax = w - 1;
        yMax = h - 1;
    }

    public void update() {
        int b = ball.update(xMax, yMax);
        if (b == 1) {
            slider.slideX = slideX;
            slider.slideWidth = slideWidth;

            game = false;
        }
        if (ball.reflect(slider)) {
            //   ball.ballSpeedX = -ball.ballSpeedX;
            ball.ballSpeedY = -ball.ballSpeedY;
        }

        for (int i = 0; i < bricks.length; i++) {
            if (ball.collide(bricks[i])) {
                bricks[i].active = false;
                if (ball.ballY + ball.ballRadius >= bricks[i].rectY) {
                    ball.ballSpeedY = -ball.ballSpeedY;
                    ball.ballY = bricks[i].rectY - ball.ballRadius;
                } else if (ball.ballY <= bricks[i].rectHeight) {
                    ball.ballSpeedY = -ball.ballSpeedY;
                    ball.ballY = bricks[i].rectHeight + ball.ballRadius;
                } else if (ball.ballX + ball.ballRadius >= bricks[i].rectX) {
                    ball.ballSpeedX = -ball.ballSpeedX;
                    ball.ballX = bricks[i].rectX - ball.ballRadius;
                } else if (ball.ballX <= bricks[i].rectWidth) {
                    ball.ballSpeedX = -ball.ballSpeedX;
                    ball.ballX = bricks[i].rectWidth + ball.ballRadius;
                }
                if (bricks[i].color == Color.CYAN) {
                    if (ball.ballSpeedX == speedCyan && ballSpeedY == speedCyan
                            || ball.ballSpeedY > speedCyan && ball.ballSpeedX > speedCyan) {
                        continue;
                    } else if (ball.ballSpeedY < speedCyan && ball.ballSpeedX < speedCyan) {
                        ball.ballSpeedX = speedCyan;
                        ball.ballSpeedY = speedCyan;
                    }
                } else if (bricks[i].color == Color.BLUE) {
                    if (ball.ballSpeedX == speedBlue && ballSpeedY == speedBlue
                            || ball.ballSpeedY > speedBlue && ball.ballSpeedX > speedBlue) {
                        continue;
                    } else if (ball.ballSpeedY < speedBlue && ball.ballSpeedX < speedBlue) {
                        ball.ballSpeedX = speedBlue;
                        ball.ballSpeedY = speedBlue;
                    }
                } else if (bricks[i].color == Color.GREEN) {
                    if (ball.ballSpeedX == speedGreen && ballSpeedY == speedGreen
                            || ball.ballSpeedY > speedGreen && ball.ballSpeedX > speedGreen) {
                        continue;
                    } else if (ball.ballSpeedY < speedGreen && ball.ballSpeedX < speedGreen) {
                        ball.ballSpeedX = speedGreen;
                        ball.ballSpeedY = speedGreen;
                    }
                } else if (bricks[i].color == Color.YELLOW) {
                    if (ball.ballSpeedX == speedYellow && ballSpeedY == speedYellow
                            || ball.ballSpeedY > speedYellow && ball.ballSpeedX > speedYellow) {
                        continue;
                    } else if (ball.ballSpeedY < speedYellow && ball.ballSpeedX < speedYellow) {
                        ball.ballSpeedX = speedYellow;
                        ball.ballSpeedY = speedYellow;
                    }
                } else if (bricks[i].color == Color.RED) {
                    if (ball.ballSpeedX == speedRed && ballSpeedY == speedRed
                            || ball.ballSpeedY > speedRed && ball.ballSpeedX > speedRed) {
                        continue;
                    } else if (ball.ballSpeedY < speedRed && ball.ballSpeedX < speedRed) {
                        ball.ballSpeedX = speedRed;
                        ball.ballSpeedY = speedRed;
                    }
                }

            }
        }

    }


    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                Log.i("DEBUG_TAG", Integer.toString(event.getAction()));
                if (x + slideWidth >= xMax) {
                    slider.slideWidth = xMax;
                    slider.slideX = xMax - slideWidth;
                    invalidate();
                } else {
                    slider.slideX = x;
                    slider.slideWidth = x + slideWidth;
                    invalidate();

                }
                if (x + slideWidth >= xMax && ball.ballY == ballY) {
                    slider.slideWidth = xMax;
                    slider.slideX = xMax - slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();

                } else if (x + slideWidth <= xMax && ball.ballY == ballY) {
                    slider.slideX = x;
                    slider.slideWidth = x + slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();

                }
            case (MotionEvent.ACTION_UP):
                if (game) {
                    break;
                } else {
                    Log.i("DEBUG_TAG", Integer.toString(event.getAction()));
                    ball.ballSpeedX -= 15;
                    ball.ballSpeedY -= 15;
                }
            case (MotionEvent.ACTION_MOVE):
                Log.i("DEBUG_TAG", Integer.toString(event.getAction()));
                if (x + slideWidth >= xMax) {
                    slider.slideWidth = xMax;
                    slider.slideX = xMax - slideWidth;
                    invalidate();

                } else {
                    slider.slideX = x;
                    slider.slideWidth = x + slideWidth;
                    invalidate();

                }
                if (x + slideWidth >= xMax && ball.ballY == ballY) {
                    slider.slideWidth = xMax;
                    slider.slideX = xMax - slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();

                } else if (x + slideWidth <= xMax && ball.ballY == ballY) {
                    slider.slideX = x;
                    slider.slideWidth = x + slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void KeyStruck(int code){
        float moveSpeed = 15;
        switch(code){
            case KeyEvent.KEYCODE_ENTER:
                if(!game) {
                    ballSpeedX -= 15;
                    ball.ballSpeedY -= 15;
                    game = true;
                    break;
                }
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (moveSpeed + slider.slideWidth >= xMax) {
                    slider.slideWidth = xMax;
                    slider.slideX = xMax - slideWidth;
                    invalidate();
                } else {
                    slider.slideX += moveSpeed;
                    slider.slideWidth = slider.slideX + slideWidth;
                    invalidate();
                }
                if (moveSpeed + slider.slideWidth >= xMax && ball.ballY == ballY) {
                    slider.slideWidth = xMax;
                    slider.slideX = xMax - slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();
                } else if (moveSpeed + slider.slideWidth < xMax && ball.ballY == ballY) {
                    slider.slideX += moveSpeed;
                    slider.slideWidth = slider.slideX + slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if ( slider.slideX - moveSpeed <= 0) {
                    slider.slideWidth = 0 + slideWidth;
                    slider.slideX = 0;
                    invalidate();
                } else {
                    slider.slideX -= moveSpeed;
                    slider.slideWidth = slider.slideX + slideWidth;
                    invalidate();
                }
                if (moveSpeed + slideWidth <= 0 && ball.ballY == ballY) {
                    slider.slideWidth = 0 + slideWidth;
                    slider.slideX = 0;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();
                } else if (moveSpeed + slideWidth <= xMax && ball.ballY == ballY) {
                    slider.slideX -= moveSpeed;
                    slider.slideWidth = slider.slideX + slideWidth;
                    ball.ballX = (slider.slideX + slider.slideWidth) / 2;
                    invalidate();
                }
                break;
        }
    }
}

