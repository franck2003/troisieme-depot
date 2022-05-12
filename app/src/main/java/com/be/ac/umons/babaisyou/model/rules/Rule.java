package com.be.ac.umons.babaisyou.model.rules;

import com.be.ac.umons.babaisyou.model.Entities;
import com.be.ac.umons.babaisyou.model.Grid;
import com.be.ac.umons.babaisyou.model.TypeOfWords;
import com.be.ac.umons.babaisyou.model.Words;
import com.be.ac.umons.babaisyou.view.Windows;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rule{
    public Words first;
    public Words second;
    public Words third;
    private ArrayList<Rule> rules = new ArrayList<>();

    /**
     * Constructor of a rule with her 3 block
     * @param first is a first Words
     * @param second is a second Words
     * @param third is a third Words
     */

    public Rule(Words first, Words second, Words third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Rule(){
        this.first = null;
        this.second = null;
        this.third = null;
    }

    public ArrayList<Rule> get_rules_list(){
        return this.rules;
    }

    /**
     *
     * Check if a rule is valid or not with 3 conditions on her 3 Words : first Words has to be a type NOUN,
     * the second has to be a type OPERATOR and the last has to be a type PROPERTY or a NOUN .
     * @param first Words for NOUN
     * @param second Words for OPERATOR
     * @param third Words for NOUN or PROPERTY
     * @return true if valid rule else false
     */
    public static boolean isValid(Words first, Words second, Words third){
        var a = first.isType(TypeOfWords.NOUN);
        var b = second.isType(TypeOfWords.OPERATOR);
        var c = (third.isType(TypeOfWords.PROPERTY) || (third.isType(TypeOfWords.NOUN)));
        return a && b && c;
    }

    /**
     * find all rules valid Vertical or Horizontal in the Grid
     */
    public void getRules(Grid grille) {
        rules = new ArrayList<>();
        List<Entities> list = grille.getList_Is();
        for (Entities second : list) {
            if(grille.ingrid(second.position.row - 1, second.position.col) && grille.ingrid(second.position.row+1,second.position.col)) {
                if (grille.getGrid()[second.position.row - 1][second.position.col].size() != 0 && grille.getGrid()[second.position.row + 1][second.position.col].size() != 0) {
                    for (Entities first : grille.getGrid()[second.position.row - 1][second.position.col]) {
                        for (Entities third : grille.getGrid()[second.position.row + 1][second.position.col]) {
                            if (Rule.isValid(first.block, second.block, third.block)) {
                                addValidRules(first.block, second.block, third.block);
                            }
                        }
                    }
                }
            }
            if(grille.ingrid(second.position.row , second.position.col-1) && grille.ingrid(second.position.row,second.position.col+1)) {
                if (grille.getGrid()[second.position.row][second.position.col - 1].size() != 0 || grille.getGrid()[second.position.row][second.position.col + 1].size() != 0) {
                    for (Entities first : grille.getGrid()[second.position.row][second.position.col - 1]) {
                        for (Entities third : grille.getGrid()[second.position.row][second.position.col + 1]) {
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
     * apply the modification in the root if the rules contains one Rule :first a  NOUN, the second an OPERATOR and the last a NOUN
     * @param root is a container of the scene
     */

    public void appliRules(Grid grille, Group root) {
        getRules(grille);
        Words baba = null;
        for(Rule rule: rules) {
            if (rule.third.equals(Words.you)) {
                baba = rule.first;
            }
            if(rule.third.equals(Words.win) && rule.first.equals(baba)){
                Windows.setI();
            }
        }
        if(baba == null && grille.get_P() == 1){
            Windows.PlayMusic("src\\main\\resources\\Image\\game_over.wav");
            grille.set_P();
        }
        for (Rule args : rules) {
            if(args.third.isType(TypeOfWords.NOUN)) {
                for (ArrayList<Entities>[] arrayLists : grille.getGrid()) {
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
}
