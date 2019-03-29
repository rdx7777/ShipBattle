package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlSquare extends Button {

    public ControlSquare() {
        setStyle("-fx-background-color: transparent;");
        setMinSize(27, 27);
        setMaxSize(27, 27);
//        setOnActionMethod();
    }

    // method can be used in constructor
/*    public void setOnActionMethod() {
        setOnAction(event -> {
            int column = (int)((getLocalToParentTransform().getTx())/27);
            int row = (int)((getLocalToParentTransform().getTy())/27);
            ShipBattle.example(0, column, row); // CHECK POSITION ONLY
        });
    }*/

    // method for use outside this class
/*
    public static void getPositionAndSetActionOfControlSquare(GridPane grid) {
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
        for(Node node : childrenOfControlSquares) {
            if(node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int)((button.getLocalToParentTransform().getTx())/27);
                    int row = (int)((button.getLocalToParentTransform().getTy())/27);
                    ShipBattle.example(0, column, row); // for check only **************************
                });
            }
        }
    }
*/

}
