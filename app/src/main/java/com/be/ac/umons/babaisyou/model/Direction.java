package com.be.ac.umons.babaisyou.model;

public enum Direction {

    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0);

    public int x;
    public int y;

    /**
     * create a construction for a Direction
     * @param x is coordinated abscissa
     * @param y is coordinate orderly
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
