package com.kodilla;

import javafx.scene.control.Label;

public class GameLabel extends Label {
    private int width;
    private int height;
    String imageName;

    public GameLabel(int width, int height, String imageName) {
        this.width = width;
        this.height = height;
        this.imageName = imageName;
        String gameLabelUrl = "-fx-background-image: url(" + imageName + ");";
        setMinSize(width, height);
        setStyle(gameLabelUrl);
        setWrapText(true);
    }

}
