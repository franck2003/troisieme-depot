package com.be.ac.umons.babaisyou.model;

import com.be.ac.umons.babaisyou.model.rules.Rule;
import com.be.ac.umons.babaisyou.view.Windows;
import com.sun.jdi.Value;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.*;

public class Grid {

    Position pos;
    private ArrayList<Entities>[][] grid;
    private final Map<Entities, Direction> list = new HashMap<>();
    ArrayList<Rule> rules = new ArrayList<>() ;
    Rule rule = new Rule();
    int p = 1;
    Stack<Map<Entities, Direction>> entities_Move = new Stack<>();
    ArrayList<Entities> list_Is = new ArrayList<>();

    public Grid(){

    }
    /**
     * Create the Arraylist<Entities>[][] with the file map given in parameter, if the file name not exist : send exception to the main.
     * @param map access path of each file in the game
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public Grid(String map) throws IOException {// constructeur qui permet de definir le plateau ou la grille qui est un tableau a 2 dimension d'entitie
        String line;
        String[] values;
        Entities entities;
        BufferedReader lecture = new BufferedReader(new FileReader(map));
        while ((line = lecture.readLine()) != null) {
            values = line.split(" ");
             if(values.length == 2) {
                this.grid = new ArrayList[Integer.parseInt(values[0])][Integer.parseInt(values[1])];
            } if(values.length == 3||values.length == 4){
                pos = new Position(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                int dir = values.length == 3 ? 0 : Integer.parseInt(values[3]);
                entities = new Entities(getWords(values[0]), pos, dir);
                if(entities.block.equals(Words.is)){
                    list_Is.add(entities);
                }
                if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
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

    public Rule get_Rule(){
        return this.rule;
    }

    public  ArrayList<Entities> getList_Is() {
        return this.list_Is;
    }

    public int get_P(){
        return this.p;
    }

    public void set_P(){
        this.p++;
    }

    /**
     * get the grid of the game since it has been defined as being private
     * @return grid
     */
    public ArrayList<Entities>[][] getGrid(){
        return this.grid;
    }

    /**
     *recovers numbers of row
     * @return grid.length number of row in the file txt
     */
    public int getRow() {
        return this.grid.length;
    }

    /**
     *recovers numbers of cols
     * @return grid.length number of cols in the file txt
     */
    public int getCol() {
        return this.grid[0].length;
    }

    /**
     * iterate on each element of enum and return corresponding element according to the character String passed as parameter
     * @param s a String
     * @return Words
     */
    public Words getWords(String s) {
        return Words.valueOf(s);
    }

    /**
     * allows remove entity in a grid on a Position
     * @param i
     * @param j
     * @param entities
     */
    public void removeEntities(int i, int j, Entities entities) {
        this.grid[i][j].remove(entities);
    }

    public void setEntities(int i, int j, Entities entities) {
        this.grid[i][j].add(entities);
    }


    /**
     * check if to coordinated is in a Grid
     * @param i first coordinste
     * @param j second coordinate
     * @return true if a coordinated is in a Grid else false
     */
    public boolean ingrid(int i, int j) {
        return i < getRow() && j < getCol() && i >= 0 && j >= 0;
    }

    public ArrayList<Entities> getItemsAtPos(int i, int j){
        if(ingrid(i, j)){
            return this.grid[i][j];
        }
        return null;
    }

    public boolean check_Hot(Entities ref, Direction dir, Group root) throws FileNotFoundException{
        ArrayList<Entities> entities = new ArrayList<>();
        for (Entities args : getItemsAtPos(ref.position.row+ dir.x, ref.position.col+ dir.y)) {
            if (chech_rules(Words.sink, args) && chech_rules(Words.push, ref) || chech_rules(Words.sink, args) && chech_rules(Words.you, ref)) {
                entities.add(ref);
                entities.add(args);
                break;
            }
        }
            if (entities.size() != 0) {
                //System.out.println(entities.get(0).block);
                //System.out.println(entities.get(1).block);
                for (Entities charac : entities) {
                    //System.out.println(entities.size());
                    //System.out.println(charac.block);
                    ImageView img = charac.getImageView();
                    root.getChildren().remove(img);
                    //System.out.println(this.grid[charac.position.row][]);
                    removeEntities(charac.position.row, charac.position.col, charac);
                }
                return true;
            }
            return false;
        }

