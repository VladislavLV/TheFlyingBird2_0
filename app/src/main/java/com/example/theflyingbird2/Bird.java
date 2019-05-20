package com.example.theflyingbird2;

import android.widget.ImageView;

import static com.example.theflyingbird2.MainActivity.birdSize;
import static com.example.theflyingbird2.MainActivity.frameHeight;

public class Bird extends GameObjects {



    public Bird(ImageView textView, int offSetX, int offSetY) {
        super(textView, offSetX, offSetY);
    }

    public void update(boolean flag){

        jump(flag);

        if (getPosY() < 0)
            setPosY(0);

        if (getPosY() > frameHeight - getImageView().getHeight())
            setPosY(frameHeight - getImageView().getHeight());
        getImageView().setY(getPosY());
    }

    private void jump(boolean flag) {
        if (flag) {
            //Touching
            setPosY(getPosY() - 20);
        } else {
            //Releasing
            setPosY(getPosY() + 20);
        }
    }
}
