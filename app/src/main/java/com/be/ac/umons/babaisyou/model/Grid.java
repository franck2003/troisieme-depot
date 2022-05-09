package com.be.ac.umons.babaisyou.model;

import com.be.ac.umons.babaisyou.model.rules.Rule;
import com.be.ac.umons.babaisyou.view.Windows;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.*;

public class Grid implements theRules{

    Position pos;
    private ArrayList<Entities>[][] grid;
    private final Map<Entities, Direction> list = new HashMap<>();
    ArrayList<Rule> rules = new ArrayList<>() ;
    private ArrayList<Entities> controllableEntity = new ArrayList<>();
    int p = 1;
    Stack<Map<Entities, Direction>> entities_Move = new Stack<>();

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
                }
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

    /**
     * apply the modification in the root if the rules contains one Rule :first a  NOUN, the second an OPERATOR and the last a NOUN
     * @param root is a container of the scene
     */
    @Override
    public void appliRules(Group root) {
        getRules();
        Words baba = null;
        for(Rule rule: rules){
            if(rule.third.equals(Words.you)){
                baba = rule.first;
                break;
            }
        }
        if(baba == null && p == 1){
            Windows.PlayMusic("src\\main\\resources\\Image\\game_over.wav");
            p++;
        }
        for (Rule args : rules) {
            if(args.third.isType(TypeOfWords.NOUN)) {
                for (ArrayList<Entities>[] arrayLists : this.grid) {
                    for (ArrayList<Entities> arrayList : arrayLists) {
                        if (!arrayList.isEmpty()) {
                            for (Entities entities : arrayList) {
                                if (entities.block.name().equals(args.first.getName())){
                                    Entities newEntity = new Entities(Words.valueOf(Words.valueOf(args.third.getName()).getName()), entities.position,entities.dir);
                                    arrayList.add(newEntity);
                                    arrayList.remove(entities);
                                    root.getChildren().remove(entities.getImageView());
                                    try {
                                        Image image = new Image(new FileInputStream(newEntity.block.getPath()));
                                        ImageView imageView = new ImageView(image);
                                        root.getChildren().add(imageView);
                                        newEntity.setImageView(imageView);
                                        imageView.setTranslateX(newEntity.position.row * 40);
                                        imageView.setTranslateY(newEntity.position.col * 40);
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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

    /**
     * find all rules valid Vertical or Horizontal in the Grid
     */
    public void getRules() {
        rules.clear();
        List<Entities> list = getIs();
        for (Entities second : list) {
            if(ingrid(second.position.row - 1, second.position.col) && ingrid(second.position.row+1,second.position.col)) {
                if (this.grid[second.position.row - 1][second.position.col].size() != 0 && this.grid[second.position.row + 1][second.position.col].size() != 0) {
                    for (Entities first : this.grid[second.position.row - 1][second.position.col]) {
                        for (Entities third : this.grid[second.position.row + 1][second.position.col]) {
                            if (Rule.isValid(first.block, second.block, third.block)) {
                                rules.add(new Rule(first.block, second.block, third.block));
                                //first.getImageView().setOpacity(0.5);
                            }
                        }
                    }
                }
            }
            if(ingrid(second.position.row , second.position.col-1) && ingrid(second.position.row,second.position.col+1)) {
                if (this.grid[second.position.row][second.position.col - 1].size() != 0 || this.grid[second.position.row][second.position.col + 1].size() != 0) {
                    for (Entities first : this.grid[second.position.row][second.position.col - 1]) {
                        for (Entities third : this.grid[second.position.row][second.position.col + 1]) {
                            addValidRules(first.block, second.block, third.block);
                            }
                        }
                    }
                }
            }

    }
    /**
     * Add the parameters in a valid rule : first must be a NOUN, the second an OPERATOR and the last a PROPERTY or NOUN
     * @param first Words Which has the type NOUN
     * @param second Words Which has the type OPERATOR
     * @param third Words Which has the type PROPERTY or NOUN
     */

    private void addValidRules(Words first, Words second, Words third) {
        if (Rule.isValid(first, second, third)) {
            rules.add(new Rule(first, second, third));
        }
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


    /**
     * Take a Words and, to see if he can be pushed.
     * @param ref verify the condition with this block.
     * @return true or false if and only if the Words is pushable.
     */
    public boolean IsPushable(Words ref) {
        Words rock = null;
        getRules();
        for (Rule args : rules) {
            if (args.third.equals(Words.push)) {
                rock = Words.valueOf(args.first.getName().toLowerCase());
            }
        }
        ArrayList<Words> n = new ArrayList<>();
        n.add(rock);
        n.add(Words.push);
        n.add(Words.win);
        n.add(Words.stop);
        n.add(Words.you);
        return n.contains(ref) || ref.isType(TypeOfWords.NOUN) || ref.isType(TypeOfWords.OPERATOR);
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
        getRules();
        ArrayList<Entities> entities = new ArrayList<>();
        Words wall = null;
        Words rock = null;
        Words flag = null;
        Words hot = null;
        Words baba = null;
        boolean isPush = true;
        for (Rule args : rules) {
            if (args.third.equals(Words.stop)) {
                wall = Words.valueOf(args.first.getName().toLowerCase());
            }
            if (args.third.equals(Words.push)) {
                rock = Words.valueOf(args.first.getName().toLowerCase());
            }
            if (args.third.equals(Words.you)) {
                baba = Words.valueOf(args.first.getName().toLowerCase());
            }
            if(args.third.equals(Words.sink)){
                hot = Words.valueOf(args.first.getName().toLowerCase());
            }
            if(args.third.equals(Words.win)){
                flag = Words.valueOf(args.first.getName().toLowerCase());
            }
        }
        if (!ingrid(ref.position.row + dir.x, ref.position.col + dir.y)) {
            return false;
        }
        for (Entities args : this.grid[ref.position.row + dir.x][ref.position.col + dir.y]) {
            if(args.block.equals(flag) && ref.block.equals(baba)){
                Windows.setI();
            }
        }

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
        for (Entities args : this.grid[ref.position.row + dir.x][ref.position.col + dir.y]) {
            if (args.block.equals(wall)) {
                return false;
            }
        }
        for (int i = 0; i < this.grid[ref.position.row + dir.x][ref.position.col + dir.y].size(); i++) {
            if (this.IsPushable(this.grid[ref.position.row + dir.x][ref.position.col + dir.y].get(i).block)) {
                isPush = push(this.grid[ref.position.row + dir.x][ref.position.col + dir.y].get(i), dir, root);
            }
        }
        if(isPush){
            removeEntities(ref.position.row, ref.position.col, ref );
            ref.position.row += dir.x;
            ref.position.col += dir.y;
            setEntities(ref.position.row, ref.position.col, ref );;

            ImageView img = ref.getImageView();
            if(img != null){
                img.setTranslateX(img.getTranslateX() + (dir.x * 40));
                img.setTranslateY(img.getTranslateY() + (dir.y * 40));
            }


        }
        return isPush;
    }

    /**
     * check in a grid all entities controllable
     * @return entityControllable
     */
    public ArrayList<Entities> ControllableEntity(){
        Words baba = null;
        ArrayList<Entities> test = new ArrayList<>();
        getRules();
        for (Rule args : rules) {
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
        getRules();
        controllableEntity = ControllableEntity();
        for (Entities entities:controllableEntity){
            push(entities, dir, root);
        }
    }

    public void writeInFile(int i){
        try {
        String line ;
        PrintWriter fw = new PrintWriter(new FileOutputStream("src\\main\\resources\\map_save\\map"+i+".txt"), true);
        for (ArrayList<Entities>[] arrayLists : this.grid) {
            for (ArrayList<Entities> arrayList : arrayLists) {
                if (!arrayList.isEmpty()) {
                    for (Entities entities : arrayList) {
                        line = entities.block.toString()+ " "+ entities.position.row+ " " + entities.position.col+ " " + entities.dir;
                        fw.write(line);
                        fw.println();
                        System.out.println(line);
                    }
                }
            }
        }
        fw.close();
    }catch (IOException e){
            System.out.println("je n'ai pas trouve");
        }
    }
}












































































































































