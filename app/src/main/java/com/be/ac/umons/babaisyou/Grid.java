package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.Graphique.Windows;
import com.be.ac.umons.babaisyou.rules.Rule;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//import static com.be.ac.umons.babaisyou.rules.Rule.isValid;

public class Grid {

    Position pos;
    private ArrayList<Entities>[][] grid;

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
            for (int i = 0; i < this.grid.length; i++) {
                for (int j = 0; j < this.grid[i].length; j++) {
                    if (this.grid[i][j] == null) {
                        this.grid[i][j] = new ArrayList<Entities>();
                    }
                }
            }
        }
        /*obj = therule();
        for (Rule args:obj){
            System.out.println(args.first+" "+args.second+" "+args.third);
        }*/
    }

    public ArrayList<Entities>[][] getGrid(){
        return this.grid;
    }

    public int getRow() {
        return this.grid.length;
    }

    public int getCol() {
        return this.grid[0].length;
    }

    public Words getWords(String s) {
        return Words.valueOf(s);
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

    public void removeEntities(int i, int j, Entities entities) {
        this.grid[i][j].remove(entities);
    }

    public void setEntities(int i, int j, Entities entities) {
        this.grid[i][j].add(entities);
    }

    public ArrayList<Entities> getIs() {
        ArrayList<Entities> list = new ArrayList<Entities>();
        int cpt = 0;
        for (ArrayList<Entities>[] arrayLists : this.grid) {
            for (ArrayList<Entities> arrayList : arrayLists) {
                if (arrayList != null) {
                    for (Entities args : arrayList) {
                        if (args.block.equals(Words.is)) {
                            list.add(args);
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public ArrayList<Rule> getRules() {
        ArrayList<Rule> rules = new ArrayList<Rule>();
        List<Entities> list = getIs();
        for (Entities second : list) {
            if (this.grid[second.position.row - 1][second.position.col].size() != 0 && this.grid[second.position.row + 1][second.position.col].size() != 0) {
                for (Entities first : this.grid[second.position.row - 1][second.position.col]) {
                    for (Entities third : this.grid[second.position.row + 1][second.position.col]) {
                        if (Rule.isValid(first.block, second.block, third.block)) {
                            rules.add(new Rule(first.block, second.block, third.block));
                        }
                    }
                }
            }
            if (this.grid[second.position.row][second.position.col - 1].size() != 0 || this.grid[second.position.row][second.position.col + 1].size() != 0) {
                for (Entities first : this.grid[second.position.row][second.position.col - 1]) {
                    for (Entities third : this.grid[second.position.row][second.position.col + 1]) {
                        if (Rule.isValid(first.block, second.block, third.block)) {
                            rules.add(new Rule(first.block, second.block, third.block));
                        }
                    }
                }
            }
            /*for(Rule args:rules){
                System.out.print(args.first+" ");
                System.out.print(args.second+" ");
                System.out.print(args.third+" ");
                System.out.println();
            }*/

        }
        return rules;
    }


    public boolean ingrid(int i, int j) {
        return i < getRow() && j < getCol() && i >= 0 && j >= 0;
    }

    public boolean IsBlock(int i, int j) {
        ArrayList<Rule> rules = getRules();
        Words rock = null;
        for (Rule args : rules) {
            if (args.first.equals(Words.push)) {
                rock = args.first;
            }
        }
        for (Entities args : this.grid[i][j]) {
            if (args.block.equals(rock)) {
                return true;
            }
        }
        return false;
    }

    public boolean push(Entities ref, Direction dir) {
        ArrayList<Rule> rules = this.getRules();
        Words wall = null;
        Words rock = null;
        boolean isPush = true;
        for (Rule args : rules) {
            if (args.third.equals(Words.stop)) {
                wall = Words.valueOf(args.first.getName().toLowerCase());
            }
            if (args.third.equals(Words.push)) {
                rock = Words.valueOf(args.first.getName().toLowerCase());
            }
        }
            if (!ingrid(ref.position.row + dir.x, ref.position.col + dir.y)) {
                return false;
            }
            for (Entities args : this.grid[ref.position.row + dir.x][ref.position.col + dir.y]) {
                if (args.block.equals(wall)) {
                    return false;
                }
            }
            for (int i = 0; i < this.grid[ref.position.row + dir.x][ref.position.col + dir.y].size(); i++) {
                if (this.grid[ref.position.row + dir.x][ref.position.col + dir.y].get(i).block.equals(rock) ) {
                    isPush = push(this.grid[ref.position.row + dir.x][ref.position.col + dir.y].get(i), dir);
                }
            }
            if(isPush){
                this.grid[ref.position.row][ref.position.col].remove(ref);
                ref.position.row += dir.x;
                ref.position.col += dir.y;
                this.grid[ref.position.row][ref.position.col].add(ref);

                ImageView img = ref.getImageView();
                img.setTranslateX(img.getTranslateX() + (dir.x * 40));
                img.setTranslateY(img.getTranslateY() + (dir.y * 40));

            }
            return isPush;
    }

    public void Move(Direction dir) {
        ArrayList<Rule> rules = this.getRules();
        Words baba = null;

        for (Rule args : rules) {
            if (args.third.equals(Words.you)) {
                baba = Words.valueOf(args.first.getName().toLowerCase());
            }
        }
        ArrayList<Entities> test = new ArrayList<Entities>();
        for (int i=0;i<this.grid.length;i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if (!this.grid[i][j].isEmpty()) {
                    for (Entities args : this.grid[i][j]) {
                        if (args.block.equals(baba)) {
                            test.add(args);
                        }
                    }
                }
            }
        }
        for (Entities args:test){
            push(args, dir);
        }
    }
}












































































































































