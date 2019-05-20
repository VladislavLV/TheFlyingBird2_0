package com.example.theflyingbird2;

import android.widget.ImageView;
import android.widget.TextView;

import static com.example.theflyingbird2.MainActivity.birdSize;

public class GameObjects {

    private ImageView imageView;

    private int posX;
    private int posY;
    private int speedX;
    private int speedRespawnX;

    public GameObjects(ImageView textView, int offSetX, int offSetY){
        this.imageView = textView;
        imageView.setX(offSetX);
        imageView.setY(offSetY);
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getCenterX(){
        return getPosX() + getImageView().getWidth() / 2;
    }

    public boolean intersect(int birdY){

        return (0 <= getCenterX() && getCenterX() <= birdSize && birdY <= getCenterY() && getCenterY() <= birdY + birdSize);
    }

    public int getCenterY(){
        return getPosY() + getImageView().getHeight() / 2;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
