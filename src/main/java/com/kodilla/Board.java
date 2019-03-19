package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Board {
    private GridPane grid;

    public Board(GridPane grid) {
        this.grid = grid;
    }

    public GridPane getGrid() {
        return grid;
    }

//    public void getPositionAndSetActionOfControlSquare() {
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
