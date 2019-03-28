package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ShipMast extends Button {

    public ShipMast() {
        setStyle("-fx-background-image: url(grey_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

//    public static void getPositionAndSetActionOfShipMast(GridPane grid) {
//        ShipMast shipMast = new ShipMast();
//        ObservableList<Node> childrenOfShipMasts = grid.getChildren();
//        for(Node node : childrenOfShipMasts) {
//            if(node.getClass() == shipMast.getClass()) { // if(node.getClass().isInstance(shipMast))
//                ShipMast button = (ShipMast) node;
//                button.setOnAction(event -> {
//                    int column = (int)((button.getLocalToParentTransform().getTx()-81)/27);
//                    int row = (int)((button.getLocalToParentTransform().getTy()-150)/27);
//                    ShipBattle.example(1, column, row);
//                });
//            }
//        }
//    }

}

