package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import static com.be.ac.umons.babaisyou.rules.Rule.isValid;

public class Grid {

    Position pos;
    public ArrayList<Entities>[][] grid;
    ArrayList<Rule> obj = new ArrayList<Rule>();
    /*
     ArrayList<ArrayList<Arraylist<Entities>>> ref = new ArrayList<ArraylistArraylist<Entities>>();
     1
     */

    public Grid(String map) throws IOException {// constructeur qui permet de definir le plateau ou la grille qui est un tableau a 2 dimension d'entities
        String line;
        String[] values;
        Entities entities;
        BufferedReader lecture = new BufferedReader(new FileReader(map));
        while ((line = lecture.readLine()) != null) {
            values = line.split(" ");
            if (values.length == 2) {
                this.grid = new ArrayList[Integer.parseInt(values[0])][Integer.parseInt(values[1])];
            } else {
                pos = new Position(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                if (values.length == 3) {
                    entities = new Entities(getWords(values[0]), pos, 0);
                    if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                        this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
                } else {
                    entities = new Entities(getWords(values[0]), pos, Integer.parseInt(values[3]));
                    if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                        this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
                    ;
                }
            }
        }
        /*obj = therule();
        for (Rule args:obj){
            System.out.println(args.first+" "+args.second+" "+args.third);
        }*/
    }

    public int getRow() {
        return this.grid.length;
    }

    public int getCol() {
        return this.grid[0].length;
    }

    public Words getWords(String s) {
        return switch (s) {
            case "text_baba" -> Words.test_baba;
            case "text_rock" -> Words.test_rock;
            case "text_wall" -> Words.test_wall;
            case "text_flag" -> Words.test_flag;
            case "is" -> Words.is;
            case "you" -> Words.you;
            case "wall" -> Words.wall;
            case "stop" -> Words.stop;
            case "rock" -> Words.rock;
            case "win" -> Words.win;
            case "baba" -> Words.baba;
            case "push" -> Words.push;
            case "flag" -> Words.flag;
            case "lava" -> Words.lava;
            case "water" -> Words.water;
            default -> null;
        };
    }

    public Entities getEntitities(int x, int y) {
        if (this.grid[x][y] != null) {// regarder si this.grid[x][y] n'est pas nul
            for (Entities args : this.grid[x][y]) {
                if (args.position.row == x && args.position.col == y) {// je pense que je devrais mettre un for ici pour parcourir chaqu'entites
                    return args;
                }
            }
        }
        return null;
    }

    public ArrayList<Rule> getRules() {
        ArrayList<Rule> rules = new ArrayList<Rule>();
        Rule tmp;
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCol(); j++) {
                for (Entities second : this.grid[i][j]) {
                    if (second.block.equals(Words.is)) {
                        for (Entities first : this.grid[i - 1][j]) {
                            for (Entities third : this.grid[i + 1][j]) {
                                if (Rule.isValid(first.block, second.block, third.block)) {
                                    tmp = new Rule(first.block, second.block, third.block);
                                    rules.add(tmp);
                                }
                            }
                        }
                        for (Entities first : this.grid[i][j - 1]) {
                            for (Entities third : this.grid[i][j + 1]) {
                                if (Rule.isValid(first.block, second.block, third.block)) {
                                    tmp = new Rule(first.block, second.block, third.block);
                                    rules.add(tmp);
                                }
                            }
                        }

                    }
                }
            }
        }
        return rules;
    }
}



    /*public void deplacerUp(Object obj){
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid[0].length;j++){
                for(Entities args:this.grid[i][j]){
                    if(args.block == obj){
                        args.position.row --;
                        this.grid[i-1][j].add(args);
                        this.grid[i][j].remove(args);
                    }
                }
            }
        }
    }

    public void deplacerDown(Object obj){
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid[0].length;j++){
                for(Entities args:this.grid[i][j]){
                    if(args.block == obj){
                        args.position.row ++;
                        this.grid[i+1][j].add(args);
                        this.grid[i][j].remove(args);
                    }
                }
            }
        }
    }

    public void deplacerRight(Object obj){
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid[0].length;j++){
                for(Entities args:this.grid[i][j]){
                    if(args.block == obj){//equals
                        args.position.col ++;
                        this.grid[i][j+1].add(args);
                        this.grid[i][j].remove(args);
                    }
                }
            }
        }
    }

    public void deplacerLeft(Object obj){
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid[0].length;j++){
                for(Entities args:this.grid[i][j]){
                    if(args.block == obj){
                        args.position.row --;
                        this.grid[i--][j].add(args);
                        this.grid[i][j].remove(args);
                    }
                }
            }
        }
    }*/























































































































































   /* public ArrayList<Rule> therule(){
        ArrayList<Rule> obj = new ArrayList<Rule>();
        ArrayList<Rule> rule = new ArrayList<>();
        Rule tmp;
        for(int i=0;i<this.grid.length;i++){// de la gauche vers la droite
            for(int j=0;j<this.grid[0].length-3;j++){
                if (this.grid[i][j] != null){
                    if(isMateriel(this.grid[i][j].get(0).block) && isConnected(this.grid[i][j+1].get(0).block)&& isproperty(this.grid[i][j+2].get(0).block)){
                        tmp = new Rule(this.grid[i][j].get(0).block,this.grid[i][j+1].get(0).block,this.grid[i][j+2].get(0).block);
                        rule.add(tmp);
                    }
                }
            }
        }
        for(int i=0;i<this.grid.length-3;i++){// haut vers le bas
            for(int j=0;j<this.grid[0].length;j++){
                if (this.grid[i][j] != null){
                    if(isMateriel(this.grid[i][j].get(0).block) && isConnected(this.grid[i+1][j].get(0).block)&& isproperty(this.grid[i+2][j].get(0).block)){
                        tmp = new Rule(this.grid[i][j].get(0).block,this.grid[i+1][j].get(0).block,this.grid[i+2][j].get(0).block);
                        rule.add(tmp);
                    }
                }
            }
        }
        for(int i=0;i<this.grid.length-3;i++){// haut vers le bas
            for(int j=0;j<this.grid[0].length;j++){
                if (this.grid[i][j] != null){
                    if(isMateriel(this.grid[i][j].get(0).block) && isConnected(this.grid[i+1][j].get(0).block)&& isMateriel(this.grid[i+2][j].get(0).block)){
                        tmp = new Rule(this.grid[i][j].get(0).block,this.grid[i+1][j].get(0).block,this.grid[i+2][j].get(0).block);
                        rule.add(tmp);
                    }
                }
            }
        }
        for(int i=0;i<this.grid.length-3;i++){// haut vers le bas
            for(int j=0;j<this.grid[0].length;j++){
                if (this.grid[i][j] != null){
                    if(isMateriel(this.grid[i][j].get(0).block) && isConnected(this.grid[i][j+1].get(0).block)&& isMateriel(this.grid[i][j+2].get(0).block)){
                        tmp = new Rule(this.grid[i][j].get(0).block,this.grid[i][j+1].get(0).block,this.grid[i][j+2].get(0).block);
                        rule.add(tmp);
                    }
                }
            }
        }
        return rule;
    }

    // create a fonction that given Objet who doing action ...
}
*/




