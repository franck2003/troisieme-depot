package com.be.ac.umons.babaisyou.Graphique;

import com.be.ac.umons.babaisyou.Entities;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import com.be.ac.umons.babaisyou.Grid;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Windows extends Application {
    @Override
    public void start(Stage stage) {
        try {
            GridPane root = new GridPane();//ensemble dans lequel on va stoker nos composants
            Scene scene = new Scene(root, 600,600, Color.BLACK);
            Grid grille = new Grid("map1.txt");
            Image image ;
            ImageView imageView;
            for(int i=0; i<grille.grid.length;i++){
                for(int j=0; j<grille.grid[i].length;j++){
                    if (grille.grid[i][j]!=null){
                        for(Entities args:grille.grid[i][j]) {
                            image = new Image(new FileInputStream(args.block.getPath()));
                            imageView = new ImageView(image);
                            root.add(imageView, args.position.row, args.position.col);
                        }
                    }
                }
            }
            root.setAlignment(Pos.CENTER);
            stage.setTitle("Hello!");
            // platform.exit() permet de fermer l'application etb pour cela il faut import platform de la classe application(javafx.application.platform)
            stage.setScene(scene);
            stage.setResizable(false);// permet d'empecher l'agrandissement ou le raccourciument
            stage.show();//permet d'afficher la fenetre
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[]args){
        launch(args);
    }
}
