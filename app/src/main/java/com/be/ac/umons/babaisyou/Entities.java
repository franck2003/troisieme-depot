package com.be.ac.umons.babaisyou;

import java.util.ArrayList;

public class Entities {
    public  Object block;
    public  int dir;
    public  Position position;

    public Entities(Object block, Position position, int dir) {
        this.position = position;
        this.block = block;
        this.dir = dir;
    }
}