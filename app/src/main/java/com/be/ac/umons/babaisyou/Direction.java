package com.be.ac.umons.babaisyou;

public enum Direction {

    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0);

    public int x;
    public int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction match(String mov) {
        return switch (mov) {
            case "w" -> Direction.UP;
            case "s" -> Direction.LEFT;
            case "a" -> Direction.DOWN;
            case "d" -> Direction.RIGHT;
            default -> null;
        };
    }
}
