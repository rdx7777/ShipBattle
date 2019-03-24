package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class ControlSquare extends Button {

    public ControlSquare() {
        setStyle("-fx-background-color: transparent;");
        setMinSize(27, 27);
        setMaxSize(27, 27);
//        setOnActionMethod();
    }

/*
    public void setOnActionMethod() {
        setOnAction(event -> {
            int column = (int)((getLocalToParentTransform().getTx()-81)/27);
            int row = (int)((getLocalToParentTransform().getTy()-150)/27);
            ShipBattle.example(0, column, row); // CHECK POSITION ONLY
        });
    }
*/

//    public static void getPositionAndSetActionOfControlSquare(GridPane grid) {
//        ControlSquare controlSquare = new ControlSquare();
//        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
//        for(Node node : childrenOfControlSquares) {
//            if(node.getClass() == controlSquare.getClass()) {
//                ControlSquare button = (ControlSquare) node;
//                button.setOnAction(event -> {
//                    int column = (int)((button.getLocalToParentTransform().getTx()-81)/27);
//                    int row = (int)((button.getLocalToParentTransform().getTy()-150)/27);
//                    ShipBattle.example(0, column, row);
//                });
//            }
//        }
//    }

}
