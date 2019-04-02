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

    public void setShipMastAction() {
        setOnAction(event -> {
            System.out.println("Clicked on column: " + getVisibleShipMastCoordinates().getKey() +
                    " and on row: " + getVisibleShipMastCoordinates().getValue());
        });
    }

//    public static void getPositionAndSetActionOfShipMast(GridPane grid) {
//        ShipMast shipMast = new ShipMast();
//        ObservableList<Node> childrenOfShipMasts = grid.getChildren();
//        for(Node node : childrenOfShipMasts) {
//            if(node.getClass() == shipMast.getClass()) { // if(node.getClass().isInstance(shipMast))
//                ShipMast button = (ShipMast) node;
//                button.setOnAction(event -> {
//                    int column = (int)((button.getLocalToParentTransform().getTx())/27);
//                    int row = (int)((button.getLocalToParentTransform().getTy())/27);
//                    ShipBattle.example(1, column, row);
//                });
//            }
//        }
//    }

}

