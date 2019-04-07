package com.kodilla;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class Hit extends Button {

    private Pair<Integer, Integer> hitCoordinates;

    public Hit(Pair<Integer, Integer> hitCoordinates) {

        this.hitCoordinates = hitCoordinates;

        setStyle("-fx-background-image: url(explosion_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);

    }

}
