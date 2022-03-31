package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grid {

    Position pos;
    public ArrayList<Entities>[][] grid;
    ArrayList<Rule> obj = new ArrayList<Rule>();


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
                    entities = new Entities(getObject(values[0]), pos, 0);
                    if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                        this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
                } else {
                    entities = new Entities(getObject(values[0]), pos, Integer.parseInt(values[3]));
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

    public int getRow(){
        return this.grid.length;
    }
    public int getCol(){
        return this.grid[0].length;
    }
    public Object getObject(String s) {
        return switch (s) {
            case "text_baba" -> Material.test_baba.getCharactere();
            case "text_rock" -> Material.test_rock.getCharactere();
            case "text_wall" -> Material.test_wall.getCharactere();
            case "text_flag" -> Material.test_flag.getCharactere();
            case"is" -> Connected.is.getCharactere();
            case "you" -> Property.you.getCharactere();
            case "wall" -> Property.wall.getCharactere();
            case "stop" -> Property.stop.getCharactere();
            case "rock"-> Property.rock.getCharactere();
            case "win" -> Property.win.getCharactere();
            case "baba" -> Property.baba.getCharactere();
            case "push" -> Property.pusher.getCharactere();
            case "flag" -> Property.flag.getCharactere();
            default -> null;
        };
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

    /*public boolean isMaterial(String materiel){
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
    }*/
    public boolean isMateriel(Object obj){
        for(Material prop:Material.values()){
            if (obj.equals(prop)){
                return true;
            }
        }
        return false;
    }

    public boolean isproperty(Object obj){
        for(Property prop:Property.values()){
            if (obj.equals(prop)){
                return true;
            }
        }
        return false;
    }

    public boolean isConnected(Object obj){
        if(obj.equals(Connected.is)){
            return true;
        }
        return false;
    }

    public ArrayList<Rule> therule(){
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

