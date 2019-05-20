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

    private FrameLayout frame;
    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView bird;
    private ImageView orange;
    private ImageView black;
    private ImageView bigBlack;
    private ImageView pink;
    private ImageView green;

    //Size
    public static int frameHeight;
    public static int birdSize;
    public static int screenWidth;
    public static int screenHeight;

    private Orange mOrange;
    private Green mGreen;
    private Pink mPink;
    private Black mBlack;
    private BigBlack mBigBlack;
    private Bird mBird;

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

        getViews();
        //Get screen size
        getDeviceScreenSize();

        createObjects();

        scoreLabel.setText("Score : 0");
    }

    private void createObjects() {
        sound = new SoundPlayer(this);
        mOrange = new Orange(orange,-80,-80);
        mGreen = new Green(green,-80,-80);
        mBigBlack = new BigBlack(bigBlack,-130,-130);
        mBlack = new Black(black,-80,-80);
        mPink = new Pink(pink,-80,-80);
        mBird = new Bird(bird,0,0);
    }

    private void getDeviceScreenSize() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;
    }

    private void getViews() {
        frame = (FrameLayout) findViewById(R.id.frame);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        bird = (ImageView) findViewById(R.id.bird);
        orange = (ImageView) findViewById(R.id.orange);
        black = (ImageView) findViewById(R.id.black);
        bigBlack = (ImageView) findViewById(R.id.bigBlack);
        pink = (ImageView) findViewById(R.id.pink);
        green = (ImageView) findViewById(R.id.green);
    }

    public void changePos() {

        hitCheck();

        mOrange.update();

        mBlack.update();

        mBigBlack.update();

        mPink.update();

        mGreen.update();

        mBird.update(action_flag);

        scoreLabel.setText("Score : " + score);
        sound.playBirdSound();
    }

    public void hitCheck() {

        //If center of the ball is in the bird, it counts as a hit.
        if(mOrange.intersect(mBird.getPosY())){
            score += 10;
            mOrange.setPosX(mOrange.getPosX() - 100);
            sound.playHitSound();
        }

        if (mBlack.intersect(mBird.getPosY())){
            //Stop Timer
            timer.cancel();
            timer = null;
            sound.playOverSound();

            //Show Result
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

        if(mBigBlack.intersect(mBird.getPosY())){
            //Stop Timer
            timer.cancel();
            timer = null;
            sound.playOverSound();

            //Show Result
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

        if(mPink.intersect(mBird.getPosY())){
            score += 25;
            mPink.setPosX(mPink.getPosX() - 100);
            sound.playHitSound();
        }

        if(mGreen.intersect(mBird.getPosY())){
            score += 50;
            mGreen.setPosX(mGreen.getPosX() - 100);
            sound.playHitSound();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if (start_flag == false) {

            start_flag = true;
            frameHeight = frame.getHeight();

            mBird.setPosY((int)mBird.getImageView().getY());
            birdSize = mBird.getImageView().getHeight();


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
