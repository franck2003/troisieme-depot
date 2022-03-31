package com.be.ac.umons.babaisyou;

public enum Property {

    you('Y'),
    win('o'),
    stop('s'),
    push('p'),
    wall('w'),
    rock('r'),
    baba('@'),
    pusher('p'),
    flag('!');

    private final Object values;
    Property(Object values){
        this.values = values;
    }

    public Object getCharactere() {
        return values;
    }

}


































































































