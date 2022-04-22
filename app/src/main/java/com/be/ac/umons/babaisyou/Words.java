package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;

public enum Words {

        // NOUNS
        text_baba(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_baba.png","baba"),
        text_rock(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_rock.png","rock"),
        text_wall(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_wall.png","wall"),
        text_flag(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_flag.png","flag"),
        push(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\push.png","push"),

        // OPERATORS
        is(TypeOfWords.OPERATOR,"src\\main\\resources\\Image\\is.png",""),

        // PROPERTIES
        baba(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\baba.png","baba"),
        water(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\water.png","water"),
        rock(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\rock.png","rock"),
        lava(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\lava.png","lava"),
        you(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\you.png","you"),
        win(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\win.png","win"),
        stop(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\stop.png","stop"),
        flag(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\flag.png","flag"),
        wall(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\wall.png","wall");

        private final TypeOfWords type;
        private final String path;
        private final String name;


        Words(TypeOfWords type, String path,String name) {
                this.type= type;
                this.path = path;
                this.name = name;

        }
        public TypeOfWords getTypeOfWords(){

                return this.type;
        }

        public String getPath(){
                return this.path;
        }

        public String getName(){
                return this.name;
        }

        public boolean isType(TypeOfWords type){
                return  this.type.equals(type);
        }
}
