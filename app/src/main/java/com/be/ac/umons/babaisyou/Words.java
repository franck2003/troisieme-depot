package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;

public enum Words {

        // NOUNS
        test_baba(TypeOfWords.NOUN,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\test_baba.png"),
        test_rock(TypeOfWords.NOUN,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\test_rock.png"),
        test_wall(TypeOfWords.NOUN,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\test_wall.png"),
        test_flag(TypeOfWords.NOUN,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\test_flag.png"),

        // OPERATORS
        is(TypeOfWords.OPERATOR,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\is.png"),


        // PROPERTIES
        baba(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\baba.png"),
        water(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\water.png"),
        rock(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\rock.png"),
        lava(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\lava.png"),
        you(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\you.png"),
        win(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\win.png"),
        stop(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\stop.png"),
        flag(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\flag.png"),
        wall(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\wall.png"),
        push(TypeOfWords.PROPERTY,"D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\push.png"),;

        private final TypeOfWords type;
        private final String path;

        Words(TypeOfWords type, String path) {
                this.type= type;
                this.path = path;

        }

        public TypeOfWords getTypeOfWords(){
                return this.type;
        }

        public String getPath(){
                return this.path;
        }

        public boolean isType(TypeOfWords type){
                return  this.type.equals(type);
        }
}
