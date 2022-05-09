package com.be.ac.umons.babaisyou.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Button extends StackPane {

        Button(String name){
            Text text = new Text(name);
            text.setFont(Font.font(20));
            text.setFill(Color.RED);

            Rectangle rt = new Rectangle(500, 100);
            rt.setOpacity(0.2);
            rt.setFill(Color.BLACK);

            getChildren().addAll(rt, text);

            rt.setOnMouseEntered(event -> {
                rt.setFill(Color.CHOCOLATE);
                text.setFill(Color.BLACK);
            });

            rt.setOnMouseExited(event -> {
                rt.setFill(Color.BLACK);
                text.setFill(Color.RED);
            });
        }
}

