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
    private int playerBoard[][] = new int[10][10];
    private int copyOfPlayerBoard[][] = new int [10][10];
    private int computerBoard[][] = new int[10][10];
    private int copyOfComputerBoard[][] = new int [10][10];
    private boolean firstMastOfShipChecker = true; // checks if player sets the first mast of the ship
    private int maxNumberOfMasts; // allows player to build a ship with max. number of masts
    private int maxNumberOfShips; // this declaration is 'must be', because the reference is used inside condition 'if'
    private List<Pair<Integer, Integer>> coordinatesForComputerShoot = new ArrayList<>();
    private boolean wasPlayerMastHit;
    private Pair<Integer, Integer> playerHitMastCoordinates;
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

    public void createBoard(GridPane grid, List<ControlSquare> controlSquaresSet) {
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
        for (Node node : childrenOfControlSquares) {
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

    public void createListOfCoordinatesToComputerShoot() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                coordinatesForComputerShoot.add(new Pair<>(i, n));
            }
        }
    }

    public int[][] getComputerBoard() {
        return computerBoard;
    }

    public void setComputerBoard(int computerBoardToSet[][]) {
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
        List<Ship> shipCollection = new ArrayList<>(Arrays.asList(ship_4_1, ship_3_1, ship_3_2, ship_2_1,
                ship_2_2, ship_2_3, ship_1_1, ship_1_2, ship_1_3, ship_1_4));
        shipsContainer.addShipsToContainer(shipCollection);

    }

    public void createComputerShipObjectsAndAddingToContainer() {

        // creating computer's objects used in the game
        Ship computerShip_4_1 = new Ship("Ship 4-masts (computer) (1)", new ArrayList<>());
        Ship computerShip_3_1 = new Ship("Ship 3-masts (computer) (1)", new ArrayList<>());
        Ship computerShip_3_2 = new Ship("Ship 3-masts (computer) (2)", new ArrayList<>());
        Ship computerShip_2_1 = new Ship("Ship 2-masts (computer) (1)", new ArrayList<>());
        Ship computerShip_2_2 = new Ship("Ship 2-masts (computer) (2)", new ArrayList<>());
        Ship computerShip_2_3 = new Ship("Ship 2-masts (computer) (3)", new ArrayList<>());
        Ship computerShip_1_1 = new Ship("Ship 1-masts (computer) (1)", new ArrayList<>());
        Ship computerShip_1_2 = new Ship("Ship 1-masts (computer) (2)", new ArrayList<>());
        Ship computerShip_1_3 = new Ship("Ship 1-masts (computer) (3)", new ArrayList<>());
        Ship computerShip_1_4 = new Ship("Ship 1-masts (computer) (4)", new ArrayList<>());

        // preparing and adding set of ships to ships container
        List<Ship> shipCollection = new ArrayList<>(Arrays.asList(computerShip_4_1, computerShip_3_1,
                computerShip_3_2, computerShip_2_1, computerShip_2_2, computerShip_2_3,
                computerShip_1_1, computerShip_1_2, computerShip_1_3, computerShip_1_4));
        shipsContainer.addComputerShipsToContainer(shipCollection);

    }

    public void setShipMastOnControlSquareField() {
        Map<String, Ship> map = shipsContainer.getSetOfShips();
        Deque<Ship> theShipsForCheck = new ArrayDeque<>(); // create queue for check if ship exists
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            theShipsForCheck.offer(entry.getValue());
        }
        Deque<Ship> theShipsForSaveData = new ArrayDeque<>(); // create queue for save ship built
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            theShipsForSaveData.offer(entry.getValue());
        }
        List<Pair<Integer, Integer>> mastsCoordinates = new ArrayList<>();
        startButton = findButton("Start");
        maxNumberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
        if (maxNumberOfMasts == 4) {
            maxNumberOfShips = 10; // sets max number of ships
        }
        ControlSquare controlSquare = new ControlSquare(new Pair<>(100, 100));
        ObservableList<Node> childrenOfControlSquares = gridPlayer.getChildren();
        for (Node node : childrenOfControlSquares) { // set actions for every ControlSquare object in the grid
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = button.getControlSquareCoordinates().getKey();
                    int row = button.getControlSquareCoordinates().getValue();
                    if (maxNumberOfMasts > 0) {
                        if (checkNeighbourDiagonally(playerBoard, column, row)) { // check player move is legal
                            if (firstMastOfShipChecker) {// adding first mast of ship
                                extractedAddingShipMast(column, row, mastsCoordinates);
                                firstMastOfShipChecker = false;
                                removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                if (maxNumberOfMasts == 0) {
                                    List<Pair<Integer, Integer>> mastsCoordinatesToSave = new ArrayList<>(mastsCoordinates);
                                    changeShipStatusToExists(theShipsForSaveData, mastsCoordinatesToSave);
                                    protectShipPosition(playerBoard, mastsCoordinatesToSave);
                                    maxNumberOfShips--;
                                    mastsCoordinates.clear();
                                    firstMastOfShipChecker = true;
                                    maxNumberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
                                    if (maxNumberOfMasts == 0 && maxNumberOfShips == 0) {
                                        startButton.setDisable(false);
                                    }
                                }
                            } else {
                                if (checkBuildingOnlyOneShipAtTime(playerBoard, column, row)) {
                                    // adding next mast of ship
                                    extractedAddingShipMast(column, row, mastsCoordinates);
                                    removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                    if (maxNumberOfMasts == 0) {
                                        List<Pair<Integer, Integer>> mastsCoordinatesToSave = new ArrayList<>(mastsCoordinates);
                                        changeShipStatusToExists(theShipsForSaveData, mastsCoordinatesToSave);
                                        protectShipPosition(playerBoard, mastsCoordinatesToSave);
                                        maxNumberOfShips--;
                                        mastsCoordinates.clear();
                                        firstMastOfShipChecker = true;
                                        maxNumberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
                                        if (maxNumberOfMasts == 0 && maxNumberOfShips == 0) {
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

    public void extractedAddingShipMast(int column, int row, List<Pair<Integer, Integer>> coordinates) {
        ShipMast shipMast = new ShipMast(new Pair<>(column, row));
        gridPlayer.add(shipMast, column, row);
        shipsContainer.addShipMastToContainer(shipMast);
        playerBoard[column][row] = 3; // temporary until accepting whole ship; then 1
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

    public int checkShipExistsInShipsContainer(Deque<Ship> deque) { // method returns max. number of masts allowed
        if (deque.size() > 0) {
            return Character.getNumericValue(deque.poll().getName().charAt(5));
        }
        return 0;
    }

    public boolean checkNeighbourDiagonally(int board[][], int column, int row) {

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

    public boolean checkBuildingOnlyOneShipAtTime(int board[][], int column, int row) {

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

    public void changeShipStatusToExists(Deque<Ship> deque, List<Pair<Integer, Integer>> coordinates) {
        if (deque.size() > 0) {
            Ship ship = deque.poll();
            ship.setCoordinates(coordinates);
            ship.setStatus(1);
        }
    }

    public void protectShipPosition(int board[][], List<Pair<Integer, Integer>> coordinates) {

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

    private void extractedMethod1(int board[][], int column, int row) {
        if (board[column][row-1] != 1) {board[column][row-1] = 2;}
        if (board[column][row+1] != 1) {board[column][row+1] = 2;}
    }

    private void extractedMethod2(int board[][], int column, int row) {
        if (board[column-1][row] != 1) {board[column-1][row] = 2;}
        board[column-1][row-1] = 2;
    }

    private void extractedMethod3(int board[][], int column, int row) {
        if (board[column-1][row] != 1) {board[column-1][row] = 2;}
        if (board[column+1][row] != 1) {board[column+1][row] = 2;}
    }

    public void removeShipMast(List<Pair<Integer, Integer>> coordinates) {
        List<ShipMast> shipMastsList = shipsContainer.getSetOfShipMasts();
        ShipMast shipMast = new ShipMast(new Pair<>(100, 100));
        ObservableList<Node> childrenOfShipMasts = gridPlayer.getChildren();
        for (Node node : childrenOfShipMasts) {
            if (node.getClass() == shipMast.getClass()) { // if (node.getClass().isInstance(shipMast)) {
                ShipMast button = (ShipMast) node;
                button.setOnAction(event -> {
                    int column = button.getVisibleShipMastCoordinates().getKey();
                    int row = button.getVisibleShipMastCoordinates().getValue();
                    if (playerBoard[column][row] == 3) {
                        if (checkRemoveShipMastIsAllowed(column, row)) { // check if not removing mast inside the ship
                            ShipMast shipMastToRemove = identifyShipMast(column, row, shipMastsList);
                            gridPlayer.getChildren().remove(shipMastToRemove); // gridPlayer.getChildren().remove(button);
                            shipsContainer.removeShipMastFromContainer(shipMastToRemove);
                            playerBoard[column][row] = 0;
                            coordinates.remove(new Pair<>(column, row));
                            maxNumberOfMasts++;
                        }
                        if (maxNumberOfShips == 10 && maxNumberOfMasts == 4
                                || maxNumberOfShips == 9 && maxNumberOfMasts == 3
                                || maxNumberOfShips == 8 && maxNumberOfMasts == 3
                                || maxNumberOfShips == 7 && maxNumberOfMasts == 2
                                || maxNumberOfShips == 6 && maxNumberOfMasts == 2
                                || maxNumberOfShips == 5 && maxNumberOfMasts == 2) {firstMastOfShipChecker = true;}
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
        List<ShipMast> playerShipMastsList = shipsContainer.getSetOfShipMasts();
        List<ShipMast> computerShipMastsList = shipsContainer.getSetOfComputerShipMasts();
        List<Missed> playerMissedList = shipsContainer.getSetOfMissed();
        List<Missed> computerMissedList = shipsContainer.getSetOfComputerMissed();
        Map<String, Ship> playerShipsMap = shipsContainer.getSetOfShips();
        Map<String, Ship> computerShipsMap = shipsContainer.getSetOfComputerShips();
        createListOfCoordinatesToComputerShoot();
        Random random = new Random();
        ControlSquare controlSquare = new ControlSquare(new Pair<>(100, 100));
        ObservableList<Node> childrenOfControlSquares = gridComputer.getChildren();
        // sets actions for every ControlSquare object in the grid on computer board
        for (Node node : childrenOfControlSquares) {
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = button.getControlSquareCoordinates().getKey();
                    int row = button.getControlSquareCoordinates().getValue();
                    if (computerBoard[column][row] == 1) {
                        hit(column, row, computerShipMastsList, computerMissedList, computerShipsMap);
                        if (!areAllShipsSunk(computerShipsMap)) {
                            computerMove(random, playerShipMastsList, playerMissedList, playerShipsMap);
                        } else {
                            // THE END OF THE GAME - player won
                            blockActionOnBoard(gridComputer, true);
                            scores.playerWon();
                            printResult("Player");
                            newGameButton.setDisable(false);
                        }
                    } else {
                        missed(column, row);
                        computerMove(random, playerShipMastsList, playerMissedList, playerShipsMap);
                    }
                });
            }
        }
    }

    public void hit(int column, int row, List<ShipMast> computerShipMastsList,
                    List<Missed> computerMissedList, Map<String, Ship> computerShipsMap) {
        copyOfComputerBoard[column][row] = 1;
        Hit hit = new Hit(new Pair<>(column, row));
        gridComputer.add(hit, column, row);
        shipsContainer.getSetOfComputerHits().add(hit);
        ShipMast shipMast = identifyShipMast(column, row, computerShipMastsList);
        shipMast.setShipMastHit(true);
        Ship ship = identifyShip(column, row, computerShipsMap);
        if (isShipSunk(ship, computerShipMastsList)) {
            protectShipPosition(copyOfComputerBoard, ship.getMastsCoordinates());
            showShipProtectedArea(gridComputer, copyOfComputerBoard, computerMissedList);
        }
    }

    public void missed(int column, int row) {
        Missed missed = new Missed(new Pair<>(column, row));
        gridComputer.add(missed, column, row);
        shipsContainer.getSetOfComputerMissed().add(missed);
    }

    public void computerMove(Random random, List<ShipMast> playerShipMastsList,
                             List<Missed> playerMissedList, Map<String, Ship> playerShipsMap) {

        int column;
        int row;

        if (wasPlayerMastHit) {
            Pair<Integer, Integer> probableCoordinates = shootAroundHitMast(random);
            Pair<Integer, Integer> prohibitedCoordinates = new Pair<>(100, 100);
            if (!probableCoordinates.equals(prohibitedCoordinates)) {
                column = probableCoordinates.getKey();
                row = probableCoordinates.getValue();
                coordinatesForComputerShoot.remove(probableCoordinates);
                System.out.println(probableCoordinates); // ******************************************
            } else {
                Pair<Integer, Integer> randomCoordinates = coordinatesForComputerShoot.
                        get(random.nextInt(coordinatesForComputerShoot.size()));
                column = randomCoordinates.getKey();
                row = randomCoordinates.getValue();
                coordinatesForComputerShoot.remove(randomCoordinates);
                System.out.println(randomCoordinates);
            }
        } else {
            Pair<Integer, Integer> randomCoordinates = coordinatesForComputerShoot.
                    get(random.nextInt(coordinatesForComputerShoot.size()));
            column = randomCoordinates.getKey();
            row = randomCoordinates.getValue();
            coordinatesForComputerShoot.remove(randomCoordinates);
            System.out.println(randomCoordinates);
        }

        ShipMast shipMast = identifyShipMast(column, row, playerShipMastsList);
        Ship ship = identifyShip(column, row, playerShipsMap);

        if (playerBoard[column][row] == 1) {
            copyOfPlayerBoard[column][row] = 1;
            Hit hit = new Hit(new Pair<>(column, row));
            gridPlayer.add(hit, column, row);
            shipsContainer.getSetOfHits().add(hit);
            shipMast.setShipMastHit(true);
            wasPlayerMastHit = true;
            playerHitMastCoordinates = new Pair<>(column, row);
            if (isShipSunk(ship, playerShipMastsList)) {
                protectShipPosition(copyOfPlayerBoard, ship.getMastsCoordinates());
                showShipProtectedArea(gridPlayer, copyOfPlayerBoard, playerMissedList);
                removeProtectedAreaFromListOfCoordinatesForComputerShoot();
                wasPlayerMastHit = false;
                if (areAllShipsSunk(playerShipsMap)) {
                    // THE END OF THE GAME - computer won
                    blockActionOnBoard(gridComputer, true);
                    scores.computerWon();
                    printResult("Computer");
                    newGameButton.setDisable(false);
                }
            }
        }  else {
            copyOfPlayerBoard[column][row] = 2;
            Missed missed = new Missed(new Pair<>(column, row));
            gridPlayer.add(missed, column, row);
            shipsContainer.getSetOfMissed().add(missed);
//            wasPlayerMastHit = false;
        }

    }

    public Pair<Integer, Integer> shootAroundHitMast(Random random) {

        int column = playerHitMastCoordinates.getKey();
        int row = playerHitMastCoordinates.getValue();

        Pair<Integer, Integer> probableCoordinates = new Pair<>(100, 100);
        List<Pair<Integer, Integer>> temporarySetOfProbableCoordinates = new ArrayList<>();

        if (column > 0 && column < 9 && row > 0 && row < 9) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row-1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row+1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column-1, row));
            temporarySetOfProbableCoordinates.add(new Pair<>(column+1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column == 0 && row > 0 && row < 9) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row-1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row+1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column+1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column == 9 && row > 0 && row < 9) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row-1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row+1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column-1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column > 0 && column < 9 && row == 0) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row+1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column-1, row));
            temporarySetOfProbableCoordinates.add(new Pair<>(column+1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column > 0 && column < 9 && row == 9) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row-1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column-1, row));
            temporarySetOfProbableCoordinates.add(new Pair<>(column+1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column == 0 && row == 0) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row+1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column+1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column == 9 && row == 0) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row+1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column-1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column == 0 && row == 9) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row-1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column+1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        if (column == 9 && row == 9) {
            temporarySetOfProbableCoordinates.add(new Pair<>(column, row-1));
            temporarySetOfProbableCoordinates.add(new Pair<>(column-1, row));
            probableCoordinates = extractedShootAroundHitMast(temporarySetOfProbableCoordinates, random);
        }

        return probableCoordinates;

    }

    public Pair<Integer, Integer> extractedShootAroundHitMast(
            List<Pair<Integer, Integer>> temporarySetOfProbableCoordinates, Random random) {

        Pair<Integer, Integer> probableCoordinates = new Pair<>(100, 100);
        List<Pair<Integer, Integer>> setOfProbableCoordinates = new ArrayList<>();

        for (Pair<Integer, Integer> coordinates : temporarySetOfProbableCoordinates) {
            if (coordinatesForComputerShoot.contains(coordinates)) {
                setOfProbableCoordinates.add(coordinates);
            }
        }
        if (setOfProbableCoordinates.size() > 1) {
            probableCoordinates = setOfProbableCoordinates.get(random.
                    nextInt(setOfProbableCoordinates.size()));
        } else {
            if (setOfProbableCoordinates.size() > 0) {
                probableCoordinates = setOfProbableCoordinates.get(0);
            }
        }

        return probableCoordinates;

    }


    public ShipMast identifyShipMast(int column, int row, List<ShipMast> shipMastsList) {
        ShipMast wantedShipMast = new ShipMast(new Pair<>(100, 100));
        for (ShipMast shipMast : shipMastsList) {
            if (shipMast.getVisibleShipMastCoordinates().getKey() == column
                    && shipMast.getVisibleShipMastCoordinates().getValue() == row) {
                wantedShipMast = shipMast;
            }
        }
        return wantedShipMast;
    }

    public Ship identifyShip(int column, int row, Map<String, Ship> shipsMap) {
        Pair<Integer, Integer> checkedCoordinates = new Pair<>(column, row);
        Ship wantedShip = new Ship("Wanted Ship", new ArrayList<>());
        for (Map.Entry<String, Ship> entry : shipsMap.entrySet()) {
            if (entry.getValue().getMastsCoordinates().contains(checkedCoordinates)) {
                wantedShip = entry.getValue();
            }
        }
        return wantedShip;
    }

    public boolean isShipSunk(Ship ship, List<ShipMast> shipMastsList) {

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

    public boolean areAllShipsSunk(Map<String, Ship> shipsMap) {
        boolean result = false;
        int calculatedStatus = 0;
        for (Map.Entry<String, Ship> entry : shipsMap.entrySet()) {
            calculatedStatus = calculatedStatus + entry.getValue().getStatus();
        }
        if (calculatedStatus == -10) {
            result = true;
        }
        return result;
    }

    public void showShipProtectedArea(GridPane grid, int board[][], List<Missed> missedList) {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                if (board[i][n] == 2) {
                    Missed missed = new Missed(new Pair<>(i, n));
                    if(!grid.getChildren().contains(missed)) {
                        grid.add(missed, i, n);
                    }
                    if (!missedList.contains(missed)) {
                        missedList.add(missed);
                    }
                }
            }
        }
    }

    public void removeProtectedAreaFromListOfCoordinatesForComputerShoot() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                if (copyOfPlayerBoard[i][n] == 2) {
                    Pair<Integer, Integer> coordinatesToRemove = new Pair<>(i, n);
                    if (coordinatesForComputerShoot.contains(coordinatesToRemove)) {
                        coordinatesForComputerShoot.remove(coordinatesToRemove);
                    }
                }
            }
        }
    }

    public void printResult(String whoWon) {
        userInterfaceLabel.setAlignment(Pos.CENTER);
        userInterfaceLabel.setPadding(new Insets(5, 0, 0, 5));
        userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        userInterfaceLabel.setText(whoWon + " won!");
        playerScoreLabel.setText(String.valueOf(scores.getPlayerScore()));
        computerScoreLabel.setText(String.valueOf(scores.getComputerScore()));
    }

    public void buildShipsOnComputerBoard() {

        Map<String, Ship> map = shipsContainer.getSetOfComputerShips();

        Deque<Ship> theShipsForCheck = new ArrayDeque<>();
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            theShipsForCheck.offer(entry.getValue());
        }

        Deque<Ship> theShipsForSaveData = new ArrayDeque<>();
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            theShipsForSaveData.offer(entry.getValue());
        }

        List<Pair<Integer, Integer>> prohibitedCoordinates = new ArrayList<>();

        Random random = new Random();

        while (getSumOfShipStatus(map) < 10) { // until all computer ships built
            int numberOfMasts = checkShipExistsInShipsContainer(theShipsForCheck);
            int column;
            int row;
            while (true) {
                column = random.nextInt(10);
                row = random.nextInt(10);
                Pair<Integer, Integer> coordinates = new Pair<>(column, row);
                if (!prohibitedCoordinates.contains(coordinates)) {
                    if (computerBoard[column][row] == 0) {
                        List<String> allowedDirections = isEnoughSpaceForShip(column, row, computerBoard, numberOfMasts);
                        if (!allowedDirections.isEmpty()) {
                            String direction = allowedDirections.get(random.nextInt(allowedDirections.size()));
                            buildShip(column, row, direction, numberOfMasts, theShipsForSaveData);
                            prohibitedCoordinates.clear();
                            break;
                        } else {
                            prohibitedCoordinates.add(coordinates);
                        }
                    } else {
                        prohibitedCoordinates.add(coordinates);
                    }
                }
            }
        }

    }

    public int getSumOfShipStatus(Map<String, Ship> map) {
        int status = 0;
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            status = status + entry.getValue().getStatus();
        }
        return status;
    }

    public List<String> isEnoughSpaceForShip(int column, int row, int board[][], int numberOfMasts) {

        List<String> allowedDirections = new ArrayList<>();

        if (row + 1 - numberOfMasts >= 0) {
            int sum = 0;
            for (int i = 0; i < numberOfMasts - 1; i++) {
                sum = sum + board[column][row - (i + 1)];
            }
            if (sum == 0) {
                allowedDirections.add("up");
            }
        }

        if (column - 1 + numberOfMasts <= 9) {
            int sum = 0;
            for (int i = 0; i < numberOfMasts - 1; i++) {
                sum = sum + board[column+(i+1)][row];
            }
            if (sum == 0) {
                allowedDirections.add("right");
            }
        }

        if (row - 1 + numberOfMasts <= 9) {
            int sum = 0;
            for (int i = 0; i < numberOfMasts - 1; i++) {
                sum = sum + board[column][row+(i+1)];
            }
            if (sum == 0) {
                allowedDirections.add("down");
            }
        }

        if (column + 1 - numberOfMasts >= 0) {
            int sum = 0;
            for (int i = 0; i < numberOfMasts - 1; i++) {
                sum = sum + board[column-(i+1)][row];
            }
            if (sum == 0) {
                allowedDirections.add("left");
            }
        }

        return allowedDirections;

    }

    public void buildShip(int approvedColumn, int approvedRow, String direction, int numberOfMasts,
                          Deque<Ship> deque) {

        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();

        switch (direction) {

            case "up":
                for (int i = 0; i < numberOfMasts; i++) {
                    int column = approvedColumn;
                    int row = approvedRow - i;
                    extractedBuildShip(column, row, coordinates);
                }
                List<Pair<Integer, Integer>> coordinatesToSaveUP = new ArrayList<>(coordinates);
                changeShipStatusToExists(deque, coordinatesToSaveUP);
                protectShipPosition(computerBoard, coordinatesToSaveUP);
                coordinates.clear();
                break;

            case "right":
                for (int i = 0; i < numberOfMasts; i++) {
                    int column = approvedColumn + i;
                    int row = approvedRow;
                    extractedBuildShip(column, row, coordinates);
                }
                List<Pair<Integer, Integer>> coordinatesToSaveRIGHT = new ArrayList<>(coordinates);
                changeShipStatusToExists(deque, coordinatesToSaveRIGHT);
                protectShipPosition(computerBoard, coordinatesToSaveRIGHT);
                coordinates.clear();
                break;

            case "down":
                for (int i = 0; i < numberOfMasts; i++) {
                    int column = approvedColumn;
                    int row = approvedRow + i;
                    extractedBuildShip(column, row, coordinates);
                }
                List<Pair<Integer, Integer>> coordinatesToSaveDOWN = new ArrayList<>(coordinates);
                changeShipStatusToExists(deque, coordinatesToSaveDOWN);
                protectShipPosition(computerBoard, coordinatesToSaveDOWN);
                coordinates.clear();
                break;

            case "left":
                for (int i = 0; i < numberOfMasts; i++) {
                    int column = approvedColumn - i;
                    int row = approvedRow;
                    extractedBuildShip(column, row, coordinates);
                }
                List<Pair<Integer, Integer>> coordinatesToSaveLEFT = new ArrayList<>(coordinates);
                changeShipStatusToExists(deque, coordinatesToSaveLEFT);
                protectShipPosition(computerBoard, coordinatesToSaveLEFT);
                coordinates.clear();
                break;

        }

    }

    public void extractedBuildShip(int column, int row, List<Pair<Integer, Integer>> coordinates) {

        ShipMast shipMast = new ShipMast(new Pair<>(column, row));
        shipsContainer.addComputerShipMastToContainer(shipMast);
        computerBoard[column][row] = 1;
        coordinates.add(new Pair<>(column, row));

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

        for (Missed missed : shipsContainer.getSetOfMissed()) {
            gridPlayer.getChildren().remove(missed);
        }
        shipsContainer.getSetOfMissed().clear();

        for (Missed missed : shipsContainer.getSetOfComputerMissed()) {
            gridComputer.getChildren().remove(missed);
        }
        shipsContainer.getSetOfComputerMissed().clear();

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
        coordinatesForComputerShoot.clear();
        wasPlayerMastHit = false;
        playerHitMastCoordinates = null;

    }

}
