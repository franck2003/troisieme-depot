package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Entities {// get
    public  Words block;
    public  int dir;
    public  Position position;
    private ImageView imageView;

    public Entities(Words block, Position position, int dir) {
        this.position = position;
        this.block = block;
        this.dir = dir;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    /*public boolean push(Entities ref, Direction dir) {
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
            if (this.grid[ref.position.row + dir.x][ref.position.col + dir.y].get(i).block.equals(rock)) {
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
        for (int i=0;i<this.grid.length;i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                if (!this.grid[i][j].isEmpty()) {
                    ArrayList<Entities> test = new ArrayList<Entities>();
                    for (Entities args : this.grid[i][j]) {
                        if (args.block.equals(baba)) {
                            test.add(args);
                        }
                    }
                    for (int idx = 0; idx < test.size(); idx++){
                        push(test.get(idx), dir);
                    }
                }
            }
        }
    }
}*/

}