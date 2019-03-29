package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private GridPane grid;
    private GridPane gridPlayer;
    private ShipsContainer shipsContainer;
    private int[][] playerBoard = new int[10][10];
    private boolean firstMastOfShipChecker = true; // checks if player sets the first mast of the ship
    private int maxNumberOfMasts; // allows player to build a ship with max. number of masts
    private int savedMaxNumberOfMasts; // this declaration is 'must be', because the reference is used inside condition 'if'
//    private GameButton setShipButton; //  for future use
    private GameButton startButton;
    ArrayList<Pair<Integer, Integer>> mastsCoordinates;

    public Player(GridPane grid, GridPane gridPlayer, ShipsContainer shipsContainer) {
        this.grid = grid;
        this.gridPlayer = gridPlayer;
        this.shipsContainer = shipsContainer;
    }

    public void createPlayerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                gridPlayer.add(new ControlSquare(), i, n);
            }
        }
    }

    public void blockActionOnPlayerBoard() {
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = gridPlayer.getChildren();
        ControlSquare button;
        for (Node node : childrenOfControlSquares) { // set actions for every ControlSquare object in the grid
            if (node.getClass() == controlSquare.getClass()) {
                button = (ControlSquare) node;
                button.setDisable(true);
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

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public void makeChangeInPlayerBoard(int column, int row, int number) {
        playerBoard[column][row] = number;
    }

    public void setShipMastOnControlSquareField() {
//        setShipButton = findSetShipButton(); // unnecessary button (for possible use in future)
        startButton = findStartButton();
        maxNumberOfMasts = checkShipExistsInShipsContainer();
        if (maxNumberOfMasts == 4) {
            savedMaxNumberOfMasts = 10; // sets max number of ships --- ZMIENIC NAZWE ZMIENNE !!!!!!!!!!!!!!!!!!!!!!!
        }
        System.out.println("Saved = " + savedMaxNumberOfMasts); // TEMPORARY ONLY **************************************
        ArrayList<Pair<Integer, Integer>> mastsCoordinates = new ArrayList<>();
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = gridPlayer.getChildren();
        for (Node node : childrenOfControlSquares) { // set actions for every ControlSquare object in the grid
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int) ((button.getLocalToParentTransform().getTx()) / 27);
                    int row = (int) ((button.getLocalToParentTransform().getTy()) / 27);
                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY ***********************************
                    System.out.println("Value of player board = " + playerBoard[column][row]);
                    System.out.println(maxNumberOfMasts); // FOR CHECK ONLY ********************************************
                    if (maxNumberOfMasts > 0) {
                        if (checkNeighbourDiagonally(column, row)) { // check player move is legal
                            if (firstMastOfShipChecker) {
                                // adding first mast of ship
                                gridPlayer.add(new ShipMast(new Pair<>(column, row), false), column, row);
                                playerBoard[column][row] = 1;
                                mastsCoordinates.add(new Pair<>(column, row));
                                maxNumberOfMasts--;
                                firstMastOfShipChecker = false;
//                                removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                if (maxNumberOfMasts == 0) {
                                    changeSetOfShipsInShipsContainer(savedMaxNumberOfMasts, mastsCoordinates);
                                    protectShipPosition(mastsCoordinates);
                                    savedMaxNumberOfMasts--;
                                    mastsCoordinates.clear();
                                    firstMastOfShipChecker = true;
//                                  setShipButton.setDisable(false); // unnecessary button (for possible use in future)
                                    System.out.println("Max. no of masts after saving ship: " +
                                            maxNumberOfMasts); // ******************************************************
                                    maxNumberOfMasts = checkShipExistsInShipsContainer();
                                    if (maxNumberOfMasts == 0 && savedMaxNumberOfMasts == 0) {
                                        startButton.setDisable(false);
                                    }
                                }
                            } else {
                                if (checkBuildingOnlyOneShipAtTime(column, row)) {
                                    // adding next mast of ship
                                    gridPlayer.add(new ShipMast(new Pair<>(column, row), false), column, row);
                                    playerBoard[column][row] = 1;
                                    mastsCoordinates.add(new Pair<>(column, row));
                                    maxNumberOfMasts--;
//                                    removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                    if (maxNumberOfMasts == 0) {
                                        changeSetOfShipsInShipsContainer(savedMaxNumberOfMasts, mastsCoordinates);
                                        protectShipPosition(mastsCoordinates);
                                        savedMaxNumberOfMasts--;
                                        mastsCoordinates.clear();
                                        firstMastOfShipChecker = true;
//                                        setShipButton.setDisable(false); // unnecessary button (for possible use in future)
                                        System.out.println("Max no of masts after saving ship: " +
                                                maxNumberOfMasts); // **************************************************
                                        maxNumberOfMasts = checkShipExistsInShipsContainer();
                                        if (maxNumberOfMasts == 0 && savedMaxNumberOfMasts == 0) {
                                            startButton.setDisable(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
//                  ********** for future use *********
//                    removeShipMast(mastsCoordinates, maxNumberOfMasts); // setting action when clicked on ShipMast object
                });
            }
        }
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

    public boolean checkNeighbourDiagonally(int column, int row) {

        boolean result = false;

        if (playerBoard[column][row] != 2) {

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                if (playerBoard[column-1][row-1] != 1 && playerBoard[column+1][row-1] != 1
                        && playerBoard[column-1][row+1] != 1 && playerBoard[column+1][row+1] != 1)
                { result = true; }
            }

            if (column == 0 && row > 0 && row < 9) {
                if (playerBoard[column+1][row-1] != 1 && playerBoard[column+1][row+1] != 1)
                { result = true; }
            }

            if (column == 9 && row > 0 && row < 9) {
                if (playerBoard[column-1][row-1] != 1 && playerBoard[column-1][row+1] != 1)
                { result = true; }
            }

            if (column > 0 && column < 9 && row == 0) {
                if (playerBoard[column-1][row+1] != 1 && playerBoard[column+1][row+1] != 1)
                { result = true; }
            }

            if (column > 0 && column < 9 && row == 9) {
                if (playerBoard[column-1][row-1] != 1 && playerBoard[column+1][row-1] != 1)
                { result = true; }
            }

            if (column == 0 && row == 0) {
                if (playerBoard[column+1][row+1] != 1)
                { result = true; }
            }

            if (column == 9 && row == 0) {
                if (playerBoard[column-1][row+1] != 1)
                { result = true; }
            }

            if (column == 0 && row == 9) {
                if (playerBoard[column+1][row-1] != 1)
                { result = true; }
            }

            if (column == 9 && row == 9) {
                if (playerBoard[column-1][row-1] != 1)
                { result = true; }
            }

        }

        return result;

    }

    public boolean checkBuildingOnlyOneShipAtTime(int column, int row) { // jeśli zmienię metodę removeShipMast,
        // to wtedy tutaj też trzeba będzie zmienić jedynki na trójki

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

    public void changeSetOfShipsInShipsContainer(int number, ArrayList<Pair<Integer, Integer>> coordinates) {

        // ZAMIENIC NA ZDEJMOWANIE Z KOLEJKI BEZ number (bo kazde zdejmowanie bedzie rownowazne odejmowaniu jedynki
        // z saved...

        if (number == 10) {
            String name = "Ship 4-masts (1)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 9) {
            String name = "Ship 3-masts (1)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 8) {
            String name = "Ship 3-masts (2)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 7) {
            String name = "Ship 2-masts (1)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 6) {
            String name = "Ship 2-masts (2)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 5) {
            String name = "Ship 2-masts (3)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 4) {
            String name = "Ship 1-masts (1)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 3) {
            String name = "Ship 1-masts (2)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 2) {
            String name ="Ship 1-masts (3)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

        if (number == 1) {
            String name = "Ship 1-masts (4)";
            replaceValueAndSaveCoordinates(name, coordinates);
        }

    }

    // method saves coordinates of current ship to the appropriate Ship object
    public void replaceValueAndSaveCoordinates(String name, ArrayList<Pair<Integer, Integer>> coordinates) {
        HashMap<Ship, Integer> map = shipsContainer.getSetOfShips();
        map.replace(new Ship(name, new ArrayList<>()), 1);
        for (Map.Entry<Ship, Integer> entry : map.entrySet()) {
            if (entry.getKey().getName().equals(name)) {
                entry.getKey().setCoordinates(coordinates);
                System.out.println(entry.getKey().getName()); // TEMP ONLY *********************************************
                System.out.println(entry.getKey().getMastsCoordinates());
            }
        }
//        map.replace(new Ship(name, new ArrayList<>()), 1);
    }
    public void protectShipPosition(ArrayList<Pair<Integer, Integer>> coordinates) {

        for (Pair<Integer, Integer> pair : coordinates) {
            int column = pair.getKey();
            int row = pair.getValue();

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                extractedMethod3(column, row);
                extractedMethod1(column, row);
                playerBoard[column-1][row-1] = 2;
                playerBoard[column+1][row-1] = 2;
                playerBoard[column-1][row+1] = 2;
                playerBoard[column+1][row+1] = 2;
            }

            if (column == 0 && row > 0 && row < 9) {
                extractedMethod1(column, row);
                if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
                playerBoard[column+1][row-1] = 2;
                playerBoard[column+1][row+1] = 2;
            }

            if (column == 9 && row > 0 && row < 9) {
                extractedMethod1(column, row);
                extractedMethod2(column, row);
                playerBoard[column-1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 0) {
                extractedMethod3(column, row);
                if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
                playerBoard[column-1][row+1] = 2;
                playerBoard[column+1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 9) {
                extractedMethod3(column, row);
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
                extractedMethod2(column, row);
            }

        }

    }

    private void extractedMethod1(int column, int row) {
        if (playerBoard[column][row-1] != 1) {playerBoard[column][row-1] = 2;}
        if (playerBoard[column][row+1] != 1) {playerBoard[column][row+1] = 2;}
    }

    private void extractedMethod2(int column, int row) {
        if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
        playerBoard[column-1][row-1] = 2;
    }

    private void extractedMethod3(int column, int row) {
        if (playerBoard[column-1][row] != 1) {playerBoard[column-1][row] = 2;}
        if (playerBoard[column+1][row] != 1) {playerBoard[column+1][row] = 2;}
    }

    public void setFirstMastOfShipChecker(boolean expression){
        firstMastOfShipChecker = expression;
    }

    public void removeShipMast(ArrayList<Pair<Integer, Integer>> coordinates) {
        ShipMast shipMast = new ShipMast(new Pair<>(100, 100), false);
        ObservableList<Node> childrenOfShipMasts = gridPlayer.getChildren();
        for (Node node : childrenOfShipMasts) {
            if (node.getClass() == shipMast.getClass()) { // if (node.getClass().isInstance(shipMast))
                ShipMast button = (ShipMast) node;
                button.setOnAction(event -> {
                    int column = (int)((button.getLocalToParentTransform().getTx())/27); // współrzędne będzie można pobrać
                    int row = (int)((button.getLocalToParentTransform().getTy())/27); // z obiektu - get...
                    ShipBattle.example(1, column, row); // CHECK POSITION ONLY
                    if (maxNumberOfMasts > 0 && coordinates.size() > 0) { // !!!!!!!! druga część
                        // !!!!!!!! pozwala rozbijać już wstawione statki - ten problem rozwiąże uwaga poniżej
                        // TUTAJ WARUNEK sprawdzający, czy wpis w tablicy == 3; jeśli jest, to można usuwać
                        if (checkRemoveMastIsAllowed(column, row)) { // check if not removing mast inside the ship
                            gridPlayer.getChildren().remove(button);
                            playerBoard[column][row] = 0;
                            System.out.println(coordinates.get(coordinates.size()-1));
                            coordinates.remove(coordinates.size()-1);
                            maxNumberOfMasts++;
                        }
                        if (maxNumberOfMasts > 1) {firstMastOfShipChecker = true;} // czy przypadkiem nie FALSE ???????
                        // ewentualnie if == 1; lub saved... > 0 i < 5
                    }
                });
            }
        }
    }

    public boolean checkRemoveMastIsAllowed(int column, int row) { // wtedy tutaj jedynki trzeba będzie zamienić na trójki

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

}
