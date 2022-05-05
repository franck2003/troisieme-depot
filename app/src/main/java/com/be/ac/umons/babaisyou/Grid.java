package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;
import com.be.ac.umons.babaisyou.Graphique.Windows;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.security.PublicKey;
import java.util.*;
//import static com.be.ac.umons.babaisyou.rules.Rule.isValid;

public class Grid implements theRules{

    Position pos;
    private static ArrayList<Entities>[][] grid;
    private final Map<Entities, Direction> list = new HashMap<>();
    Stack<Map<Entities, Direction>> entities_Move = new Stack<>();

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

    @Override
    public void appliRules(Group root) {
        ArrayList<Rule> rules = this.getRules();
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
                            if (Rule.isValid(first.block, second.block, third.block)) {
                                rules.add(new Rule(first.block, second.block, third.block));
                            }
                        }
                    }
                }
            }
            for(Rule ruls:rules){
                System.out.println(ruls.first);
                System.out.println(ruls.second);
                System.out.println(ruls.third);
            }
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

    public boolean IsPushable(Words ref) {
        Words rock = null;
        ArrayList<Rule> rules = this.getRules();
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

    public boolean push(Entities ref, Direction dir, Group root) throws FileNotFoundException {
        ArrayList<Rule> rules = this.getRules();//this.getRules();
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
                    this.grid[charac.position.row][charac.position.col].remove(charac);
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


    public void Move(Direction dir, Group root) throws FileNotFoundException {
        ArrayList<Rule> rules = this.getRules();
        Words baba = null;

        for (Rule args : rules) {
            if (args.third.equals(Words.you)) {
                baba = Words.valueOf(args.first.getName().toLowerCase());
            }
        }
        ArrayList<Entities> test = new ArrayList<Entities>();
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
        for (Entities args:test){
            if(dir.equals(Direction.UP)) {
                list.put(args, Direction.DOWN);
            }
            if(dir.equals(Direction.DOWN)) {
                list.put(args, Direction.UP);
            }
            if(dir.equals(Direction.LEFT)) {
                list.put(args, Direction.RIGHT);
            }
            if(dir.equals(Direction.RIGHT)) {
                list.put(args, Direction.LEFT);
            }
            entities_Move.push(list);
            push(args, dir, root);
            list.put(args, dir);
        }
    }
    public void goBack(Group root) throws FileNotFoundException {
        Map<Entities, Direction> ref = entities_Move.pop();
        Set<Entities> entity = ref.keySet();
        for(Entities arg:entity){
            //push(arg, arg.)
        }
        Iterator<Map.Entry<Entities, Direction>> iterator = ref.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Entities, Direction> entry = iterator.next();
            push(entry.getKey(),entry.getValue(),root);
        }

    }
}












































































































































