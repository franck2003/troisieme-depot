package com.be.ac.umons.babaisyou.Graphique;

import com.be.ac.umons.babaisyou.Direction;
import com.be.ac.umons.babaisyou.Entities;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import com.be.ac.umons.babaisyou.Grid;
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
    private static int i = 1;
    private static int j = i+1;


    private Grid grille;

    public static void setI() {
         i++;
    }

    public void start(Stage stage) {
        this.stage = stage;
        try {
            //Interface("map1.txt");
            Pane root = new Pane();
            root.setPrefSize(600,600);

            gameMenu = new GameMenu();

            root.getChildren().add(gameMenu);
            Image i = new Image("file:src\\main\\resources\\Image\\image.png");
            BackgroundImage ima = new BackgroundImage(i,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
            Background imag = new Background(ima);
            root.setBackground(imag);
            //Windows.PlayMusic("D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\song.wav");
            //Windows.PlayMusic("D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\hello.wav");
            Scene scene = new Scene(root, 600,600);
            //root.setStyle("D:\\projet_baba_is_you_new\\app\\src\\main\\resources\\Image\\image.png");
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
        stage.getIcons().add(new Image("file:src\\main\\resources\\Image\\baba.png"));
        stage.setTitle("BabaIsYou!");
        stage.setScene(scene);
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
                try {
                    this.Interface("map" + String.valueOf(i) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case O -> {
                //System.out.println(i);
                //System.out.println("map"+String.valueOf(i)+".txt");
                try {
                    this.Interface("map" + String.valueOf(i) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case S -> {
                i--;
                try {
                    this.Interface("map" + String.valueOf(i) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            default -> System.out.print("");
        }
        if(i == j){
            root = new Group();
            j++;
            try {
                this.Interface("map" + String.valueOf(i) + ".txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    public void handle(KeyEvent event) {
        try {
            switch (event.getCode()) {
                case RIGHT -> grille.Move(Direction.RIGHT);
                case LEFT -> grille.Move(Direction.LEFT);
                case UP -> grille.Move(Direction.UP);
                case DOWN -> grille.Move(Direction.DOWN);
                case Q -> this.Interface("map"+String.valueOf(i)+".txt");
                default -> System.out.print("");
            }
        } catch (IOException e) {
        }
    }
     */

    public class GameMenu extends Parent{
        GameMenu(){
            Pane root = new Pane();
            VBox menu = new VBox(5);
            menu.setAlignment(Pos.CENTER);

            Rectangle rt = new Rectangle(300, 50);
            rt.setFill(Color.GREY);
            rt.setOpacity(0.2);

            Button play = new Button("PLAY");
            Button setting = new Button("SETTING");
            Button quit = new Button("QUIT");
            Button initRule = new Button("INIT RULES");

            play.setOnMouseClicked(event -> {
                System.out.println("play");
                try {
                    Interface("map"+i+".txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


            setting.setOnMouseClicked(event -> {
                System.out.println("SETTING");
            });


            quit.setOnMouseClicked(event -> {
                System.out.println("QUIT");
            });

            initRule.setOnMouseClicked(event -> {
                System.out.println("INIT RULES");
            });

            root.setTranslateX(150);
            root.setTranslateY(150);

            menu.getChildren().addAll(play, setting, quit, initRule);
            root.getChildren().addAll(rt, menu);

            getChildren().addAll(root);
        }

    }

    public static class Button extends StackPane{


        Button(String name){
            Text text = new Text(name);
            text.setFont(Font.font(20));
            text.setFill(Color.RED);

            Rectangle rt = new Rectangle(300, 50);
            rt.setOpacity(0.2);
            rt.setFill(Color.BLACK);

            getChildren().addAll(rt, text);

            setOnMouseEntered(event -> {
                rt.setFill(Color.CHOCOLATE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                rt.setFill(Color.BLACK);
                text.setFill(Color.RED);
            });
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