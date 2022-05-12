package com.be.ac.umons.babaisyou.model;

import javafx.scene.image.ImageView;

public class Entities {
    public Words block;
    public  int dir;
    public  Position position;
    private ImageView imageView;


    /**
     * constructor for each element for each element in my Grid
     * @param block is a Words for different entity in my Grid
     * @param position is a Position for each entity
     * @param dir is a Direction for each entity
     */
    public Entities(Words block, Position position, int dir) {
        this.position = position;
        this.block = block;
        this.dir = dir;
    }

    /**
     * allows to set imageview for each entity
     * @param imageView imagevieuw for each entity
     */
    public void setImageView(ImageView imageView) {

        this.imageView = imageView;

    }

    /**
     * allows to get Imageview for each entity
     * @return ImageView for each entity
     */
    public ImageView getImageView() {
        return this.imageView;
    }

}