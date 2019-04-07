package com.kodilla;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class ShipMast extends Button {

    private Pair<Integer, Integer> visibleShipMastCoordinates;
    private boolean isShipMastHit; // if hit, isMastHit = true;

    public ShipMast(Pair<Integer, Integer> visibleShipMastCoordinates) {
        this.visibleShipMastCoordinates = visibleShipMastCoordinates;

        setMinSize(27, 27);
        setMaxSize(27, 27);
//        setShipMastAction();
    }

    public Pair<Integer, Integer> getVisibleShipMastCoordinates() {
        return visibleShipMastCoordinates;
    }

    public boolean getIsShipMastHit() {
        return isShipMastHit;
    }

    public void setShipMastHit(boolean shipMastHit) {
        isShipMastHit = shipMastHit;
    }

//    public void setShipMastAction() {
//        setOnAction(event -> {
//            System.out.println("Clicked on column: " + getVisibleShipMastCoordinates().getKey() +
//                    " and on row: " + getVisibleShipMastCoordinates().getValue());
//        });
//    }

}

