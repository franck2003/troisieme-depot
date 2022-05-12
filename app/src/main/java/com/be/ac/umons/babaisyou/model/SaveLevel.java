package com.be.ac.umons.babaisyou.model;

import com.be.ac.umons.babaisyou.view.Windows;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveLevel {

    public static void save(Grid grille, String s, int i){
        try {
            String line ;
            PrintWriter fw = new PrintWriter(new FileOutputStream("src\\main\\resources\\map_save\\"+s+".txt"), true);
            String row =   " "+grille.getCol();
            String col = " "+grille.getCol();
            int p = Windows.getI();
            fw.write(String.valueOf(p));
            fw.println();
            fw.write(String.valueOf(grille.getRow()) + " ");
            fw.write(String.valueOf(grille.getCol()));
            fw.println();
            for (ArrayList<Entities>[] arrayLists : grille.getGrid()) {
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
