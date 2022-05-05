package com.be.ac.umons.babaisyou;

//import com.be.ac.umons.babaisyou.myRules.Rule;

import java.io.IOException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException {
        /*try {
            /*ArrayList<Rule> rules = new ArrayList<Rule>();
            Object [][] tab = new Object[9][11];
            Grid grille;
            grille = new Grid("map1.txt");
            Direction dir = Direction.RIGHT;
            grille.Move(dir);
            for (int i = 0; i < grille.grid.length; i++) {
                for (int j = 0; j < grille.grid[0].length; j++) {
                    if (grille.grid[i][j].size() != 0) {
                        tab[i][j] = grille.grid[i][j].get(0).block;
                    }else{
                        tab[i][j] = " ";
                    }
                }
            }
            //rules = grille.getRules();

            for (int i = 0; i < tab[0].length; i++) {
                for (Object[] objects : tab) {
                    System.out.print(objects[i] + " ");
                }
                System.out.println();
            }


        }catch(FileNotFoundException e){
            System.out.println("le fichier n'existe pas");
            e.printStackTrace();

        }
    }*/
    }
}
