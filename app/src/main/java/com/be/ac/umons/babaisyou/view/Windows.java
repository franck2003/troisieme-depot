package com.be.ac.umons.babaisyou.view;

import com.be.ac.umons.babaisyou.model.Direction;
import com.be.ac.umons.babaisyou.model.Entities;
import com.be.ac.umons.babaisyou.model.Direction;
import com.be.ac.umons.babaisyou.model.Entities;
import com.be.ac.umons.babaisyou.model.Grid;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.be.ac.umons.babaisyou.model.Grid;
import javafx.scene.text.Text;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import javax.sound.sampled.*;

public class Windows extends Application implements EventHandler<KeyEvent> {

    GameMenu gameMenu;
    Stage stage;
    Group root;
    Scene scene ;
    Button save;
    private static int i = 1;
    private static int j = i+1;


    private Grid grille;

    public Grid getGrille(){
        return grille;
    }


    public static void setI() {
         i++;
    }
    public static int getI(){return i;}

    public void start(Stage stage) {
        this.stage = stage;
        try {
            root = new Group();
            //root.setPrefSize(600,600);

            scene = new Scene(root, 900,800);
            gameMenu = new GameMenu(this);
            stage.getIcons().add(new Image("file:src\\main\\resources\\Image\\baba.png"));
            stage.setTitle("BabaIsYou!");
            stage.setScene(scene);
            stage.show();


            Windows.PlayMusicLoop("D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\song.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch(args);

    }

    public void Interface(String map) throws FileNotFoundException {
        root = new Group();
        Scene scene = new Scene(root, 900, 800, Color.BLACK);
        save = new Button("SAVE");
        root.getChildren().add(save);
        try {
            grille = new Grid(map);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Image image;
        ImageView imageView;
        ArrayList<Entities>[][] ref = grille.getGrid();

        for (int i = 0; i < ref.length; i++) {
            for (int j = 0; j < ref[0].length; j++) {
                for (Entities args : ref[i][j]) {
                    try {

                        image = new Image(new FileInputStream(args.block.getPath()));
                        imageView = new ImageView(image);
                        if(args.block.toString().equals("wall")){
                            imageView.setOpacity(0.4);
                        }
                        root.getChildren().add(imageView);
                        args.setImageView(imageView);
                        imageView.setTranslateX(args.position.row * 40);
                        imageView.setTranslateY(args.position.col * 40);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }


        root.setTranslateX(100);
        root.setTranslateY(90);
        //stage.setIconified(true);
        stage.setScene(scene);
        save.setOnMouseClicked(event -> {
            grille.writeInFile(Windows.getI());
        });
        scene.setOnKeyPressed(this);
        stage.setResizable(false);// permet d'empecher l'agrandissement ou le raccourciument
        stage.show();//permet d'afficher la fenetre}
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT -> {
                try {
                    grille.Move(Direction.RIGHT, root);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Windows.PlayMusic("src\\main\\resources\\Image\\saut.wav");
            }
            case LEFT -> {
                try {
                    grille.Move(Direction.LEFT, root);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Windows.PlayMusic("src\\main\\resources\\Image\\saut.wav");
            }
            case UP -> {
                try {
                    grille.Move(Direction.UP, root);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Windows.PlayMusic("src\\main\\resources\\Image\\saut.wav");
            }
            case DOWN -> {
                try {
                    grille.Move(Direction.DOWN, root);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Windows.PlayMusic("src\\main\\resources\\Image\\saut.wav");
            }
            case P -> {
                //System.out.println(i);
                //System.out.println("map"+String.valueOf(i)+".txt");
                i++;
                j++;
                try {
                    this.Interface("src\\main\\resources\\map\\map" + String.valueOf(i) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case O -> {
                //System.out.println(i);
                //System.out.println("map"+String.valueOf(i)+".txt");
                try {
                    this.Interface("src\\main\\resources\\map\\map" + String.valueOf(i) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case S -> {
                i--;
                j--;
                try {
                    this.Interface("src\\main\\resources\\map\\map" + String.valueOf(i) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case N -> {
                System.out.println("N");
                grille.writeInFile(i);
            }
            default -> System.out.print("");
        }

        grille.appliRules(root);

        if(i == j){
            root = new Group();
            j++;
            try {
                this.Interface("src\\main\\resources\\map\\map" + String.valueOf(i) + ".txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void PlayMusicLoop(String path){

        try{

            File file = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void PlayMusic(String path){

        try{

            File file = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}