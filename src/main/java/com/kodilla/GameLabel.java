package com.kodilla;

import javafx.scene.control.Label;

public class GameLabel extends Label {
    private int width;
    private int heigth;
    String imageName;

    public GameLabel(int width, int heigth, String imageName) {
        this.width = width;
        this.heigth = heigth;
        this.imageName = imageName;
        String gameLabelUrl = "-fx-background-image: url(" + imageName + ");";
        setMinSize(width, heigth);
        setStyle(gameLabelUrl);
        setWrapText(true);
    }
}
