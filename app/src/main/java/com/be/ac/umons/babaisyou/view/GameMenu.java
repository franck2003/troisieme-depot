package com.be.ac.umons.babaisyou.view;

import com.be.ac.umons.babaisyou.model.Grid;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.be.ac.umons.babaisyou.view.Windows.getI;

public class GameMenu {

    public static String name;
// gerer le fait que la grille ne soit pas null
    GameMenu(Windows window){
        StackPane root = new StackPane();
        Image i = new Image("file:src\\main\\resources\\Image\\image1.png");
        BackgroundImage ima = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background imag = new Background(ima);
        root.setBackground(imag);

        VBox menu = new VBox(5);
        StackPane.setAlignment(menu, Pos.BOTTOM_CENTER);
        window.scene.setRoot(root);

        Rectangle rt = new Rectangle(300, 50);
        rt.setFill(Color.GREY);
        rt.setOpacity(0.2);

        Button play = new Button("PLAY");
        Button setting = new Button("SETTING");
        Button quit = new Button("QUIT");
        Button charger = new Button("CHARGER");

        play.setOnMouseClicked(event -> {
            System.out.println("play");
            try {
                JFrame jFrame = new JFrame();
                name = JOptionPane.showInputDialog(jFrame, "Enter your name");
                if(!Objects.equals(name, "")) {
                    window.Interface("src\\main\\resources\\map\\map" + getI() + ".txt");
                }
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

        charger.setOnMouseClicked(event -> {
            Grid grille = new Grid();
            Component frame = new Component() {
                @Override
                public String getName() {
                    return super.getName();
                }
            };
            JFileChooser fileChooser = new JFileChooser("src\\main\\resources\\map_save\\");
            fileChooser.setMultiSelectionEnabled(false);
            int res = fileChooser.showOpenDialog(frame);
            if(res == JFileChooser.APPROVE_OPTION){
                File fs = fileChooser.getSelectedFile();
                String s = fs.getName().toString();
                try {
                    grille.load_file("src\\main\\resources\\map_save\\"+s);
                    window.Interface("src\\main\\resources\\map_save\\"+s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("CHARGER");
        });


        menu.getChildren().addAll(play, setting, quit, charger);

        //menu.setTranslateX(200);
        //menu.setTranslateY(200);

        root.getChildren().addAll(rt);


        root.getChildren().add(menu);
    }

}
