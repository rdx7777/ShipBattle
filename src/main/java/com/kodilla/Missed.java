package com.kodilla;

import javafx.scene.control.Button;

public class Missed extends Button {

    public Missed() {
        setStyle("-fx-background-image: url(missed_square.jpg);");
//        setStyle("-fx-background-image: url(explosion_square.jpg);");
        setStyle("-fx-opacity: 0.5"); // do sprawdzenia, czy działa na grafice
        // (bo na pustym polu nie ma efektu)
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

}
