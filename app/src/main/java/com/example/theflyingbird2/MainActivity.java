package com.example.theflyingbird2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView bird;
    private ImageView orange;
    private ImageView black;
    private ImageView bigBlack;
    private ImageView pink;
    private ImageView green;

    //Size
    private int frameHeight;
    private int birdSize;
    private int screenWidth;
    private int screenHeight;

    //Position
    private int birdY;
    private int orangeX;
    private int orangeY;
    private int blackX;
    private int blackY;
    private int bigBlackX;
    private int bigBlackY;
    private int pinkX;
    private int pinkY;
    private int greenX;
    private int greenY;

    //Score
    private int score = 0;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;

    //Status Check
    private boolean action_flag = false;
    private boolean start_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new SoundPlayer(this);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        bird = (ImageView) findViewById(R.id.bird);
        orange = (ImageView) findViewById(R.id.orange);
        black = (ImageView) findViewById(R.id.black);
        bigBlack = (ImageView) findViewById(R.id.bigBlack);
        pink = (ImageView) findViewById(R.id.pink);
        green = (ImageView) findViewById(R.id.green);

        //Get screen size
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //move to out of screen
        orange.setX(-80);
        orange.setY(-80);
        black.setX(-80);
        black.setY(-80);
        bigBlack.setX(-130);
        bigBlack.setY(-130);
        pink.setX(-80);
        pink.setY(-80);
        green.setX(-80);
        green.setY(-80);

        scoreLabel.setText("Score : 0");
    }

    public void changePos() {

        hitCheck();

        //Orange
        orangeX -= 10;

        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        //Black
        blackX -= (16 + Math.random() * 9);
        if (blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (int) Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        //BigBlack
        bigBlackX -= 10;
        bigBlackY = (int) (Math.sin(bigBlackX * 0.01) * 500 + 500);
        if (bigBlackX < 0) {
            bigBlackX = screenWidth + 2500;
            bigBlackY = (int) Math.floor(Math.random() * (frameHeight - bigBlack.getHeight()));
        }
        bigBlack.setX(bigBlackX);
        bigBlack.setY(bigBlackY);

        //Pink
        pinkX -= 20;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        //Green
        greenX -= 30;
        if (greenX < 0) {
            greenX = screenWidth + 10000;
            greenY = (int) Math.floor(Math.random() * (frameHeight - green.getHeight()));
        }
        green.setX(greenX);
        green.setY(greenY);

        //Move bird
        if (action_flag == true) {
            //Touching
            birdY -= 20;
        } else {
            //Releasing
            birdY += 20;
        }

        //Check bird position
        if (birdY < 0)
            birdY = 0;

        if (birdY > frameHeight - birdSize)
            birdY = frameHeight - birdSize;
        bird.setY(birdY);

        scoreLabel.setText("Score : " + score);
        sound.playBirdSound();
    }

    public void hitCheck() {

        //If center of the ball is in the bird, it counts as a hit.
        //Orange
        int orangeCenterX = orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;

        if (0 <= orangeCenterX && orangeCenterX <= birdSize && birdY <= orangeCenterY && orangeCenterY <= birdY + birdSize) {
            score += 10;
            orangeX -= 100;
            sound.playHitSound();
        }

        //Black
        int blackCenterX = blackX + black.getWidth() / 2;
        int blackCenterY = blackY + black.getHeight() / 2;

        if (0 <= blackCenterX && blackCenterX <= birdSize && birdY <= blackCenterY && blackCenterY <= birdY + birdSize) {

            //Stop Timer
            timer.cancel();
            timer = null;
            sound.playOverSound();

            //Show Result
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

        //BigBlack
        int bigBlackCenterX = bigBlackX + bigBlack.getWidth() / 2;
        int bigBlackCenterY = bigBlackY + bigBlack.getHeight() / 2;

        if (0 <= bigBlackCenterX && bigBlackCenterX <= birdSize && birdY <= bigBlackCenterY && bigBlackCenterY <= birdY + birdSize) {

            //Stop Timer
            timer.cancel();
            timer = null;
            sound.playOverSound();

            //Show Result
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

        //Pink
        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;

        if (0 <= pinkCenterX && pinkCenterX <= birdSize && birdY <= pinkCenterY && pinkCenterY <= birdY + birdSize) {
            score += 25;
            pinkX -= 100;
            sound.playHitSound();
        }

        //Green
        int greenCenterX = pinkX + pink.getWidth() / 2;
        int greenCenterY = pinkY + pink.getHeight() / 2;

        if (0 <= greenCenterX && greenCenterX <= birdSize && birdY <= greenCenterY && greenCenterY <= birdY + birdSize) {
            score += 50;
            greenX -= 100;
            sound.playHitSound();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if (start_flag == false) {

            start_flag = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            birdY = (int) bird.getY();
            birdSize = bird.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                action_flag = true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }
        }
        return true;
    }

    //Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
