package com.be.ac.umons.babaisyou;

import java.util.ArrayList;

public class Entities {
    public  Words block;
    public  int dir;
    public  Position position;

    public Entities(Words block, Position position, int dir) {
        this.position = position;
        this.block = block;
        this.dir = dir;
    }
}