package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.*;

public class ComputerBoard {
    private GridPane grid;
    private ShipsContainer shipsContainer;
    private PlayerBoard playerBoard;
    private int[][] computerBoard = new int[10][10];
    int[][] playerBoardOfInts;

    public ComputerBoard(GridPane grid, ShipsContainer shipsContainer, PlayerBoard playerBoard) {
        this.grid = grid;
        this.shipsContainer = shipsContainer;
        this.playerBoard = playerBoard;
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

    public void createShipsOnComputerBoard(int randomParameter) {

        if (randomParameter == 0) {
            // 4-masts ship
            computerBoard[1][1] = 1;
            computerBoard[2][1] = 1;
            computerBoard[3][1] = 1;
            computerBoard[4][1] = 1;
            // first 3-masts ship
            computerBoard[8][4] = 1;
            computerBoard[8][5] = 1;
            computerBoard[8][6] = 1;
            // second 3-masts ship
            computerBoard[2][8] = 1;
            computerBoard[3][8] = 1;
            computerBoard[4][8] = 1;
            // first 2-masts ship
            computerBoard[2][3] = 1;
            computerBoard[2][4] = 1;
            // second 2-masts ship
            computerBoard[5][5] = 1;
            computerBoard[5][6] = 1;
            // third 2-masts ship
            computerBoard[8][9] = 1;
            computerBoard[9][9] = 1;
            // four 1-masts ships
            computerBoard[8][0] = 1;
            computerBoard[8][2] = 1;
            computerBoard[1][6] = 1;
            computerBoard[6][9] = 1;
        }

        if (randomParameter == 1) {
            // 4-masts ship
            computerBoard[2][6] = 1;
            computerBoard[2][7] = 1;
            computerBoard[2][8] = 1;
            computerBoard[2][9] = 1;
            // first 3-masts ship
            computerBoard[5][1] = 1;
            computerBoard[6][1] = 1;
            computerBoard[7][1] = 1;
            // second 3-masts ship
            computerBoard[6][3] = 1;
            computerBoard[7][3] = 1;
            computerBoard[8][3] = 1;
            // first 2-masts ship
            computerBoard[1][2] = 1;
            computerBoard[1][3] = 1;
            // second 2-masts ship
            computerBoard[3][4] = 1;
            computerBoard[4][4] = 1;
            // third 2-masts ship
            computerBoard[7][9] = 1;
            computerBoard[8][9] = 1;
            // four 1-masts ships
            computerBoard[8][5] = 1;
            computerBoard[0][7] = 1;
            computerBoard[9][7] = 1;
            computerBoard[4][9] = 1;
        }

        if (randomParameter == 2) {
            // 4-masts ship
            computerBoard[4][6] = 1;
            computerBoard[5][6] = 1;
            computerBoard[6][6] = 1;
            computerBoard[7][6] = 1;
            // first 3-masts ship
            computerBoard[0][0] = 1;
            computerBoard[1][0] = 1;
            computerBoard[2][0] = 1;
            // second 3-masts ship
            computerBoard[8][1] = 1;
            computerBoard[8][2] = 1;
            computerBoard[8][3] = 1;
            // first 2-masts ship
            computerBoard[4][2] = 1;
            computerBoard[5][2] = 1;
            // second 2-masts ship
            computerBoard[3][4] = 1;
            computerBoard[4][4] = 1;
            // third 2-masts ship
            computerBoard[1][8] = 1;
            computerBoard[2][8] = 1;
            // four 1-masts ships
            computerBoard[5][0] = 1;
            computerBoard[0][5] = 1;
            computerBoard[6][8] = 1;
            computerBoard[8][8] = 1;
        }

        if (randomParameter == 3) {
            // 4-masts ship
            computerBoard[0][4] = 1;
            computerBoard[0][5] = 1;
            computerBoard[0][6] = 1;
            computerBoard[0][7] = 1;
            // first 3-masts ship
            computerBoard[1][1] = 1;
            computerBoard[2][1] = 1;
            computerBoard[3][1] = 1;
            // second 3-masts ship
            computerBoard[6][1] = 1;
            computerBoard[6][2] = 1;
            computerBoard[6][3] = 1;
            // first 2-masts ship
            computerBoard[8][0] = 1;
            computerBoard[8][1] = 1;
            // second 2-masts ship
            computerBoard[6][6] = 1;
            computerBoard[7][6] = 1;
            // third 2-masts ship
            computerBoard[6][9] = 1;
            computerBoard[7][9] = 1;
            // four 1-masts ships
            computerBoard[2][4] = 1;
            computerBoard[4][5] = 1;
            computerBoard[3][8] = 1;
            computerBoard[9][9] = 1;
        }

        if (randomParameter == 4) {
            // 4-masts ship
            computerBoard[4][3] = 1;
            computerBoard[5][3] = 1;
            computerBoard[6][3] = 1;
            computerBoard[7][3] = 1;
            // first 3-masts ship
            computerBoard[1][6] = 1;
            computerBoard[1][7] = 1;
            computerBoard[1][8] = 1;
            // second 3-masts ship
            computerBoard[3][7] = 1;
            computerBoard[4][7] = 1;
            computerBoard[5][7] = 1;
            // first 2-masts ship
            computerBoard[6][0] = 1;
            computerBoard[6][1] = 1;
            // second 2-masts ship
            computerBoard[5][9] = 1;
            computerBoard[6][9] = 1;
            // third 2-masts ship
            computerBoard[8][5] = 1;
            computerBoard[8][6] = 1;
            // four 1-masts ships
            computerBoard[1][0] = 1;
            computerBoard[3][0] = 1;
            computerBoard[8][8] = 1;
            computerBoard[9][1] = 1;
        }

        System.out.println("Computer board #" + randomParameter);
    }

    public void createShipObjectsAndShipsCoordinates(int randomParameter){

        if (randomParameter == 0) {
            ArrayList<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(1, 1));
            ship_4_1_coordinates.add(new Pair<>(2, 1));
            ship_4_1_coordinates.add(new Pair<>(3, 1));
            ship_4_1_coordinates.add(new Pair<>(4, 1));
            ArrayList<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(8, 4));
            ship_3_1_coordinates.add(new Pair<>(8, 5));
            ship_3_1_coordinates.add(new Pair<>(8, 6));
            ArrayList<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(2, 8));
            ship_3_2_coordinates.add(new Pair<>(3, 8));
            ship_3_2_coordinates.add(new Pair<>(4, 8));
            ArrayList<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(2, 3));
            ship_2_1_coordinates.add(new Pair<>(2, 4));
            ArrayList<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(5, 5));
            ship_2_2_coordinates.add(new Pair<>(5, 6));
            ArrayList<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(8, 9));
            ship_2_3_coordinates.add(new Pair<>(9, 9));
            ArrayList<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(8, 0));
            ArrayList<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(8, 2));
            ArrayList<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(1, 6));
            ArrayList<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(6, 9));

            extractedCreateShipObjects(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 1) {
            ArrayList<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(2, 6));
            ship_4_1_coordinates.add(new Pair<>(2, 7));
            ship_4_1_coordinates.add(new Pair<>(2, 8));
            ship_4_1_coordinates.add(new Pair<>(2, 9));
            ArrayList<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(5, 1));
            ship_3_1_coordinates.add(new Pair<>(6, 1));
            ship_3_1_coordinates.add(new Pair<>(7, 1));
            ArrayList<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(6, 3));
            ship_3_2_coordinates.add(new Pair<>(7, 3));
            ship_3_2_coordinates.add(new Pair<>(8, 3));
            ArrayList<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(1, 2));
            ship_2_1_coordinates.add(new Pair<>(1, 3));
            ArrayList<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(3, 4));
            ship_2_2_coordinates.add(new Pair<>(4, 4));
            ArrayList<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(7, 9));
            ship_2_3_coordinates.add(new Pair<>(8, 9));
            ArrayList<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(8, 5));
            ArrayList<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(0, 7));
            ArrayList<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(9, 7));
            ArrayList<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(4, 9));

            extractedCreateShipObjects(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 2) {
            ArrayList<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(4, 6));
            ship_4_1_coordinates.add(new Pair<>(5, 6));
            ship_4_1_coordinates.add(new Pair<>(6, 6));
            ship_4_1_coordinates.add(new Pair<>(7, 6));
            ArrayList<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(0, 0));
            ship_3_1_coordinates.add(new Pair<>(1, 0));
            ship_3_1_coordinates.add(new Pair<>(2, 0));
            ArrayList<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(8, 1));
            ship_3_2_coordinates.add(new Pair<>(8, 2));
            ship_3_2_coordinates.add(new Pair<>(8, 3));
            ArrayList<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(4, 2));
            ship_2_1_coordinates.add(new Pair<>(5, 2));
            ArrayList<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(3, 4));
            ship_2_2_coordinates.add(new Pair<>(4, 4));
            ArrayList<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(1, 8));
            ship_2_3_coordinates.add(new Pair<>(2, 8));
            ArrayList<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(5, 0));
            ArrayList<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(0, 5));
            ArrayList<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(6, 8));
            ArrayList<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(8, 8));

            extractedCreateShipObjects(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 3) {
            ArrayList<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(0, 4));
            ship_4_1_coordinates.add(new Pair<>(0, 5));
            ship_4_1_coordinates.add(new Pair<>(0, 6));
            ship_4_1_coordinates.add(new Pair<>(0, 7));
            ArrayList<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(1, 1));
            ship_3_1_coordinates.add(new Pair<>(2, 1));
            ship_3_1_coordinates.add(new Pair<>(3, 1));
            ArrayList<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(6, 1));
            ship_3_2_coordinates.add(new Pair<>(6, 2));
            ship_3_2_coordinates.add(new Pair<>(6, 3));
            ArrayList<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(8, 0));
            ship_2_1_coordinates.add(new Pair<>(8, 1));
            ArrayList<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(6, 6));
            ship_2_2_coordinates.add(new Pair<>(7, 6));
            ArrayList<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(6, 9));
            ship_2_3_coordinates.add(new Pair<>(7, 9));
            ArrayList<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(2, 4));
            ArrayList<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(4, 5));
            ArrayList<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(3, 8));
            ArrayList<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(9, 9));

            extractedCreateShipObjects(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 4) {
            ArrayList<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(4, 3));
            ship_4_1_coordinates.add(new Pair<>(5, 3));
            ship_4_1_coordinates.add(new Pair<>(6, 3));
            ship_4_1_coordinates.add(new Pair<>(7, 3));
            ArrayList<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(1, 6));
            ship_3_1_coordinates.add(new Pair<>(1, 7));
            ship_3_1_coordinates.add(new Pair<>(1, 8));
            ArrayList<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(3, 7));
            ship_3_2_coordinates.add(new Pair<>(4, 7));
            ship_3_2_coordinates.add(new Pair<>(5, 7));
            ArrayList<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(6, 0));
            ship_2_1_coordinates.add(new Pair<>(6, 1));
            ArrayList<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(5, 9));
            ship_2_2_coordinates.add(new Pair<>(6, 9));
            ArrayList<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(8, 5));
            ship_2_3_coordinates.add(new Pair<>(8, 6));
            ArrayList<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(1, 0));
            ArrayList<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(3, 0));
            ArrayList<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(8, 8));
            ArrayList<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(9, 1));

            extractedCreateShipObjects(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

    }

    public void extractedCreateShipObjects
            (ArrayList<Pair<Integer, Integer>> ship_4_1_coordinates, ArrayList<Pair<Integer, Integer>> ship_3_1_coordinates,
             ArrayList<Pair<Integer, Integer>> ship_3_2_coordinates, ArrayList<Pair<Integer, Integer>> ship_2_1_coordinates,
             ArrayList<Pair<Integer, Integer>> ship_2_2_coordinates, ArrayList<Pair<Integer, Integer>> ship_2_3_coordinates,
             ArrayList<Pair<Integer, Integer>> ship_1_1_coordinates, ArrayList<Pair<Integer, Integer>> ship_1_2_coordinates,
             ArrayList<Pair<Integer, Integer>> ship_1_3_coordinates, ArrayList<Pair<Integer, Integer>> ship_1_4_coordinates) {

        Ship computerShip_4_1 = new Ship("Computer ship 4-masts (1)", ship_4_1_coordinates);
        Ship computerShip_3_1 = new Ship("Computer ship 3-masts (1)", ship_3_1_coordinates);
        Ship computerShip_3_2 = new Ship("Computer ship 3-masts (2)", ship_3_2_coordinates);
        Ship computerShip_2_1 = new Ship("Computer ship 2-masts (1)", ship_2_1_coordinates);
        Ship computerShip_2_2 = new Ship("Computer ship 2-masts (2)", ship_2_2_coordinates);
        Ship computerShip_2_3 = new Ship("Computer ship 2-masts (3)", ship_2_3_coordinates);
        Ship computerShip_1_1 = new Ship("Computer ship 1-masts (1)", ship_1_1_coordinates);
        Ship computerShip_1_2 = new Ship("Computer ship 1-masts (2)", ship_1_2_coordinates);
        Ship computerShip_1_3 = new Ship("Computer ship 1-masts (3)", ship_1_3_coordinates);
        Ship computerShip_1_4 = new Ship("Computer ship 1-masts (4)", ship_1_4_coordinates);
        ArrayList<Ship> shipCollection = new ArrayList<>(Arrays.asList(computerShip_4_1, computerShip_3_1,
                computerShip_3_2, computerShip_2_1, computerShip_2_2, computerShip_2_3,
                computerShip_1_1, computerShip_1_2, computerShip_1_3, computerShip_1_4));

        shipsContainer.addComputerShipsToContainer(shipCollection);

    }

    public void protectAllComputerShipsPositions() {
        HashMap<Ship, Integer> map = shipsContainer.getSetOfComputerShips();
        for (Map.Entry<Ship, Integer> entry : map.entrySet()) {
            ArrayList<Pair<Integer, Integer>> shipCoordinates = entry.getKey().getMastsCoordinates();
            protectComputerShipPosition(shipCoordinates);
        }
    }

    public void protectComputerShipPosition(ArrayList<Pair<Integer, Integer>> coordinates) {
        for (Pair<Integer, Integer> pair : coordinates) {
            int column = pair.getKey();
            int row = pair.getValue();

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                extractedMethod3(column, row);
                extractedMethod1(column, row);
                computerBoard[column-1][row-1] = 2;
                computerBoard[column+1][row-1] = 2;
                computerBoard[column-1][row+1] = 2;
                computerBoard[column+1][row+1] = 2;
            }

            if (column == 0 && row > 0 && row < 9) {
                extractedMethod1(column, row);
                if (computerBoard[column+1][row] != 1) {computerBoard[column+1][row] = 2;}
                computerBoard[column+1][row-1] = 2;
                computerBoard[column+1][row+1] = 2;
            }

            if (column == 9 && row > 0 && row < 9) {
                extractedMethod1(column, row);
                extractedMethod2(column, row);
                computerBoard[column-1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 0) {
                extractedMethod3(column, row);
                if (computerBoard[column][row+1] != 1) {computerBoard[column][row+1] = 2;}
                computerBoard[column-1][row+1] = 2;
                computerBoard[column+1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 9) {
                extractedMethod3(column, row);
                if (computerBoard[column][row-1] != 1) {computerBoard[column][row-1] = 2;}
                computerBoard[column-1][row-1] = 2;
                computerBoard[column+1][row-1] = 2;
            }

            if (column == 0 && row == 0) {
                if (computerBoard[column][row+1] != 1) {computerBoard[column][row+1] = 2;}
                if (computerBoard[column+1][row] != 1) {computerBoard[column+1][row] = 2;}
                computerBoard[column+1][row+1] = 2;
            }

            if (column == 9 && row == 0) {
                if (computerBoard[column][row+1] != 1) {computerBoard[column][row+1] = 2;}
                if (computerBoard[column-1][row] != 1) {computerBoard[column-1][row] = 2;}
                computerBoard[column-1][row+1] = 2;
            }

            if (column == 0 && row == 9) {
                if (computerBoard[column][row-1] != 1) {computerBoard[column][row-1] = 2;}
                if (computerBoard[column+1][row] != 1) {computerBoard[column+1][row] = 2;}
                computerBoard[column+1][row-1] = 2;
            }

            if (column == 9 && row == 9) {
                if (computerBoard[column][row-1] != 1) {computerBoard[column][row-1] = 2;}
                extractedMethod2(column, row);
            }

        }

    }

    private void extractedMethod1(int column, int row) {
        if (computerBoard[column][row-1] != 1) {computerBoard[column][row-1] = 2;}
        if (computerBoard[column][row+1] != 1) {computerBoard[column][row+1] = 2;}
    }

    private void extractedMethod2(int column, int row) {
        if (computerBoard[column-1][row] != 1) {computerBoard[column-1][row] = 2;}
        computerBoard[column-1][row-1] = 2;
    }

    private void extractedMethod3(int column, int row) {
        if (computerBoard[column-1][row] != 1) {computerBoard[column-1][row] = 2;}
        if (computerBoard[column+1][row] != 1) {computerBoard[column+1][row] = 2;}
    }



    public void showAllShipsMastsOnComputerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                if (computerBoard[i][n] == 1) {
                    grid.add(new ShipMast(), i + 12, n + 4);
                }
            }
        }
    }

    public void shootOnComputerBoard() {
        playerBoardOfInts = playerBoard.getPlayerBoard();
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
        // set actions for every ControlSquare object in the grid on computer board
        for (Node node : childrenOfControlSquares) {
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int) ((button.getLocalToParentTransform().getTx() - 81) / 27) - 14;
                    int row = (int) ((button.getLocalToParentTransform().getTy() - 150) / 27);
                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY ***********************************
                    if (column >= 0 && column <= 9) {
                        if (computerBoard[column][row] == 1) {
                            hit(column, row);
                            computerMove();
                        } else {
                            missed(column, row);
                            computerMove();
                        }
                    }

                });
            }

        }
    }

    public void hit(int column, int row) {
        System.out.println("HIT on [" + column + "][" + row + "]!");
    }

    public void missed(int column, int row) {
        System.out.println("Missed on [" + column + "][" + row + "]...");
    }

    public void computerMove() {
        Random random = new Random();
        int column = random.nextInt(9);
        int row = random.nextInt(9);
        System.out.println("Computer shoots on [" + column + "][" + row + "]...");
        if (playerBoardOfInts[column][row] == 1) {
            System.out.println("Hit on player board!");
        }


    }


//    ****** metoda NIEPOTRZEBNA
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