    public boolean chech_rules(Words words, Entities entity){
        for (Rule args : rule.get_rules_list()) {
            if (args.first.getName().equals(entity.block.getName()) && args.third.equals(words)){
                return true;
            }
        }
        return false;
    }
    /**
     * allows you to push entity in the grid and give a modification in the scene
     * @param ref is entity in a Grif
     * @param dir Direction enter by a gamer
     * @param root the container in the scene
     * @return true if entity has been pushed else false
     * @throws FileNotFoundException
     */
    public boolean push(Entities ref, Direction dir, Group root) throws FileNotFoundException {
        ArrayList<Entities> item_on_next_pos = this.getItemsAtPos(ref.position.row+dir.x, ref.position.col+dir.y);
        ArrayList<Entities> itemToMove = new ArrayList<Entities>();
        boolean isPush = true;
        if(!ingrid(ref.position.row+dir.x, ref.position.col+dir.y)){
            return false;
        }
        for (Entities e : item_on_next_pos) {
            if (chech_rules(Words.push, e) || e.block.isType(TypeOfWords.NOUN) || e.block.isType(TypeOfWords.OPERATOR) || e.block.isType(TypeOfWords.PROPERTY)) {
                itemToMove.add(e);
            }
        }


        for(Entities item:itemToMove){
            isPush = push(item, dir, root);
        }

        if(check_Hot(ref,dir,root)){
            return true;
        }

        for(Entities e:item_on_next_pos) {
            if (chech_rules(Words.stop, e)){
                return false;
            }
            if(chech_rules(Words.win, e) && chech_rules(Words.you, ref)){
                System.out.println("gagne");
                System.out.println(Windows.getI());
                Windows.setI();
            }

        }
        if(isPush){
            removeEntities(ref.position.row, ref.position.col, ref );
            ref.position.row += dir.x;
            ref.position.col += dir.y;
            setEntities(ref.position.row, ref.position.col, ref );

            ImageView img = ref.getImageView();
            if(img != null){
                img.setTranslateX(img.getTranslateX() + (dir.x * 40));
                img.setTranslateY(img.getTranslateY() + (dir.y * 40));
            }
        }

        /*for (Entities args : this.grid[ref.position.row + dir.x][ref.position.col + dir.y]) {
            if(args.block.equals(flag) && ref.block.equals(baba)){
                Windows.setI();
            }
        }*/
        return isPush;
    }


        /*

        for (Entities args : this.grid[ref.position.row + dir.x][ref.position.col + dir.y]) {
            if ((args.block.equals(hot) && ref.block.equals(baba) || (args.block.equals(hot) && ref.block.equals(rock)))) {
                entities.add(ref);
                entities.add(args);
            }
            if (entities.size() != 0) {

                for (Entities charac : entities) {
                    ImageView img = charac.getImageView();
                    root.getChildren().remove(img);
                    removeEntities(charac.position.row, charac.position.col, charac);
                }
                return true;
            }
        }

     */
        //return true;


    /**
     * check in a grid all entities controllable
     * @return entityControllable
     */
    public ArrayList<Entities> ControllableEntity(){
        Words baba = null;
        ArrayList<Entities> test = new ArrayList<>();
        rule.getRules(this);
        for (Rule args : rule.get_rules_list()) {
            if (args.third.equals(Words.you)) {
                baba = Words.valueOf(args.first.getName().toLowerCase());
            }
        }
        for (ArrayList<Entities>[] arrayLists : this.grid) {
            for (ArrayList<Entities> arrayList : arrayLists) {
                if (!arrayList.isEmpty()) {
                    for (Entities args : arrayList) {
                        if (args.block.equals(baba)) {
                            test.add(args);
                        }
                    }
                }
            }
        }
        return test;
    }

    /**
     * allows you to move all entity touchy to move and apply all modification in a root
     * @param dir is a Direction enter by the gamer
     * @param root is a container of the scene
     * @throws FileNotFoundException
     */
    public void Move(Direction dir, Group root) throws FileNotFoundException {
        rule.getRules(this);
        ArrayList<Entities> controllableEntity = ControllableEntity();
        for (Entities entities: controllableEntity){
            push(entities, dir, root);
        }
    }

    public void load_file(String map) throws IOException {// constructeur qui permet de definir le plateau ou la grille qui est un tableau a 2 dimension d'entitie
        String line;
        String[] values;
        Entities entities;
        BufferedReader lecture = new BufferedReader(new FileReader(map));
        while ((line = lecture.readLine()) != null) {
            values = line.split(" ");
            if(values.length == 1){
                Windows.putI(Integer.parseInt(values[0]));
                Windows.putJ(Integer.parseInt(values[0])+1);
            }
            if (values.length == 2) {
                this.grid = new ArrayList[Integer.parseInt(values[0])][Integer.parseInt(values[1])];
            } else if(values.length == 4){
                pos = new Position(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                int dir = values[3].equals("0") ? 0 : Integer.parseInt(values[3]);
                entities = new Entities(getWords(values[0]), pos, dir);
                if(entities.block.equals(Words.is)){
                    list_Is.add(entities);
                }
                if (this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] == null)
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])] = new ArrayList<Entities>();
                this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add(entities);
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

}












































































































































