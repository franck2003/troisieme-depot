package com.be.ac.umons.babaisyou;

public enum Material {
    TEXT_BABA(Description.NOUN,'B'),
    TEXT_ROCK(Description.NOUN,'R'),
    TEXT_WALL(Description.NOUN,'W'),
    TEXT_FLAG(Description.NOUN,'F'),
    TEXT_WATER(Description.NOUN,'E'),
    TEXT_LAVA(Description.NOUN,'L'),

    IS(Description.CONNECTED,'i'),

    YOU(Description.PROPERTY,'Y'),
    WIN(Description.PROPERTY,'!'),
    STOP(Description.PROPERTY,'S'),
    PUSH(Description.PROPERTY,'P'),
    ;

    private Description noun;
    private char charactere;

    Material(Description noun, char charactere) {// define a  word his description and charactere
        this.noun = noun;
        this.charactere = charactere;
    }

    public boolean isType(Description type) {// check if the type is the same as the type of word
        return this.noun.equals(type);
    }

    public boolean isName(String name) {// check if the name is similar to same of the word
        return this.name().equals(name);
    }

    public boolean ischaractere(char charactere) {// check if the char is the same with inf charactere
        return this.charactere == charactere || (noun.equals(Description.NOUN) && Character.toUpperCase(this.charactere) == charactere);
    }
}
