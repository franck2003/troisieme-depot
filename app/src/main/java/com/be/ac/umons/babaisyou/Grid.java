package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grid {
    Position pos;
    ArrayList<Entities>[][] grid;



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
                    entities = new Entities(values[0], pos, 0);
                    if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                        this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
                } else {
                    entities = new Entities(values[0], pos, Integer.parseInt(values[3]));
                    if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                        this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
                }
            }
        }
    }

    public Entities getEntitities(int x, int y) {
        if(this.grid[x][y] != null){// regarder si this.grid[x][y] n'est pas nul
            for (Entities args : this.grid[x][y]) {
                if (args.position.row == x && args.position.col == y) {// je pense que je devrais mettre un for ici pour parcourir chaqu'entites
                    return args;
            }
        }
        }
        return null;
    }

    public boolean isMaterial(String materiel){
        String[] tab = {"text_baba","test_rock","test_wall","text_flag","test_lava"};
        for (String s : tab) {
            if (s.equals(materiel)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected(String connected){
        return connected.equals("is");
    }

    public boolean isProperty(String property){
        String[] tab = {"You","win","stop","push"};
        for (String s : tab) {
            if (s.equals(property)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Rule> therule(){
        ArrayList<Rule> rule = new ArrayList<>();
        Rule tmp;
        for(int i=0;i<this.grid.length;i++){// de la gauche vers la droite
            for(int j=0;j<this.grid[0].length-3;j++){
                if(this.grid[i][j].get(0).name != null) && isConnected(this.grid[i][j+1].get(0).name)&& isProperty(this.grid[i][j+2].get(0).name)){
                    tmp = new Rule(this.grid[i][j].get(0),this.grid[i][j+1].get(0),this.grid[i][j+2].get(0));
                    rule.add(tmp);
                }
            }
        }
        for(int i=0;i<this.grid.length-3;i++){// haut vers le bas
            for(int j=0;j<this.grid[0].length;j++){
                if(isMaterial(this.grid[i][j].get(0).name) && isConnected(this.grid[i+1][j].get(0).name)&& isProperty(this.grid[i+1][j].get(0).name)){
                    tmp = new Rule(this.grid[i][j+1].get(0),this.grid[i][j].get(0),this.grid[i][j+2].get(0));
                    rule.add(tmp);
                }
            }
        }
        return rule;
    }
}
