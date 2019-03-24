package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerBoard {
    private GridPane grid;
    private ShipsContainer shipsContainer;
    private int[][] playerBoard = new int[10][10];
    private boolean firstMastOfShipChecker = true; // checks if player sets the first mast of the ship
    private int maxNumberOfMasts; // allows player to build a ship with max. number of masts
    private int savedMaxNumberOfMasts; // this declaration is 'must be', because the reference is used inside condition 'if'
//    private GameButton setShipButton; //  for future use
    private GameButton startButton;

    public PlayerBoard(GridPane grid, ShipsContainer shipsContainer) {
        this.grid = grid;
        this.shipsContainer = shipsContainer;
    }

    // method for use in the future
/*
    public GameButton findSetShipButton() {
        GameButton gameButton = new GameButton(100, 50, "A");
        GameButton wantedGameButton = new GameButton(100, 50, "B");
        ObservableList<Node> childrenOfGameButtons = grid.getChildren();
        for (Node node : childrenOfGameButtons) {
            if (node.getClass() == gameButton.getClass()) {
                gameButton = (GameButton) node;
            }
            if (gameButton.getButtonName() == "Set ship") {
                wantedGameButton = gameButton;
            }
        }
        return wantedGameButton;
    }
*/

    public GameButton findStartButton() {
        GameButton gameButton = new GameButton(100, 50, "A");
        GameButton wantedGameButton = new GameButton(100, 50, "B");
        ObservableList<Node> childrenOfGameButtons = grid.getChildren();
        for (Node node : childrenOfGameButtons) {
            if (node.getClass() == gameButton.getClass()) {
                gameButton = (GameButton) node;
            }
            if (gameButton.getButtonName() == "Start") {
                wantedGameButton = gameButton;
            }
        }
        return wantedGameButton;
    }


    public void createPlayerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                grid.add(new ControlSquare(), i, n + 4);
            }
        }
    }

    public void setEmptyPlayerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                playerBoard[i][n] = 0;
            }
        }
    }

    public void protectShipPosition(ArrayList<Pair<Integer, Integer>> coordinates) {

        for (Pair<Integer, Integer> pair : coordinates) {
            int column = pair.getKey();
            int row = pair.getValue();

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                playerBoard[column-1][row-1] = 2;
                playerBoard[column+1][row-1] = 2;
                playerBoard[column-1][row+1] = 2;
                playerBoard[column+1][row+1] = 2;
            }

            if (column == 0 && row > 0 && row < 9) {
                if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                playerBoard[column+1][row-1] = 2;
                playerBoard[column+1][row+1] = 2;
            }

            if (column == 9 && row > 0 && row < 9) {
                if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
                playerBoard[column-1][row-1] = 2;
                playerBoard[column-1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 0) {
                if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                playerBoard[column-1][row+1] = 2;
                playerBoard[column+1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 9) {
                if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
                playerBoard[column-1][row-1] = 2;
                playerBoard[column+1][row-1] = 2;
            }

            if (column == 0 && row == 0) {
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                playerBoard[column+1][row+1] = 2;
            }

            if (column == 9 && row == 0) {
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
                playerBoard[column-1][row+1] = 2;
            }

            if (column == 0 && row == 9) {
                if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                playerBoard[column+1][row-1] = 2;
            }

            if (column == 9 && row == 9) {
                if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
                if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
                playerBoard[column-1][row-1] = 2;
            }

        }

    }

    public void setFirstMastOfShipChecker(boolean expression){
        firstMastOfShipChecker = expression;
    }

    public boolean checkNeighbourDiagonally(int column, int row) {

        boolean result = false;

        if (playerBoard[column][row] != 2) {

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                if (playerBoard[column-1][row-1] != 1 && playerBoard[column+1][row-1] != 1
                        && playerBoard[column-1][row+1] != 1 && playerBoard[column+1][row+1] != 1
//                    && playerBoard[column][row-1] != 1 && playerBoard[column][row+1] != 1
//                    && playerBoard[column-1][row] != 1 && playerBoard[column+1][row] != 1
                ) { result = true; }
            }

            if (column == 0 && row > 0 && row < 9) {
                if (playerBoard[column+1][row-1] != 1 && playerBoard[column+1][row+1] != 1
//                    && playerBoard[column+1][row] != 1
                ) { result = true; }
            }

            if (column == 9 && row > 0 && row < 9) {
                if (playerBoard[column-1][row-1] != 1 && playerBoard[column-1][row+1] != 1
//                    && playerBoard[column-1][row] != 1
                ) { result = true; }
            }

            if (column > 0 && column < 9 && row == 0) {
                if (playerBoard[column-1][row+1] != 1 && playerBoard[column+1][row+1] != 1
//                    && playerBoard[column][row+1] != 1
                ) { result = true; }
            }

            if (column > 0 && column < 9 && row == 9) {
                if (playerBoard[column-1][row-1] != 1 && playerBoard[column+1][row-1] != 1
//                    && playerBoard[column][row-1] != 1
                ) { result = true; }
            }

            if (column == 0 && row == 0) {
                if (playerBoard[column+1][row+1] != 1
//                    && playerBoard[column+1][row] != 1 && playerBoard[column][row+1] != 1
                ) { result = true; }
            }

            if (column == 9 && row == 0) {
                if (playerBoard[column-1][row+1] != 1
//                    && playerBoard[column-1][row] != 1 && playerBoard[column][row+1] != 1
                ) { result = true; }
            }

            if (column == 0 && row == 9) {
                if (playerBoard[column+1][row-1] != 1
//                    && playerBoard[column+1][row] != 1 && playerBoard[column][row-1] != 1
                ) { result = true; }
            }

            if (column == 9 && row == 9) {
                if (playerBoard[column-1][row-1] != 1
//                    && playerBoard[column-1][row] != 1 && playerBoard[column][row-1] != 1
                ) { result = true; }
            }

        }

        return result;

    }

    public boolean checkBuildingOnlyOneShipAtTime(int column, int row) {

        boolean result = false;

        if (column > 0 && column < 9 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 1 || playerBoard[column][row+1] == 1
                    || playerBoard[column-1][row] == 1 || playerBoard[column+1][row] == 1) { result = true; }
        }

        if (column == 0 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 1 || playerBoard[column][row+1] == 1
                    || playerBoard[column + 1][row] == 1) { result = true; }
        }

        if (column == 9 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 1 || playerBoard[column][row+1] == 1
                    || playerBoard[column - 1][row] == 1) { result = true; }
        }

        if (column > 0 && column < 9 && row == 0) {
            if (playerBoard[column][row+1] == 1
                    || playerBoard[column-1][row] == 1 || playerBoard[column+1][row] == 1) { result = true; }
        }

        if (column > 0 && column < 9 && row == 9) {
            if (playerBoard[column][row-1] == 1
                    || playerBoard[column-1][row] == 1 || playerBoard[column+1][row] == 1) { result = true; }
        }

        if (column == 0 && row == 0) {
            if (playerBoard[column][row+1] == 1 || playerBoard[column+1][row] == 1) { result = true; }
        }

        if (column == 9 && row == 0) {
            if (playerBoard[column][row+1] == 1 || playerBoard[column-1][row] == 1) { result = true; }
        }

        if (column == 0 && row == 9) {
            if (playerBoard[column][row-1] == 1 || playerBoard[column+1][row] == 1) { result = true; }
        }

        if (column == 9 && row == 9) {
            if (playerBoard[column][row-1] == 1 || playerBoard[column-1][row] == 1) { result = true; }
        }

        return result;

    }

    public int checkShipExistsInShipsContainer() { // method returns max. number of masts allowed

        HashMap<Ship, Integer> map = shipsContainer.getSetOfShips();

        if (map.get(new Ship("Ship 4-masts (1)", new ArrayList<>())) == 0) {return 4;}
        if (map.get(new Ship("Ship 3-masts (1)", new ArrayList<>())) == 0) {return 3;}
        if (map.get(new Ship("Ship 3-masts (2)", new ArrayList<>())) == 0) {return 3;}
        if (map.get(new Ship("Ship 2-masts (1)", new ArrayList<>())) == 0) {return 2;}
        if (map.get(new Ship("Ship 2-masts (2)", new ArrayList<>())) == 0) {return 2;}
        if (map.get(new Ship("Ship 2-masts (3)", new ArrayList<>())) == 0) {return 2;}
        if (map.get(new Ship("Ship 1-masts (1)", new ArrayList<>())) == 0) {return 1;}
        if (map.get(new Ship("Ship 1-masts (2)", new ArrayList<>())) == 0) {return 1;}
        if (map.get(new Ship("Ship 1-masts (3)", new ArrayList<>())) == 0) {return 1;}
        if (map.get(new Ship("Ship 1-masts (4)", new ArrayList<>())) == 0) {return 1;}

        return 0;

    }

    // method saves coordinates of current ship to the appropriate Ship object
    public void replaceValueAndSaveCoordinates(String name, ArrayList<Pair<Integer, Integer>> coordinates) {
        HashMap<Ship, Integer> map = shipsContainer.getSetOfShips();
        map.replace(new Ship(name, new ArrayList<>()), 1);
        for (Map.Entry<Ship, Integer> entry : map.entrySet()) {
            if (entry.getKey().getName() == name) {
                entry.getKey().setCoordinates(coordinates);
                System.out.println(entry.getKey().getName()); // TEMP ONLY *********************************************
            }
        }
    }

    public void changeSetOfShipsInShipsContainer(int number, ArrayList<Pair<Integer, Integer>> coordinates) {

//        HashMap<Ship, Integer> map = shipsContainer.getSetOfShips();

        if (number == 10) {
            String name = "Ship 4-masts (1)";
//            map.replace(new Ship(name, new ArrayList<>()), 1);
//            for (Map.Entry<Ship, Integer> entry : map.entrySet()) {
//                if (entry.getKey().getName() == name) {
//                    entry.getKey().setCoordinates(mastsCoordinates);
//                    System.out.println(entry.getKey().getName());
//                }
//            }
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 9) {
            String name = "Ship 3-masts (1)";
//            map.replace(new Ship("Ship 3-masts (1)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 8) {
            String name = "Ship 3-masts (2)";
//            map.replace(new Ship("Ship 3-masts (2)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 7) {
            String name = "Ship 2-masts (1)";
//            map.replace(new Ship("Ship 2-masts (1)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 6) {
            String name = "Ship 2-masts (2)";
//            map.replace(new Ship("Ship 2-masts (2)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 5) {
            String name = "Ship 2-masts (3)";
//            map.replace(new Ship("Ship 2-masts (3)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 4) {
            String name = "Ship 1-masts (1)";
//            map.replace(new Ship("Ship 1-masts (1)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 3) {
            String name = "Ship 1-masts (2)";
//            map.replace(new Ship("Ship 1-masts (2)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 2) {
            String name ="Ship 1-masts (3)";
//            map.replace(new Ship("Ship 1-masts (3)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 1) {
            String name = "Ship 1-masts (4)";
//            map.replace(new Ship("Ship 1-masts (4)", new ArrayList<>()), 1);
            replaceValueAndSaveCoordinates(name, coordinates);
        }

    }

    public void setShipMastOnControlSquareField() {
//        setShipButton = findSetShipButton(); // unnecessary button (for possible use in future)
        startButton = findStartButton();
        maxNumberOfMasts = checkShipExistsInShipsContainer();
        if (maxNumberOfMasts == 4) {
            savedMaxNumberOfMasts = 10;
        }
        System.out.println("Saved = " + savedMaxNumberOfMasts); // TEMPORARY ONLY **************************************
        ArrayList<Pair<Integer, Integer>> mastsCoordinates = new ArrayList<>();
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
        for (Node node : childrenOfControlSquares) { // set actions for every ControlSquare object in the grid
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int) ((button.getLocalToParentTransform().getTx() - 81) / 27);
                    int row = (int) ((button.getLocalToParentTransform().getTy() - 150) / 27);
                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY ***********************************
                    System.out.println("Value of player board = " + playerBoard[column][row]);
                    System.out.println(maxNumberOfMasts); // FOR CHECK ONLY ********************************************
                    if (maxNumberOfMasts > 0) {
                        if (checkNeighbourDiagonally(column, row)) { // check player move is legal
                            if (firstMastOfShipChecker) {
//                                if (!checkBuildingOnlyOneShipAtTime(column, row)) {
                                    grid.add(new ShipMast(), column, row + 4); // adding first mast of ship
                                    playerBoard[column][row] = 1;
                                    mastsCoordinates.add(new Pair<>(column, row));
                                    maxNumberOfMasts--;
                                    firstMastOfShipChecker = false;
                                    if (maxNumberOfMasts == 0) {
                                        changeSetOfShipsInShipsContainer(savedMaxNumberOfMasts, mastsCoordinates);
                                        protectShipPosition(mastsCoordinates);
                                        savedMaxNumberOfMasts--;
                                        mastsCoordinates.clear();
                                        firstMastOfShipChecker = true;
//                                    setShipButton.setDisable(false); // unnecessary button (for possible use in future)
                                        System.out.println(maxNumberOfMasts); // *******************************************
                                        maxNumberOfMasts = checkShipExistsInShipsContainer(); // ***********************
                                        if (maxNumberOfMasts == 0 && savedMaxNumberOfMasts == 0) { // ******************
                                            startButton.setDisable(false);
                                        }
                                    }
//                                }
                            } else {
                                if (checkBuildingOnlyOneShipAtTime(column, row)) {
                                    grid.add(new ShipMast(), column, row + 4); // adding next mast of ship
                                    playerBoard[column][row] = 1;
                                    mastsCoordinates.add(new Pair<>(column, row));
                                    maxNumberOfMasts--;
                                    if (maxNumberOfMasts == 0) {
                                        changeSetOfShipsInShipsContainer(savedMaxNumberOfMasts, mastsCoordinates);
                                        protectShipPosition(mastsCoordinates);
                                        savedMaxNumberOfMasts--;
                                        mastsCoordinates.clear();
                                        firstMastOfShipChecker = true;
//                                        setShipButton.setDisable(false); // unnecessary button (for possible use in future)
                                        System.out.println("Max. no of masts after saving ship: " + maxNumberOfMasts); // ***************************************
                                        System.out.println(); // *******************************************************
                                        maxNumberOfMasts = checkShipExistsInShipsContainer(); // ***********************
                                        if (maxNumberOfMasts == 0 && savedMaxNumberOfMasts == 0) { // ******************
                                            startButton.setDisable(false);
                                        }
                                    }
                                }
                            }
                        }
//                    } else { // ends building new ship, saves ship to ships container, saves all coordinates to ship object
////                        setShipButton.setDisable(true); // unnecessary button (for possible use in future)
//                        maxNumberOfMasts = checkShipExistsInShipsContainer();
//                        System.out.println(maxNumberOfMasts + ", " + savedMaxNumberOfMasts);
//                        if (maxNumberOfMasts == 0 && savedMaxNumberOfMasts == 0) {startButton.setDisable(false);}
//                        System.out.println("New max =" + maxNumberOfMasts); // TEMP ONLY *******************************
                    }


/*                  ********** for future use *********
                    removeShipMast(); // setting action when clicked on ShipMast object
*/


                });
            }
        }
    }

