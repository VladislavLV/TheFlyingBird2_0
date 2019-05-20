package com.example.theflyingbird2;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int birdSound;

    public SoundPlayer(Context context){

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        hitSound = soundPool.load(context,R.raw.hit,1);
        overSound = soundPool.load(context,R.raw.over,1);
        birdSound = soundPool.load(context,R.raw.birdsounds,0);

    }

    public void playHitSound(){
        soundPool.play(hitSound,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound(){
        soundPool.play(overSound,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playBirdSound(){
        soundPool.play(birdSound,1.0f, 1.0f, 0, 0, 1.0f);
    }

}
