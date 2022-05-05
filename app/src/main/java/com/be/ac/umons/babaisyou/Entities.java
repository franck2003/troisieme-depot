package com.be.ac.umons.babaisyou;

import javafx.scene.image.ImageView;

public class Entities {
    public  Words block;
    public  int dir;
    public  Position position;
    private ImageView imageView;

    public Entities(Words block, Position position, int dir) {
        this.position = position;
        this.block = block;
        this.dir = dir;
    }

    public void setImageView(ImageView imageView) {

        this.imageView = imageView;

    }

    public ImageView getImageView() {
        return this.imageView;
    }

}