/*
    public boolean checkRemoveMastIsAllowed(int column, int row) {

        boolean result = true;

        if (column > 0 && column < 9 && row > 0 && row < 9) {
            if ((playerBoard[column-1][row] == 1 && playerBoard[column+1][row] == 1)
                    || (playerBoard[column][row-1] == 1 && playerBoard[column][row+1] == 1)) { result = false; }
        }

        if (column == 0 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 1 && playerBoard[column][row+1] == 1) { result = false; }
        }

        if (column == 9 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 1 && playerBoard[column][row+1] == 1) { result = false; }
        }

        if (column > 0 && column < 9 && row == 0) {
            if (playerBoard[column-1][row] == 1 && playerBoard[column+1][row] == 1) { result = false; }
        }

        if (column > 0 && column < 9 && row == 9) {
            if (playerBoard[column-1][row] == 1 && playerBoard[column+1][row] == 1) { result = false; }
        }

        return result;

    }

    public void removeShipMast() {
        ShipMast shipMast = new ShipMast();
        ObservableList<Node> childrenOfShipMasts = grid.getChildren();
        for (Node node : childrenOfShipMasts) {
            if (node.getClass() == shipMast.getClass()) { // if (node.getClass().isInstance(shipMast))
                ShipMast button = (ShipMast) node;
                button.setOnAction(event -> {
                    int column = (int)((button.getLocalToParentTransform().getTx()-81)/27);
                    int row = (int)((button.getLocalToParentTransform().getTy()-150)/27);
                    ShipBattle.example(1, column, row); // CHECK POSITION ONLY
                    if (checkRemoveMastIsAllowed(column, row)) { // check if not removing mast inside the ship
                        grid.getChildren().remove(button);
                        playerBoard[column][row] = 0;
                    }
                });
            }
        }
    }
*/


}
