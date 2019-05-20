package com.example.theflyingbird2;

import android.widget.ImageView;

import static com.example.theflyingbird2.MainActivity.frameHeight;
import static com.example.theflyingbird2.MainActivity.screenWidth;

public class Pink extends GameObjects {

    public Pink(ImageView imageView,int offSetX, int offSetY) {
        super(imageView,offSetX,offSetY);
    }

    public void update(){
        setPosX(getPosX() -20);
        setPosY((int) (Math.sin(getPosX() * 0.01) * 500 + 500));

        if (getPosX() < 0) {
            setPosX(screenWidth + 5000);
            setPosY((int) Math.floor(Math.random() * (frameHeight - getImageView().getHeight())));
        }
        getImageView().setX(getPosX());
        getImageView().setY(getPosY());
    }

}
