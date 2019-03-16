package com.kodilla;

import javafx.scene.control.Button;

public class ShipMast extends Button {

    public ShipMast() {
        setStyle("-fx-background-image: url(grey_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

    public int[] getShipMastColumnAndRow(ShipMast shipMast) {
        int column = (int)((shipMast.getLocalToParentTransform().getTx()-81)/27);
        int row = (int)((shipMast.getLocalToParentTransform().getTy()-150)/27);
        int results[] = {1, column, row}; // 1 indicates field with mast (a piece of ship)
        return results;
    }

}
