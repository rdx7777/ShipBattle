package com.kodilla;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class Missed extends Button {

    private Pair<Integer, Integer> missedCoordinates;

    public Missed(Pair<Integer, Integer> missedCoordinates) {

        this.missedCoordinates = missedCoordinates;

        setStyle("-fx-background-image: url(missed_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);

    }

}
