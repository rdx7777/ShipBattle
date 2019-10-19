package com.kodilla;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class Missed extends Button {

    private Pair<Integer, Integer> missedCoordinates;

    public Missed(Pair<Integer, Integer> missedCoordinates) {

        this.missedCoordinates = missedCoordinates;

        setMinSize(25, 25);
        setMaxSize(25, 25);
        setStyle("-fx-background-image: url(missed_square.jpg);");
    }
}
