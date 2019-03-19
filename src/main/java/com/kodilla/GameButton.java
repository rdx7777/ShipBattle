package com.kodilla;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameButton extends Button {
    private int width;
    private int height;
    private String buttonName;

    public GameButton(int width, int height, String buttonName) {
        this.width = width;
        this.height = height;
        this.buttonName = buttonName;
        setMinSize(width, height);
        setText(buttonName);
        setFont(Font.font("Verdana", FontWeight.MEDIUM, 12));
    }

}
