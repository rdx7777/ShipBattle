package com.kodilla;

import javafx.scene.layout.GridPane;

public class Board {
    private GridPane grid;
    private int[][] playerBoard;
    private int[][] computerBoard;

    public Board(GridPane grid) {
        this.grid = grid;
    }

    public GridPane getGrid() {
        return grid;
    }

}
