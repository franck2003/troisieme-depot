package com.be.ac.umons.babaisyou;

public enum Connected {

     is('I');
    final char values;

    Connected(char values){
        this.values = values;
    }

    public char getCharactere() {
        return values;
    }

}
