package com.be.ac.umons.babaisyou;

public enum Material {
    test_baba('B'),
    test_rock('R'),
    test_wall('W'),
    test_flag('F'),
    test_water('E'),
    test_lava('L');

    private final char charactere;

    Material(char charactere) {// define a  word his description and charactere
        this.charactere = charactere;
    }

    public char getCharactere() {
        return charactere;
    }



    public boolean isMateriel(Object obj){
        for(Material prop:Material.values()){
            if (obj.equals(prop)){
                return true;
            }
        }
        return false;
    }
}
