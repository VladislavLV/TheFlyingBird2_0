package com.example.theflyingbird2;

import android.widget.ImageView;
import android.widget.TextView;

import static com.example.theflyingbird2.MainActivity.frameHeight;
import static com.example.theflyingbird2.MainActivity.screenWidth;

public class Orange extends GameObjects {

    public Orange(ImageView imageView,int offSetX, int offSetY) {
        super(imageView,offSetX,offSetY);
    }

    public void update(){
        setPosX(getPosX() -10);

        if (getPosX() < 0) {
            setPosX(screenWidth + 20);
            setPosY((int) Math.floor(Math.random() * (frameHeight - getImageView().getHeight())));
        }
        getImageView().setX(getPosX());
        getImageView().setY(getPosY());
    }

}
