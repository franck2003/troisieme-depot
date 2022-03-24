package com.be.ac.umons.babaisyou;

import java.util.ArrayList;

public class Entities {
    Material name;
    int dir;
    Position position;

    public Entities(Material name, Position position, int dir) {
        this.position = position;
        this.name = name;
        this.dir = dir;
    }
}