package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ComputerBoard {
    private GridPane grid;
    private int[][] computerBoard =  new int[10][10];

    public ComputerBoard(GridPane grid) {
        this.grid = grid;
    }

    public void createComputerBoard() {
        for(int i = 0; i < 10; i++) {
            for(int n = 0; n < 10; n++) {
                grid.add(new ControlSquare(), i + 12, n + 4);
            }
        }
    }

    public void setEmptyComputerBoard() {
        for(int i = 0; i < 10; i++) {
            for(int n = 0; n < 10; n++) {
                computerBoard[i][n] = 0;
            }
        }
    }

//    ****** metoda (prawdopodobnie) NIEPOTRZEBNA
//    public void blockActionOnComputerBoard() {
//        ControlSquare controlSquare = new ControlSquare();
//        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
//        for(Node node : childrenOfControlSquares) {
//            if(node.getClass() == controlSquare.getClass()) {
//                ControlSquare button = (ControlSquare) node;
//                button.setOnAction(event -> { });
//            }
//        }
//    }


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
