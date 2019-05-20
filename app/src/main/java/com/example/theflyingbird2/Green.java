package com.example.theflyingbird2;

import android.widget.ImageView;

import static com.example.theflyingbird2.MainActivity.frameHeight;
import static com.example.theflyingbird2.MainActivity.screenWidth;

public class Green extends GameObjects {

    public Green(ImageView imageView,int offSetX, int offSetY) {
        super(imageView,offSetX,offSetY);
    }

    public void update(){
        setPosX(getPosX() -30);

        if (getPosX() < 0) {
            setPosX(screenWidth + 10000);
            setPosY((int) Math.floor(Math.random() * (frameHeight - getImageView().getHeight())));
        }
        getImageView().setX(getPosX());
        getImageView().setY(getPosY());
    }

}
