package com.be.ac.umons.babaisyou.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;

import static com.be.ac.umons.babaisyou.view.Windows.getI;

public class GameMenu {

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
        Button initRule = new Button("INIT RULES");

        play.setOnMouseClicked(event -> {
            System.out.println("play");
            try {
                window.Interface("src\\main\\resources\\map\\map"+ getI()+".txt");
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


        menu.getChildren().addAll(play, setting, quit, initRule);

        //menu.setTranslateX(200);
        //menu.setTranslateY(200);

        root.getChildren().addAll(rt);


        root.getChildren().add(menu);
    }

}
