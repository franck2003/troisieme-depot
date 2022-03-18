package com.be.ac.umons.babaisyou;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grid {
    Position pos;
    ArrayList<Entities>[][] grid;
    ArrayList<Entities> obj = null;

    public Grid(ArrayList<Entities>[][] grid, String map) throws IOException {// constructeur qui permet de definir le plateau ou la grille qui est un tableau a 2 dimension d'entities
        this.grid = grid;
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
                    obj.add(entities);
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add((Entities) obj);
                } else {
                    entities = new Entities(values[0], pos, Integer.parseInt(values[3]));
                    obj.add(entities);
                    this.grid[Integer.parseInt(values[1])][Integer.parseInt(values[2])].add((Entities) obj);
                }
            }
        }
    }

    public ArrayList<Entities> getEntitities(int x, int y) {
        ArrayList<Entities> entities;
        ArrayList<Entities> identities = null;
        for (ArrayList<Entities>[] arrayLists : this.grid) {
            for (ArrayList<Entities> arrayList : arrayLists) {
                for (ArrayList<Entities> args : arrayList) {
                    if (args.get(1).position.row == x && args.get(1).position.col == y) {// je pense que je devrais mettre un for ici pour parcourir chaqu'entites
                        identities.add((Entities) args);
                        return identities;
                    }
                }
            }

        }
        return null;
    }

    // ecrire une methode qui prends en parametre une entites et retourne l'indice a la i ieme position
    public ArrayList<Entities> you() {
        ArrayList<Entities> entities;
        for (ArrayList[] arrayLists : this.grid) {
            for (ArrayList arrayList : arrayLists) {
                if (arrayList != null) {
                    entities = arrayList;
                    for (ArrayList<Entities> args : entities) {
                        if (args.get(0).get(0).equals("you")) {// je comprends pas pourquoi
                            return args;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Object getobjetthancanmove() {// retourne l'objet qui peut bouger
        ArrayList<Entities> entities = you();
        int x = entities.get(1).position.row;
        int y = entities.get(1).position.col;
        ArrayList<Entities> connect = getEntitities(x, y + 1);
        ArrayList<Entities> objet = getEntitities(x, y + 2);
        if (connect == null || objet == null) {
            System.out.println("vous avez perdu");
        } else if (connect.get(0).get(0).equals("is")) {
            return objet.get(0).get(0);
        } else {
            connect = getEntitities(x + 1, y);
            objet = getEntitities(x + 2, y);
            if (connect == null || objet == null) {
                System.out.println("vous avez perdu");
            } else if (connect.get(0).get(0).equals("is")) {
                return objet.get(0).get(0);
            }
        }
        return null;
    }
}
