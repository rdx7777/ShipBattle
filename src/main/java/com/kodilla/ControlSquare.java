package com.kodilla;

import javafx.scene.control.Button;

public class ControlSquare extends Button {

    public ControlSquare() {
        setStyle("-fx-background-color: transparent;");
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

    public int[] getControlSquareColumnAndRow(ControlSquare controlSquare) {
        int column = (int)((controlSquare.getLocalToParentTransform().getTx()-81)/27);
        int row = (int)((controlSquare.getLocalToParentTransform().getTy()-150)/27);
        int results[] = {0, column, row}; // 0 indicates empty field (controlled by ControlSquare object)
        return results;
    }


}
