package com.be.ac.umons.babaisyou;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException {
        try {
            String[][] tab = new String[9][11];
            ArrayList<Entities>[][] grid ;
            Grid grille;
            grille = new Grid("map1.txt");
            for (int i = 0; i < grille.grid.length; i++) {
                for (int j = 0; j < grille.grid[0].length; j++) {
                    if (grille.grid[i][j]!=null) {
                        tab[i][j] = grille.grid[i][j].get(0).name;
                    }else{
                        tab[i][j] = " ";
                    }
                }
            }

            for (int i = 0; i < tab[0].length; i++) {
                for (int j = 0; j < tab.length; j++) {
                    System.out.print(tab[j][i] + " ");
                }
                System.out.println();
            }
        }catch(FileNotFoundException e){
            System.out.println("le fichier n'existe pas");
            e.printStackTrace();

        }
    }
}
