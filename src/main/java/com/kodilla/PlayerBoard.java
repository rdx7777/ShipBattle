package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class PlayerBoard {
    private GridPane grid;
    private int[][] playerBoard = new int[10][10];

    public PlayerBoard(GridPane grid) {
        this.grid = grid;
    }

    public void createPlayerBoard() {
        for(int i = 0; i < 10; i++) {
            for(int n = 0; n < 10; n++) {
                grid.add(new ControlSquare(), i + 0, n + 4);
            }
        }
    }

    public void setEmptyPlayerBoard() {
        for(int i = 0; i < 10; i++) {
            for(int n = 0; n < 10; n++) {
                playerBoard[i][n] = 0;
            }
        }
    }

    public boolean checkNeighbourDiagonally(int column, int row) {

        boolean result = false;

        if(column > 0 && column < 9 && row > 0 && row < 9) {
            if(playerBoard[column-1][row-1] == 0 && playerBoard[column+1][row-1] == 0
                    && playerBoard[column-1][row+1] == 0 && playerBoard[column+1][row+1] == 0
                    && playerBoard[column][row-1] != 2 && playerBoard[column][row+1] != 2
                    && playerBoard[column-1][row] != 2 && playerBoard[column+1][row] != 2) { result = true; }
        }

        if(column == 0 && row > 0 && row < 9) {
            if (playerBoard[column + 1][row - 1] == 0 && playerBoard[column + 1][row + 1] == 0
                    && playerBoard[column + 1][row] != 2) { result = true; }
        }

        if(column == 9 && row > 0 && row < 9) {
            if (playerBoard[column - 1][row - 1] == 0 && playerBoard[column - 1][row + 1] == 0
                    && playerBoard[column - 1][row] != 2) { result = true; }
        }

        if(column > 0 && column < 9 && row == 0) {
            if (playerBoard[column - 1][row + 1] == 0 && playerBoard[column + 1][row + 1] == 0
                    && playerBoard[column][row+1] != 2) { result = true; }
        }

        if(column > 0 && column < 9 && row == 9) {
            if (playerBoard[column - 1][row - 1] == 0 && playerBoard[column + 1][row - 1] == 0
                    && playerBoard[column][row-1] != 2) { result = true; }
        }

        if(column == 0 && row == 0) {
            if(playerBoard[column+1][row+1] == 0 && playerBoard[column+1][row] != 2
                    && playerBoard[column][row+1] != 2) { result = true; }
        }

        if(column == 9 && row == 0) {
            if(playerBoard[column-1][row+1] == 0 && playerBoard[column-1][row] != 2
                    && playerBoard[column][row+1] != 2) { result = true; }
        }

        if(column == 0 && row == 9) {
            if(playerBoard[column+1][row-1] == 0 && playerBoard[column+1][row] != 2
                    && playerBoard[column][row-1] != 2) { result = true; }
        }

        if(column == 9 && row == 9) {
            if(playerBoard[column-1][row-1] == 0 && playerBoard[column-1][row] != 2
                    && playerBoard[column][row-1] != 2) { result = true; }
        }

        return result;

    }

    public void getPositionAndSetActionOfControlSquare() {
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
        for(Node node : childrenOfControlSquares) {
            if(node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int)((button.getLocalToParentTransform().getTx()-81)/27);
                    int row = (int)((button.getLocalToParentTransform().getTy()-150)/27);
                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY
                    if(checkNeighbourDiagonally(column, row)) { // check player move is legal
                        grid.add(new ShipMast(), column, row + 4); // adding mast
                        playerBoard[column][row] = 1;
                    }
                    setActionOfRemovingShipMast(); // setting action
                });
            }
        }
    }

    public void setActionOfRemovingShipMast() {
        ShipMast shipMast = new ShipMast();
        ObservableList<Node> childrenOfShipMasts = grid.getChildren();
        for(Node node : childrenOfShipMasts) {
            if(node.getClass() == shipMast.getClass()) { // if(node.getClass().isInstance(shipMast))
                ShipMast button = (ShipMast) node;
                button.setOnAction(event -> {
                    int column = (int)((button.getLocalToParentTransform().getTx()-81)/27);
                    int row = (int)((button.getLocalToParentTransform().getTy()-150)/27);
                    ShipBattle.example(1, column, row); // CHECK POSITION ONLY
                    grid.getChildren().remove(button);
                    playerBoard[column][row] = 0;
                });
            }
        }
    }


}
