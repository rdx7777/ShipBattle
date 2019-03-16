package com.kodilla;

import javafx.scene.control.Button;

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
    }
}
