package com.be.ac.umons.babaisyou.model;



public enum Words {

        // NOUNS
        text_baba(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_baba.png","baba"),
        text_rock(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_rock.png","rock"),
        text_wall(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_wall.png","wall"),
        text_flag(TypeOfWords.NOUN,"src\\main\\resources\\Image\\test_flag.png","flag"),
        text_lava(TypeOfWords.NOUN,"src\\main\\resources\\Image\\kill.png","lava"),
        kill(TypeOfWords.NOUN,"src\\main\\resources\\Image\\kill.png","lava"),
        metal(TypeOfWords.NOUN,"src\\main\\resources\\Image\\kill.png","lava"),
        hot(TypeOfWords.NOUN,"src\\main\\resources\\Image\\hot.png","water"),

        //PROPERTIES
        you(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\you.png","you"),
        win(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\win.png","win"),
        stop(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\stop.png","stop"),
        push(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\push.png","push"),
        sink(TypeOfWords.PROPERTY,"src\\main\\resources\\Image\\sink.png","wall"),

        // OPERATORS
        is(TypeOfWords.OPERATOR,"src\\main\\resources\\Image\\is.png",""),

        // MATERIAL
        baba(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\baba.png","baba"),
        water(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\water.png","water"),
        rock(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\rock.png","rock"),
        lava(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\lava.png","lava"),

        flag(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\flag.png","flag"),
        wall(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\wall.png","wall"),
        grass(TypeOfWords.MATERIAL,"src\\main\\resources\\Image\\grass.png","grass");

        private final TypeOfWords type;
        private final String path;
        private final String name;

        /**
         * Define a word with his type, his path and possible property
         * @param type is type of Words
         * @param path is path of Words
         * @param name is possible property of Words
         */
        Words(TypeOfWords type, String path,String name) {
                this.type= type;
                this.path = path;
                this.name = name;

        }


        /**
         *
         * @return @return a type for different Words
         */
        public TypeOfWords getTypeOfWords(){

                return this.type;
        }

        /**
         *
         * @return a Path for different Words
         */

        public String getPath(){
                return this.path;
        }

        /**
         *
         * @return a possible property for different Words
         */
        public String getName(){
                return this.name;
        }

        /**
         * Take a type and check if the type is the same as the type of the word
         * @param type is type of Words
         * @return true if the type is similar to the type of the word, else false
         */
        public boolean isType(TypeOfWords type){
                return  this.type.equals(type);
        }
}
