package com.be.ac.umons.babaisyou.model.rules;

import com.be.ac.umons.babaisyou.model.TypeOfWords;
import com.be.ac.umons.babaisyou.model.Words;

public class Rule{
    public Words first;
    public Words second;
    public Words third;

    /**
     * Constructor of a rule with her 3 block
     * @param first is a first Words
     * @param second is a second Words
     * @param third is a third Words
     */

    public Rule(Words first, Words second, Words third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     *
     * Check if a rule is valid or not with 3 conditions on her 3 Words : first Words has to be a type NOUN,
     * the second has to be a type OPERATOR and the last has to be a type PROPERTY or a NOUN .
     * @param first Words for NOUN
     * @param second Words for OPERATOR
     * @param third Words for NOUN or PROPERTY
     * @return true if valid rule else false
     */
    public static boolean isValid(Words first, Words second, Words third){
        var a = first.isType(TypeOfWords.NOUN);
        var b = second.isType(TypeOfWords.OPERATOR);
        var c = (third.isType(TypeOfWords.PROPERTY) || (third.isType(TypeOfWords.NOUN)));
        return a && b && c;
    }
}
