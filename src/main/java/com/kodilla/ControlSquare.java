package com.kodilla;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class ControlSquare extends Button {

    private Pair<Integer, Integer> controlSquareCoordinates;

    public ControlSquare(Pair<Integer, Integer> controlSquareCoordinates) {

        this.controlSquareCoordinates = controlSquareCoordinates;

        setStyle("-fx-background-color: transparent;");
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

    public Pair<Integer, Integer> getControlSquareCoordinates() {
        return controlSquareCoordinates;
    }
}
