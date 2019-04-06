package com.kodilla;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

import java.util.*;

public class Player {
    private GridPane grid;
    private GridPane gridPlayer;
    private GridPane gridComputer;
    private ShipsContainer shipsContainer;
    private Scores scores;
    private int[][] playerBoard = new int[10][10];
    private int[][] copyOfPlayerBoard = new int [10][10];
    private int[][] computerBoard = new int[10][10];
    private int[][] copyOfComputerBoard = new int [10][10];
    private boolean firstMastOfShipChecker = true; // checks if player sets the first mast of the ship
    private int maxNumberOfMasts; // allows player to build a ship with max. number of masts
    private int maxNumberOfShips; // this declaration is 'must be', because the reference is used inside condition 'if'
    private GameButton startButton;
    private GameButton newGameButton;
    private GameLabel userInterfaceLabel;
    private GameLabel playerScoreLabel;
    private GameLabel computerScoreLabel;

    public Player(GridPane grid, GridPane gridPlayer, GridPane gridComputer,
                  ShipsContainer shipsContainer, Scores scores) {
        this.grid = grid;
        this.gridPlayer = gridPlayer;
        this.gridComputer = gridComputer;
        this.shipsContainer = shipsContainer;
        this.scores = scores;
    }

    public void createBoard(GridPane grid, ArrayList<ControlSquare> controlSquaresSet) {
        if (controlSquaresSet.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                for (int n = 0; n < 10; n++) {
                    ControlSquare controlSquare = new ControlSquare(new Pair<>(i, n));
                    grid.add(controlSquare, i, n);
                    controlSquaresSet.add(controlSquare);
                }
            }
        }
    }

    public void blockActionOnBoard(GridPane grid, boolean setStatus) {
        ControlSquare controlSquare = new ControlSquare(new Pair<>(100, 100));
        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
        ControlSquare button;
        for (Node node : childrenOfControlSquares) { // set actions for every ControlSquare object in the grid
            if (node.getClass() == controlSquare.getClass()) {
                button = (ControlSquare) node;
                button.setDisable(setStatus);
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

    public void setEmptyComputerBoard() {
        for(int i = 0; i < 10; i++) {
            for(int n = 0; n < 10; n++) {
                computerBoard[i][n] = 0;
            }
        }
    }

    public int[][] getComputerBoard() {
        return computerBoard;
    }

    public void setComputerBoard(int[][] computerBoardToSet) {
        computerBoard = computerBoardToSet;
    }

    public void createShipObjectsAndAddingToContainer() {

        // creating player's objects used in the game
        Ship ship_4_1 = new Ship("Ship 4-masts (1)", new ArrayList<>());
        Ship ship_3_1 = new Ship("Ship 3-masts (1)", new ArrayList<>());
        Ship ship_3_2 = new Ship("Ship 3-masts (2)", new ArrayList<>());
        Ship ship_2_1 = new Ship("Ship 2-masts (1)", new ArrayList<>());
        Ship ship_2_2 = new Ship("Ship 2-masts (2)", new ArrayList<>());
        Ship ship_2_3 = new Ship("Ship 2-masts (3)", new ArrayList<>());
        Ship ship_1_1 = new Ship("Ship 1-masts (1)", new ArrayList<>());
        Ship ship_1_2 = new Ship("Ship 1-masts (2)", new ArrayList<>());
        Ship ship_1_3 = new Ship("Ship 1-masts (3)", new ArrayList<>());
        Ship ship_1_4 = new Ship("Ship 1-masts (4)", new ArrayList<>());

        // preparing and adding set of ships to ships container
        ArrayList<Ship> shipCollection = new ArrayList<>(Arrays.asList(ship_4_1, ship_3_1, ship_3_2, ship_2_1, ship_2_2, ship_2_3,
                ship_1_1, ship_1_2, ship_1_3, ship_1_4));
        shipsContainer.addShipsToContainer(shipCollection);

    }

    public void setShipMastOnControlSquareField() {
        LinkedHashMap<String, Ship> map = shipsContainer.getSetOfShips();
        ArrayDeque<Ship> theShipsForCheck = new ArrayDeque<>();
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            theShipsForCheck.offer(entry.getValue());
        }
        ArrayDeque<Ship> theShipsForSaveData = new ArrayDeque<>();
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            theShipsForSaveData.offer(entry.getValue());
        }
        ArrayList<Pair<Integer, Integer>> mastsCoordinates = new ArrayList<>();
        startButton = findButton("Start");
        maxNumberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
        if (maxNumberOfMasts == 4) {
            maxNumberOfShips = 10; // sets max number of ships
        }
        System.out.println("Saved = " + maxNumberOfShips); // TEMPORARY ONLY **************************************
        ControlSquare controlSquare = new ControlSquare(new Pair<>(100, 100));
        ObservableList<Node> childrenOfControlSquares = gridPlayer.getChildren();
        for (Node node : childrenOfControlSquares) { // set actions for every ControlSquare object in the grid
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = button.getControlSquareCoordinates().getKey();
                    int row = button.getControlSquareCoordinates().getValue();
                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY ***********************************
                    System.out.println(maxNumberOfMasts); // FOR CHECK ONLY ********************************************
                    if (maxNumberOfMasts > 0) {
                        if (checkNeighbourDiagonally(playerBoard, column, row)) { // check player move is legal
                            if (firstMastOfShipChecker) {// adding first mast of ship
                                extractedAddingShipMast(gridPlayer, playerBoard, column, row, mastsCoordinates);
                                firstMastOfShipChecker = false;
                                removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                if (maxNumberOfMasts == 0) {
                                    ArrayList<Pair<Integer, Integer>> mastsCoordinatesToSave = new ArrayList<>(mastsCoordinates);
                                    changeShipStatusToExists(theShipsForSaveData, mastsCoordinatesToSave);
                                    protectShipPosition(playerBoard, mastsCoordinatesToSave);
                                    maxNumberOfShips--;
                                    mastsCoordinates.clear();
                                    firstMastOfShipChecker = true;
                                    System.out.println("Max. no of masts after saving ship: " +
                                            maxNumberOfMasts); // ******************************************************
                                    maxNumberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
                                    if (maxNumberOfMasts == 0 && maxNumberOfShips == 0) {
//                                        for(Map.Entry<String, Ship> entry : map.entrySet()) {
//                                            System.out.println(entry.getValue().getName() + ", "
//                                                    + entry.getValue().getMastsCoordinates()
//                                                    + entry.getValue().getStatus());
//                                        }
                                        startButton.setDisable(false);
                                    }
                                }
                            } else {
                                if (checkBuildingOnlyOneShipAtTime(playerBoard, column, row)) {
                                    // adding next mast of ship
                                    extractedAddingShipMast(gridPlayer, playerBoard, column, row, mastsCoordinates);
                                    removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                    if (maxNumberOfMasts == 0) {
                                        // ######################## tak się tworzy kopię obiektu #######################
                                        ArrayList<Pair<Integer, Integer>> mastsCoordinatesToSave = new ArrayList<>(mastsCoordinates);
                                        changeShipStatusToExists(theShipsForSaveData, mastsCoordinatesToSave);
                                        protectShipPosition(playerBoard, mastsCoordinatesToSave);
                                        maxNumberOfShips--;
                                        mastsCoordinates.clear();
                                        firstMastOfShipChecker = true;
                                        maxNumberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
                                        if (maxNumberOfMasts == 0 && maxNumberOfShips == 0) {
//                                            for(Map.Entry<String, Ship> entry : map.entrySet()) {
//                                                System.out.println(entry.getValue().getName() + ", "
//                                                        + entry.getValue().getMastsCoordinates() +
//                                                        entry.getValue().getStatus());
//                                            }
                                            startButton.setDisable(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void extractedAddingShipMast(GridPane grid, int[][] board, int column, int row,
                                        ArrayList<Pair<Integer, Integer>> coordinates) {
        ShipMast shipMast = new ShipMast(new Pair<>(column, row));
        grid.add(shipMast, column, row);
        shipsContainer.addShipMastToContainer(shipMast);
        board[column][row] = 3; // temporary until accepting whole ship; then 1
        coordinates.add(new Pair<>(column, row));
        maxNumberOfMasts--;
    }

    public GameButton findButton(String buttonName) {
        GameButton gameButton = new GameButton(100, 50, "A");
        GameButton wantedGameButton = new GameButton(100, 50, "B");
        ObservableList<Node> childrenOfGameButtons = grid.getChildren();
        for (Node node : childrenOfGameButtons) {
            if (node.getClass() == gameButton.getClass()) {
                gameButton = (GameButton) node;
            }
            if (gameButton.getButtonName() == buttonName) {
                wantedGameButton = gameButton;
            }
        }
        return wantedGameButton;
    }

    public GameLabel findLabel (String labelName) {
        GameLabel gameLabel = new GameLabel(100, 50, "A", "AA");
        GameLabel wantedGameLabel = new GameLabel(100, 50, "B", "BB");
        ObservableList<Node> childrenOfGameLabels = grid.getChildren();
        for (Node node : childrenOfGameLabels) {
            if (node.getClass() == gameLabel.getClass()) {
                gameLabel = (GameLabel) node;
            }
            if (gameLabel.getName() == labelName) {
                wantedGameLabel = gameLabel;
            }
        }
        return wantedGameLabel;
    }

    public int checkShipExistsInShipsContainer(ArrayDeque<Ship> deque) { // method returns max. number of masts allowed
        if (deque.size() > 0) {
            return Character.getNumericValue(deque.poll().getName().charAt(5));
        }
        return 0;
    }

    // old version of method
//    public int checkShipExistsInShipsContainer(HashMap<String, Ship> map) { // method returns max. number of masts allowed
//        if (map.get("Ship 4-masts (1)").getStatus() == 0) {return 4;}
//        if (map.get("Ship 3-masts (1)").getStatus() == 0) {return 3;}
//        if (map.get("Ship 3-masts (2)").getStatus() == 0) {return 3;}
//        if (map.get("Ship 2-masts (1)").getStatus() == 0) {return 2;}
//        if (map.get("Ship 2-masts (2)").getStatus() == 0) {return 2;}
//        if (map.get("Ship 2-masts (3)").getStatus() == 0) {return 2;}
//        if (map.get("Ship 1-masts (1)").getStatus() == 0) {return 1;}
//        if (map.get("Ship 1-masts (2)").getStatus() == 0) {return 1;}
//        if (map.get("Ship 1-masts (3)").getStatus() == 0) {return 1;}
//        if (map.get("Ship 1-masts (4)").getStatus() == 0) {return 1;}
//        return 0;
//    }

    public boolean checkNeighbourDiagonally(int[][] board, int column, int row) {

        boolean result = false;

        if (board[column][row] != 2) {

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                if (board[column-1][row-1] != 1 && board[column+1][row-1] != 1
                        && board[column-1][row+1] != 1 && board[column+1][row+1] != 1
                        && board[column-1][row-1] != 3 && board[column+1][row-1] != 3
                        && board[column-1][row+1] != 3 && board[column+1][row+1] != 3)
                { result = true; }
            }

            if (column == 0 && row > 0 && row < 9) {
                if (board[column+1][row-1] != 1 && board[column+1][row+1] != 1
                        && board[column+1][row-1] != 3 && board[column+1][row+1] != 3)
                { result = true; }
            }

            if (column == 9 && row > 0 && row < 9) {
                if (board[column-1][row-1] != 1 && board[column-1][row+1] != 1
                        && board[column-1][row-1] != 3 && board[column-1][row+1] != 3)
                { result = true; }
            }

            if (column > 0 && column < 9 && row == 0) {
                if (board[column-1][row+1] != 1 && board[column+1][row+1] != 1
                        && board[column-1][row+1] != 3 && board[column+1][row+1] != 3)
                { result = true; }
            }

            if (column > 0 && column < 9 && row == 9) {
                if (board[column-1][row-1] != 1 && board[column+1][row-1] != 1
                        && board[column-1][row-1] != 3 && board[column+1][row-1] != 3)
                { result = true; }
            }

            if (column == 0 && row == 0) {
                if (board[column+1][row+1] != 1 && board[column+1][row+1] != 3)
                { result = true; }
            }

            if (column == 9 && row == 0) {
                if (board[column-1][row+1] != 1 && board[column-1][row+1] != 3)
                { result = true; }
            }

            if (column == 0 && row == 9) {
                if (board[column+1][row-1] != 1 && board[column+1][row-1] != 3)
                { result = true; }
            }

            if (column == 9 && row == 9) {
                if (board[column-1][row-1] != 1 && board[column-1][row-1] != 3)
                { result = true; }
            }

        }

        return result;

    }

    public boolean checkBuildingOnlyOneShipAtTime(int[][] board, int column, int row) {

        boolean result = false;

        if (column > 0 && column < 9 && row > 0 && row < 9) {
            if (board[column][row-1] == 3 || board[column][row+1] == 3
                    || board[column-1][row] == 3 || board[column+1][row] == 3) { result = true; }
        }

        if (column == 0 && row > 0 && row < 9) {
            if (board[column][row-1] == 3 || board[column][row+1] == 3
                    || board[column + 1][row] == 3) { result = true; }
        }

        if (column == 9 && row > 0 && row < 9) {
            if (board[column][row-1] == 3 || board[column][row+1] == 3
                    || board[column - 1][row] == 3) { result = true; }
        }

        if (column > 0 && column < 9 && row == 0) {
            if (board[column][row+1] == 3
                    || board[column-1][row] == 3 || board[column+1][row] == 3) { result = true; }
        }

        if (column > 0 && column < 9 && row == 9) {
            if (board[column][row-1] == 3
                    || board[column-1][row] == 3 || board[column+1][row] == 3) { result = true; }
        }

        if (column == 0 && row == 0) {
            if (board[column][row+1] == 3 || board[column+1][row] == 3) { result = true; }
        }

        if (column == 9 && row == 0) {
            if (board[column][row+1] == 3 || board[column-1][row] == 3) { result = true; }
        }

        if (column == 0 && row == 9) {
            if (board[column][row-1] == 3 || board[column+1][row] == 3) { result = true; }
        }

        if (column == 9 && row == 9) {
            if (board[column][row-1] == 3 || board[column-1][row] == 3) { result = true; }
        }

        return result;

    }

    public void changeShipStatusToExists(ArrayDeque<Ship> deque, ArrayList<Pair<Integer, Integer>> coordinates) {
        if (deque.size() > 0) {
            Ship ship = deque.poll();
            ship.setCoordinates(coordinates);
            ship.setStatus(1);
            // ********************************* BELOW ONLY FOR CHECK **************************************************
            System.out.println(ship.getName() + "; " + ship.getMastsCoordinates() + "; " + ship.getStatus());
        }
    }

    // old version of 2 methods
//    public void changeShipStatusToExists(int number, ArrayList<Pair<Integer, Integer>> coordinates,
//                                         LinkedHashMap<String, Ship> map) {
//        if (number == 10) {
//            String name = "Ship 4-masts (1)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 9) {
//            String name = "Ship 3-masts (1)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 8) {
//            String name = "Ship 3-masts (2)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 7) {
//            String name = "Ship 2-masts (1)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 6) {
//            String name = "Ship 2-masts (2)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 5) {
//            String name = "Ship 2-masts (3)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 4) {
//            String name = "Ship 1-masts (1)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 3) {
//            String name = "Ship 1-masts (2)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 2) {
//            String name ="Ship 1-masts (3)";
//            saveCoordinates(name, coordinates, map);
//        }
//        if (number == 1) {
//            String name = "Ship 1-masts (4)";
//            saveCoordinates(name, coordinates, map);
//        }
//    }

/*
    // method saves coordinates of current ship to the appropriate Ship object
    public void saveCoordinates(String name, ArrayList<Pair<Integer, Integer>> coordinates,
                                LinkedHashMap<String, Ship> map) {
//        map.get(name).setStatus(1);
//        map.get(name).setCoordinates(coordinates);
//        System.out.println(name); // TEMP ONLY *******************************************************
//        System.out.println(map.get(name).getMastsCoordinates()); // TEMP ONLY *******************************
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            if (entry.getKey().equals(name)) {
                System.out.println("saving status for ship: " + entry.getValue().getName());
                entry.getValue().setStatus(1);
                System.out.println("Saving coordinates for ship: " + entry.getValue().getName());
                entry.getValue().setCoordinates(coordinates);
                System.out.println(entry.getKey()); // TEMP ONLY *******************************************************
                System.out.println(entry.getValue().getName() + ", "
                        + entry.getValue().getMastsCoordinates()); // TEMP ONLY *******************************
            }
        }
    }
*/

    public void protectShipPosition(int[][] board, ArrayList<Pair<Integer, Integer>> coordinates) {

        for (Pair<Integer, Integer> pair : coordinates) {
            int column = pair.getKey();
            int row = pair.getValue();
            board[column][row] = 1;
        }

        for (Pair<Integer, Integer> pair : coordinates) {
            int column = pair.getKey();
            int row = pair.getValue();

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                extractedMethod3(board, column, row);
                extractedMethod1(board, column, row);
                board[column-1][row-1] = 2;
                board[column+1][row-1] = 2;
                board[column-1][row+1] = 2;
                board[column+1][row+1] = 2;
            }

            if (column == 0 && row > 0 && row < 9) {
                extractedMethod1(board, column, row);
                if (board[column+1][row] != 1) {board[column+1][row] = 2;}
                board[column+1][row-1] = 2;
                board[column+1][row+1] = 2;
            }

            if (column == 9 && row > 0 && row < 9) {
                extractedMethod1(board, column, row);
                extractedMethod2(board, column, row);
                board[column-1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 0) {
                extractedMethod3(board, column, row);
                if (board[column][row+1] != 1) {board[column][row+1] = 2;}
                board[column-1][row+1] = 2;
                board[column+1][row+1] = 2;
            }

            if (column > 0 && column < 9 && row == 9) {
                extractedMethod3(board, column, row);
                if (board[column][row-1] != 1) {board[column][row-1] = 2;}
                board[column-1][row-1] = 2;
                board[column+1][row-1] = 2;
            }

            if (column == 0 && row == 0) {
                if (board[column][row+1] != 1) {board[column][row+1] = 2;}
                if (board[column+1][row] != 1) {board[column+1][row] = 2;}
                board[column+1][row+1] = 2;
            }

            if (column == 9 && row == 0) {
                if (board[column][row+1] != 1) {board[column][row+1] = 2;}
                if (board[column-1][row] != 1) {board[column-1][row] = 2;}
                board[column-1][row+1] = 2;
            }

            if (column == 0 && row == 9) {
                if (board[column][row-1] != 1) {board[column][row-1] = 2;}
                if (board[column+1][row] != 1) {board[column+1][row] = 2;}
                board[column+1][row-1] = 2;
            }

            if (column == 9 && row == 9) {
                if (board[column][row-1] != 1) {board[column][row-1] = 2;}
                extractedMethod2(board, column, row);
            }

        }

    }

    private void extractedMethod1(int[][] board, int column, int row) {
        if (board[column][row-1] != 1) {board[column][row-1] = 2;}
        if (board[column][row+1] != 1) {board[column][row+1] = 2;}
    }

    private void extractedMethod2(int[][] board, int column, int row) {
        if (board[column-1][row] != 1) {board[column-1][row] = 2;}
        board[column-1][row-1] = 2;
    }

    private void extractedMethod3(int[][] board, int column, int row) {
        if (board[column-1][row] != 1) {board[column-1][row] = 2;}
        if (board[column+1][row] != 1) {board[column+1][row] = 2;}
    }

    public void setFirstMastOfShipChecker(boolean expression){
        firstMastOfShipChecker = expression;
    }

    public void removeShipMast(ArrayList<Pair<Integer, Integer>> coordinates) {
        ArrayList<ShipMast> shipMastsList = shipsContainer.getSetOfShipMasts();
        ShipMast shipMast = new ShipMast(new Pair<>(100, 100));
        ObservableList<Node> childrenOfShipMasts = gridPlayer.getChildren();
        for (Node node : childrenOfShipMasts) {
            if (node.getClass() == shipMast.getClass()) { // if (node.getClass().isInstance(shipMast)) {
                ShipMast button = (ShipMast) node;
                button.setOnAction(event -> {
//                    int column = (int)((button.getLocalToParentTransform().getTx())/27);
//                    int row = (int)((button.getLocalToParentTransform().getTy())/27);
                    int column = button.getVisibleShipMastCoordinates().getKey();
                    int row = button.getVisibleShipMastCoordinates().getValue();
                    ShipBattle.example(1, column, row); // CHECK POSITION ONLY
                    if (playerBoard[column][row] == 3) {
                        if (checkRemoveShipMastIsAllowed(column, row)) { // check if not removing mast inside the ship
                            ShipMast shipMastToRemove = identifyShipMast(column, row, shipMastsList);
                            gridPlayer.getChildren().remove(shipMastToRemove);
//                            gridPlayer.getChildren().remove(button);
                            shipsContainer.removeShipMastFromContainer(shipMastToRemove);
                            playerBoard[column][row] = 0;
                            coordinates.remove(new Pair<>(column, row));
                            maxNumberOfMasts++;
                        }
                        if (maxNumberOfShips == 10 && maxNumberOfMasts == 4) {firstMastOfShipChecker = true;}
                        if (maxNumberOfShips == 9 && maxNumberOfMasts == 3) {firstMastOfShipChecker = true;}
                        if (maxNumberOfShips == 8 && maxNumberOfMasts == 3) {firstMastOfShipChecker = true;}
                        if (maxNumberOfShips == 7 && maxNumberOfMasts == 2) {firstMastOfShipChecker = true;}
                        if (maxNumberOfShips == 6 && maxNumberOfMasts == 2) {firstMastOfShipChecker = true;}
                        if (maxNumberOfShips == 5 && maxNumberOfMasts == 2) {firstMastOfShipChecker = true;}
                    }
                });
            }
        }
    }

    public boolean checkRemoveShipMastIsAllowed(int column, int row) {

        boolean result = true;

        if (column > 0 && column < 9 && row > 0 && row < 9) {
            if ((playerBoard[column-1][row] == 3 && playerBoard[column+1][row] == 3)
                    || (playerBoard[column][row-1] == 3 && playerBoard[column][row+1] == 3)) { result = false; }
        }

        if (column == 0 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 3 && playerBoard[column][row+1] == 3) { result = false; }
        }

        if (column == 9 && row > 0 && row < 9) {
            if (playerBoard[column][row-1] == 3 && playerBoard[column][row+1] == 3) { result = false; }
        }

        if (column > 0 && column < 9 && row == 0) {
            if (playerBoard[column-1][row] == 3 && playerBoard[column+1][row] == 3) { result = false; }
        }

        if (column > 0 && column < 9 && row == 9) {
            if (playerBoard[column-1][row] == 3 && playerBoard[column+1][row] == 3) { result = false; }
        }

        return result;

    }

    public void shootOnComputerBoard() {
        newGameButton = findButton("New game");
        userInterfaceLabel = findLabel("User Interface");
        playerScoreLabel = findLabel("Player Score");
        computerScoreLabel = findLabel("Computer Score");
        ArrayList<ShipMast> playerShipMastsList = shipsContainer.getSetOfShipMasts();
        ArrayList<ShipMast> computerShipMastsList = shipsContainer.getSetOfComputerShipMasts();
        ArrayList<Missed> playerMissedsList = shipsContainer.getSetOfMisseds();
        ArrayList<Missed> computerMissedsList = shipsContainer.getSetOfComputerMisseds();
        LinkedHashMap<String, Ship> playerShipsMap = shipsContainer.getSetOfShips();
        LinkedHashMap<String, Ship> computerShipsMap = shipsContainer.getSetOfComputerShips();
        Random random = new Random();
        ControlSquare controlSquare = new ControlSquare(new Pair<>(100, 100));
        ObservableList<Node> childrenOfControlSquares = gridComputer.getChildren();
        // sets actions for every ControlSquare object in the grid on computer board
        for (Node node : childrenOfControlSquares) {
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int) ((button.getLocalToParentTransform().getTx()) / 27);
                    int row = (int) ((button.getLocalToParentTransform().getTy()) / 27);
//                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY ***********************************
                    if (computerBoard[column][row] == 1) {
                        hit(column, row, computerShipMastsList, computerMissedsList, computerShipsMap);
                        if (!areAllShipsSunk(computerShipsMap)) {
                            computerMove(random, playerShipMastsList, playerMissedsList, playerShipsMap);
                        } else {
                            // THE END OF THE GAME - player won
                            blockActionOnBoard(gridComputer, true); // blocks computer board
                            scores.playerWon(); // saves the result of the game (player won)
                            printResult("Player"); // prints the result of the game
                            newGameButton.setDisable(false); // unblocks New Game button
//                            resetAllForNewGame();
                            // ?????????? ASK FOR NEW GAME ?????????? *******************@@@@@@@@@@@@@@@@@@@############
                        }
                    } else {
                        missed(column, row);
                        computerMove(random, playerShipMastsList, playerMissedsList, playerShipsMap);
                    }
                });
            }
        }
    }

    public void hit(int column, int row, ArrayList<ShipMast> computerShipMastsList,
                    ArrayList<Missed> computerMissedsList, LinkedHashMap<String, Ship> computerShipsMap) {
        copyOfComputerBoard[column][row] = 1;
        Hit hit = new Hit(new Pair<>(column, row));
        gridComputer.add(hit, column, row);
        shipsContainer.getSetOfComputerHits().add(hit);
        ShipMast shipMast = identifyShipMast(column, row, computerShipMastsList);
        shipMast.setShipMastHit(true);
        Ship ship = identifyShip(column, row, computerShipsMap);
        if (isShipSunk(ship, computerShipMastsList)) {
            System.out.println("Statek komputera został zatopiony **********************"); // ******** TEMP ONLY ******
            protectShipPosition(copyOfComputerBoard, ship.getMastsCoordinates());
//            showShipProtectedArea(ship, gridComputer, copyOfComputerBoard);
            showShipProtectedArea(gridComputer, copyOfComputerBoard, computerMissedsList);
        }
    }

    public void missed(int column, int row) {
        System.out.println("Missed on [" + column + "][" + row + "]..."); // *************** TEMP ONLY *****************
        Missed missed = new Missed(new Pair<>(column, row));
        gridComputer.add(missed, column, row);
        shipsContainer.getSetOfComputerMisseds().add(missed);
    }

    public void computerMove(Random random, ArrayList<ShipMast> playerShipMastsList,
                             ArrayList<Missed> playerMissedsList, LinkedHashMap<String, Ship> playerShipsMap) {

        int cheater = random.nextInt(5);
        boolean cheaterChecker = false;
        boolean checker = false;
        int column = 100;
        int row = 100;

        if (cheater != 0 || cheater !=3) { // an attempt to equalize the chances of winning computer
            while (!cheaterChecker) {
                for (ShipMast cheatedShipMast : playerShipMastsList) {
                    if (!cheatedShipMast.getIsShipMastHit()) {
                        column = cheatedShipMast.getVisibleShipMastCoordinates().getKey();
                        row = cheatedShipMast.getVisibleShipMastCoordinates().getValue();
                        cheaterChecker = true;
                    } else {
                        while (!checker) {
                            column = random.nextInt(9);
                            row = random.nextInt(9);
                            if (copyOfPlayerBoard[column][row] == 1 || copyOfPlayerBoard[column][row] == 2) {
                                checker = false;
                            } else {
                                checker = true;
                            }
                        }
                        cheaterChecker = true;
                    }
                }
            }
        } else {
            while (!checker) {
                column = random.nextInt(9);
                row = random.nextInt(9);
                if (copyOfPlayerBoard[column][row] == 1 || copyOfPlayerBoard[column][row] == 2) {
                    checker = false;
                } else {
                    checker = true;
                }
            }
        }

        ShipMast shipMast = identifyShipMast(column, row, playerShipMastsList);
        Ship ship = identifyShip(column, row, playerShipsMap);
        System.out.println("Computer shoots on [" + column + "][" + row + "]..."); // ********* TEMP ONLY **************

        if (playerBoard[column][row] == 1) {
            copyOfPlayerBoard[column][row] = 1;
            Hit hit = new Hit(new Pair<>(column, row));
            gridPlayer.add(hit, column, row);
            shipsContainer.getSetOfHits().add(hit);
            shipMast.setShipMastHit(true);
            if (isShipSunk(ship, playerShipMastsList)) {
                System.out.println("Ship's sunk !!!!!"); // ************** TEST ***************
                protectShipPosition(copyOfPlayerBoard, ship.getMastsCoordinates());
//                showShipProtectedArea(ship, gridPlayer, copyOfPlayerBoard);
                showShipProtectedArea(gridPlayer, copyOfPlayerBoard, playerMissedsList);
                if (areAllShipsSunk(playerShipsMap)) {
                    // THE END OF THE GAME - computer won
                    blockActionOnBoard(gridComputer, true); // blocks computer board
                    scores.computerWon(); // saves the result of the game (computer won)
                    printResult("Computer"); // prints the result of the game
                    newGameButton.setDisable(false); // unblocks New Game button
//                    resetAllForNewGame();
                    // ?????????? ASK FOR NEW GAME ?????????? *******************@@@@@@@@@@@@@@@@@@@############
                }
            }
        }  else {
            copyOfPlayerBoard[column][row] = 2;
            Missed missed = new Missed(new Pair<>(column, row));
            gridPlayer.add(missed, column, row);
            shipsContainer.getSetOfMisseds().add(missed);
        }

    }

    public ShipMast identifyShipMast(int column, int row, ArrayList<ShipMast> shipMastsList) {
        ShipMast wantedShipMast = new ShipMast(new Pair<>(100, 100));
        for (ShipMast shipMast : shipMastsList) {
            if (shipMast.getVisibleShipMastCoordinates().getKey() == column
                    && shipMast.getVisibleShipMastCoordinates().getValue() == row) {
                wantedShipMast = shipMast;
            }
        }
        return wantedShipMast;
    }

    public Ship identifyShip(int column, int row, LinkedHashMap<String, Ship> shipsMap) {
        Pair<Integer, Integer> checkedCoordinates = new Pair<>(column, row);
        System.out.println("***** Method identifyShip is checking column & row: " + checkedCoordinates); // ******* TEMP
        Ship wantedShip = new Ship("Wanted Ship", new ArrayList<>());
        for (Map.Entry<String, Ship> entry : shipsMap.entrySet()) {
            System.out.println("Currently checked column & row: "+ entry.getValue().getMastsCoordinates()); // **** TEMP
            if (entry.getValue().getMastsCoordinates().contains(checkedCoordinates)) {
                wantedShip = entry.getValue();
            }
        }
        return wantedShip;
    }

    public boolean isShipSunk(Ship ship, ArrayList<ShipMast> shipMastsList) {

        boolean checkIsShipSunk;
        boolean checker = true;

        for (Pair<Integer, Integer> pair : ship.getMastsCoordinates()) {
            ShipMast shipMast = identifyShipMast(pair.getKey(), pair.getValue(), shipMastsList);
            checker = checker && shipMast.getIsShipMastHit();
        }

        checkIsShipSunk = checker;

        if (checkIsShipSunk) { ship.setStatus(-1); }

        return checkIsShipSunk;

    }

    public boolean areAllShipsSunk(LinkedHashMap<String, Ship> shipsMap) {
        boolean result = false;
        int cumulatedStatus = 0;
        for (Map.Entry<String, Ship> entry : shipsMap.entrySet()) {
            cumulatedStatus = cumulatedStatus + entry.getValue().getStatus();
        }
        if (cumulatedStatus == -10) {
            result = true;
        }
        return result;
    }

    public void showShipProtectedArea(GridPane grid, int[][] board, ArrayList<Missed> missedsList) {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                if (board[i][n] == 2) {
                    Missed missed = new Missed(new Pair<>(i, n));
                    grid.add(missed, i, n);
                    if (!missedsList.contains(missed)) {
                        missedsList.add(missed);
                    }
                }
            }
        }
    }





    public void oldAndUnusedShowShipProtectedArea(Ship ship, GridPane grid, int[][]board) {

        ArrayList<Pair<Integer, Integer>> list = ship.getMastsCoordinates();

        for (Pair<Integer, Integer> pair : list) {
            int column = pair.getKey();
            int row = pair.getValue();

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                extractedMethodArea3(column, row, grid, board);
                extractedMethodArea1(column, row, grid, board);
                grid.add(new Missed(new Pair<>(column-1, row-1)), column-1, row-1);
                grid.add(new Missed(new Pair<>(column+1, row-1)), column+1, row-1);
                grid.add(new Missed(new Pair<>(column-1, row+1)), column-1, row+1);
                grid.add(new Missed(new Pair<>(column+1, row+1)), column+1, row+1);
            }

            if (column == 0 && row > 0 && row < 9) {
                extractedMethodArea1(column, row, grid, board);
                if (board[column+1][row] != 1) {grid.add(new Missed(new Pair<>(column+1, row)), column+1, row);}
                grid.add(new Missed(new Pair<>(column+1, row-1)), column+1, row-1);
                grid.add(new Missed(new Pair<>(column+1, row+1)), column+1, row+1);
            }

            if (column == 9 && row > 0 && row < 9) {
                extractedMethodArea1(column, row, grid, board);
                extractedMethodArea2(column, row, grid, board);
                grid.add(new Missed(new Pair<>(column-1, row+1)), column-1, row+1);
            }

            if (column > 0 && column < 9 && row == 0) {
                extractedMethodArea3(column, row, grid, board);
                if (board[column][row+1] != 1) {grid.add(new Missed(new Pair<>(column, row+1)), column, row+1);}
                grid.add(new Missed(new Pair<>(column-1, row+1)), column-1, row+1);
                grid.add(new Missed(new Pair<>(column+1, row+1)), column+1, row+1);
            }

            if (column > 0 && column < 9 && row == 9) {
                extractedMethodArea3(column, row, grid, board);
                if (board[column][row-1] != 1) {grid.add(new Missed(new Pair<>(column, row-1)), column, row-1);}
                grid.add(new Missed(new Pair<>(column-1, row-1)), column-1, row-1);
                grid.add(new Missed(new Pair<>(column+1, row-1)), column+1, row-1);
            }

            if (column == 0 && row == 0) {
                if (board[column][row+1] != 1) {grid.add(new Missed(new Pair<>(column, row+1)), column, row+1);}
                if (board[column+1][row] != 1) {grid.add(new Missed(new Pair<>(column+1, row)), column+1, row);}
                grid.add(new Missed(new Pair<>(column+1, row+1)), column+1, row+1);
            }

            if (column == 9 && row == 0) {
                if (board[column][row+1] != 1) {grid.add(new Missed(new Pair<>(column, row+1)), column, row+1);}
                if (board[column-1][row] != 1) {grid.add(new Missed(new Pair<>(column-1, row)), column-1, row);}
                grid.add(new Missed(new Pair<>(column-1, row+1)), column-1, row+1);
            }

            if (column == 0 && row == 9) {
                if (board[column][row-1] != 1) {grid.add(new Missed(new Pair<>(column, row-1)), column, row-1);}
                if (board[column+1][row] != 1) {grid.add(new Missed(new Pair<>(column+1, row)), column+1, row);}
                grid.add(new Missed(new Pair<>(column+1, row-1)), column+1, row-1);
            }

            if (column == 9 && row == 9) {
                if (board[column][row-1] != 1) {grid.add(new Missed(new Pair<>(column, row-1)), column, row-1);}
                extractedMethodArea2(column, row, grid, board);
            }

        }

    }

    private void extractedMethodArea1(int column, int row, GridPane grid, int[][] board) {
        if (board[column][row-1] != 1) {grid.add(new Missed(new Pair<>(column, row-1)), column, row-1);}
        if (board[column][row+1] != 1) {grid.add(new Missed(new Pair<>(column, row+1)), column, row+1);}
    }

    private void extractedMethodArea2(int column, int row, GridPane grid, int[][] board) {
        if (board[column-1][row] != 1) {grid.add(new Missed(new Pair<>(column-1, row)), column-1, row);}
        grid.add(new Missed(new Pair<>(column-1, row-1)), column-1, row-1);
    }

    private void extractedMethodArea3(int column, int row, GridPane grid, int[][] board) {
        if (board[column-1][row] != 1) {grid.add(new Missed(new Pair<>(column-1, row)), column-1, row);}
        if (board[column+1][row] != 1) {grid.add(new Missed(new Pair<>(column+1, row)), column+1, row);}
    }

    public void printResult(String whoWon) {
        userInterfaceLabel.setAlignment(Pos.CENTER);
        userInterfaceLabel.setPadding(new Insets(5, 0, 0, 5));
        userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        userInterfaceLabel.setText(whoWon + " won!");
        playerScoreLabel.setText(String.valueOf(scores.getPlayerScore()));
        computerScoreLabel.setText(String.valueOf(scores.getComputerScore()));
    }

    public void resetAllForNewGame() {

        for (ShipMast shipMast : shipsContainer.getSetOfShipMasts()) {
            gridPlayer.getChildren().remove(shipMast);
        }
        shipsContainer.getSetOfShipMasts().clear();

        for (ShipMast shipMast : shipsContainer.getSetOfComputerShipMasts()) {
            gridComputer.getChildren().remove(shipMast);
        }
        shipsContainer.getSetOfComputerShipMasts().clear();

        for (Hit hit : shipsContainer.getSetOfHits()) {
            gridPlayer.getChildren().remove(hit);
        }
        shipsContainer.getSetOfHits().clear();

        for (Hit hit : shipsContainer.getSetOfComputerHits()) {
            gridComputer.getChildren().remove(hit);
        }
        shipsContainer.getSetOfComputerHits().clear();

        for (Missed missed : shipsContainer.getSetOfMisseds()) {
            gridPlayer.getChildren().remove(missed);
        }
        shipsContainer.getSetOfMisseds().clear();

        for (Missed missed : shipsContainer.getSetOfComputerMisseds()) {
            gridComputer.getChildren().remove(missed);
        }
        shipsContainer.getSetOfComputerMisseds().clear();

        shipsContainer.getSetOfShips().clear();
        shipsContainer.getSetOfComputerShips().clear();

        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                copyOfPlayerBoard[i][n] = 0;
                copyOfComputerBoard[i][n] = 0;
            }
        }

        blockActionOnBoard(gridPlayer, false);

        firstMastOfShipChecker = true;

    }

}
