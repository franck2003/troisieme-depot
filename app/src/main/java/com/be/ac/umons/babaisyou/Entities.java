package com.be.ac.umons.babaisyou;

import java.util.ArrayList;

public class Entities extends ArrayList {
    String name;
    int dir;
    Position position;

    public Entities(String name, Position position, int dir) {
        this.position = position;
        this.name = name;
        this.dir = dir;
    }
}