package com.be.ac.umons.babaisyou.rules;

import com.be.ac.umons.babaisyou.TypeOfWords;
import com.be.ac.umons.babaisyou.Words;

public class Rule{
    public Words first;
    public Words second;
    public Words third;

    public Rule(Words first, Words second, Words third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static boolean isValid(Words first, Words second, Words third){
        var a = first.isType(TypeOfWords.NOUN);
        var b = second.isType(TypeOfWords.OPERATOR);
        var c = (third.isType(TypeOfWords.PROPERTY) || (third.isType(TypeOfWords.NOUN)));
        return a && b && c;
    }
}
