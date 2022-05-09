package com.be.ac.umons.babaisyou.model;

public class Position {

    public int row;
    public int col;

    /**
     * constructor for each Position of entity in Grid
     * @param row coordinated abscissa
     * @param col coordinated orderly
     */
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

}