package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.*;

public class Player {
    private GridPane grid;
    private GridPane gridPlayer;
    private GridPane gridComputer;
    private ShipsContainer shipsContainer;
    private int[][] playerBoard = new int[10][10];
    private int[][] copyOfPlayerBoard = new int [10][10];
    private int[][] computerBoard = new int[10][10];
    private int[][] copyOfComputerBoard = new int [10][10];
    private boolean firstMastOfShipChecker = true; // checks if player sets the first mast of the ship
    private int maxNumberOfMasts; // allows player to build a ship with max. number of masts
    private int maxNumberOfShips; // this declaration is 'must be', because the reference is used inside condition 'if'
    private GameButton startButton;

    public Player(GridPane grid, GridPane gridPlayer, GridPane gridComputer, ShipsContainer shipsContainer) {
        this.grid = grid;
        this.gridPlayer = gridPlayer;
        this.gridComputer = gridComputer;
        this.shipsContainer = shipsContainer;
    }

    public void createBoard(GridPane grid) {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                grid.add(new ControlSquare(), i, n);
            }
        }
    }

    public void blockActionOnBoard(GridPane grid) {
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
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

    public void setEmptyComputerBoard() {
        for(int i = 0; i < 10; i++) {
            for(int n = 0; n < 10; n++) {
                computerBoard[i][n] = 0;
            }
        }
    }

    // ************************ do usuniecia !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ***************************************
    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public int[][] getComputerBoard() {
        return computerBoard;
    }

    public void setComputerBoard(int[][] computerBoardToSet) {
        computerBoard = computerBoardToSet;
    }

    // ****************** NA RAZIE ZOSTAJE, DO MOMENTU NAPISANIA METODY, KTORA ZBUDUJE STATKI KOMPUTERA ****************
    // ****************** WTEDY ZMIANA, ZEBY MOZNA JEJ BYLO UZYC DLA OBYDWOCH GRACZY ***********************************
    public void createShipObjectsAndAddingToContainer(ShipsContainer shipsContainer) {

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
        startButton = findStartButton();
        maxNumberOfMasts = checkShipExistsInShipsContainer();
        if (maxNumberOfMasts == 4) {
            maxNumberOfShips = 10; // sets max number of ships
        }
        System.out.println("Saved = " + maxNumberOfShips); // TEMPORARY ONLY **************************************
        HashMap<String, Ship> map = shipsContainer.getSetOfShips();
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
                    System.out.println("Value of player board = " + playerBoard[column][row]); // ***** TEST ONLY ******
                    System.out.println(maxNumberOfMasts); // FOR CHECK ONLY ********************************************
                    if (maxNumberOfMasts > 0) {
                        if (checkNeighbourDiagonally(playerBoard, column, row)) { // check player move is legal
                            if (firstMastOfShipChecker) {// adding first mast of ship
                                extractedAddingShipMast(gridPlayer, playerBoard, column, row, mastsCoordinates);
//                                ShipMast shipMast = new ShipMast(new Pair<>(column, row));
//                                gridPlayer.add(shipMast, column, row);
//                                shipsContainer.addShipMastToContainer(shipMast);
//                                playerBoard[column][row] = 3; // temporary until accepting whole ship; then 1
//                                mastsCoordinates.add(new Pair<>(column, row));
//                                maxNumberOfMasts--;
                                firstMastOfShipChecker = false;
                                removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                if (maxNumberOfMasts == 0) {
                                    ArrayList<Pair<Integer, Integer>> mastsCoordinatesToSave = mastsCoordinates;
                                    System.out.println("maxNumberOfShips before saving changes to ship = " + maxNumberOfShips); // TEMP
                                    changeShipStatusToExists(maxNumberOfShips, mastsCoordinatesToSave, map);
                                    protectShipPosition(playerBoard, mastsCoordinates);
                                    maxNumberOfShips--;
//                                    mastsCoordinates.clear();
                                    firstMastOfShipChecker = true;
                                    System.out.println("Max. no of masts after saving ship: " +
                                            maxNumberOfMasts); // ******************************************************
                                    maxNumberOfMasts = checkShipExistsInShipsContainer();
                                    if (maxNumberOfMasts == 0 && maxNumberOfShips == 0) {
//                                        // **************************** TEMPORARY ONLY *********************************
//                                        ArrayList<ShipMast> tempListOfShipMasts = shipsContainer.getSetOfShipMasts();
//                                        for (ShipMast shipMastToPrint : tempListOfShipMasts) {
//                                            System.out.println(shipMastToPrint.getVisibleShipMastCoordinates());
//                                        }
//                                        // *****************************************************************************

                                        for(Map.Entry<String, Ship> entry : map.entrySet()) {
                                            System.out.println(entry.getValue().getName() + ", "
                                                    + entry.getValue().getMastsCoordinates()
                                                    + entry.getValue().getStatus());
                                        }

                                        startButton.setDisable(false);
                                    }
                                }
                            } else {
                                if (checkBuildingOnlyOneShipAtTime(playerBoard, column, row)) {
                                    // adding next mast of ship
                                    extractedAddingShipMast(gridPlayer, playerBoard, column, row, mastsCoordinates);
                                    removeShipMast(mastsCoordinates); // setting action when clicked on ShipMast object
                                    if (maxNumberOfMasts == 0) {
                                        ArrayList<Pair<Integer, Integer>> mastsCoordinatesToSave = mastsCoordinates;
                                        System.out.println("maxNumberOfShips before saving changes to ship = " + maxNumberOfShips); // TEMP
                                        changeShipStatusToExists(maxNumberOfShips, mastsCoordinatesToSave, map);
                                        protectShipPosition(playerBoard, mastsCoordinates);
                                        maxNumberOfShips--;
//                                        mastsCoordinates.clear();
                                        firstMastOfShipChecker = true;
                                        System.out.println("Max no of masts after saving ship: " +
                                                maxNumberOfMasts); // **************************************************
                                        maxNumberOfMasts = checkShipExistsInShipsContainer();
                                        if (maxNumberOfMasts == 0 && maxNumberOfShips == 0) {
//                                            // ********************* TEMPORARY ONLY FOR TEST *************************
//                                            ArrayList<ShipMast> tempListOfShipMasts = shipsContainer.getSetOfShipMasts();
//                                            for (ShipMast shipMastToPrint : tempListOfShipMasts) {
//                                                System.out.println(shipMastToPrint.getVisibleShipMastCoordinates());
//                                            }
//                                            // ***********************************************************************

                                            for(Map.Entry<String, Ship> entry : map.entrySet()) {
                                                System.out.println(entry.getValue().getName() + ", "
                                                        + entry.getValue().getMastsCoordinates() +
                                                        entry.getValue().getStatus());
                                            }

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


    // **************** PRAWDOPODOBNIE TRZEBA BĘDZIE ZROBIĆ COŚ PODOBNEGO DLA KOMPUTERA ********************************
    // ***************************** LUB TEŻ BYĆ MOŻE JAKĄŚ KOLEJKĘ ****************************************************

    // !!!!!!!!!!!!!!!!!!! ***************** LUB --- EUREKA !!!! *******************************************************
    // ******************* DODAĆ W KLASIE Ship POLE Max number of masts I RESZTE ZROBIC JAKO KOLEJKE *******************
    // ******************* tylko że będzie to wymagało zmiany w kilku miejscach: w drugiej kolejce zapisującej *********
    // ******************* współrzędne, usunięcie Max number of ships, ??????? *****************************************

    public int checkShipExistsInShipsContainer() { // method returns max. number of masts allowed
        HashMap<String, Ship> map = shipsContainer.getSetOfShips();
        if (map.get("Ship 4-masts (1)").getStatus() == 0) {return 4;}
        if (map.get("Ship 3-masts (1)").getStatus() == 0) {return 3;}
        if (map.get("Ship 3-masts (2)").getStatus() == 0) {return 3;}
        if (map.get("Ship 2-masts (1)").getStatus() == 0) {return 2;}
        if (map.get("Ship 2-masts (2)").getStatus() == 0) {return 2;}
        if (map.get("Ship 2-masts (3)").getStatus() == 0) {return 2;}
        if (map.get("Ship 1-masts (1)").getStatus() == 0) {return 1;}
        if (map.get("Ship 1-masts (2)").getStatus() == 0) {return 1;}
        if (map.get("Ship 1-masts (3)").getStatus() == 0) {return 1;}
        if (map.get("Ship 1-masts (4)").getStatus() == 0) {return 1;}
        return 0;
    }

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

    public void changeShipStatusToExists(int number, ArrayList<Pair<Integer, Integer>> coordinates,
                                         HashMap<String, Ship> map) {

        // ZAMIENIC NA ZDEJMOWANIE Z KOLEJKI BEZ number (bo kazde zdejmowanie bedzie rownowazne odejmowaniu jedynki
        // z maxNumberOfShips...

        // **** kolejka FIFO !!!!!!!!! *********************************************************************************

        if (number == 10) {
            String name = "Ship 4-masts (1)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 9) {
            String name = "Ship 3-masts (1)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 8) {
            String name = "Ship 3-masts (2)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 7) {
            String name = "Ship 2-masts (1)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 6) {
            String name = "Ship 2-masts (2)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 5) {
            String name = "Ship 2-masts (3)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 4) {
            String name = "Ship 1-masts (1)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 3) {
            String name = "Ship 1-masts (2)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 2) {
            String name ="Ship 1-masts (3)";
            saveCoordinates(name, coordinates, map);
        }

        if (number == 1) {
            String name = "Ship 1-masts (4)";
            saveCoordinates(name, coordinates, map);
        }

    }

    // method saves coordinates of current ship to the appropriate Ship object
    public void saveCoordinates(String name, ArrayList<Pair<Integer, Integer>> coordinates,
                                HashMap<String, Ship> map) {
//        map.get(name).setStatus(1);
//        map.get(name).setCoordinates(coordinates);
//        System.out.println(name); // TEMP ONLY *******************************************************
//        System.out.println(map.get(name).getMastsCoordinates()); // TEMP ONLY *******************************
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            if (entry.getKey().equals(name)) {
                entry.getValue().setStatus(1);
                entry.getValue().setCoordinates(coordinates);
                System.out.println(entry.getKey()); // TEMP ONLY *******************************************************
                System.out.println(entry.getValue().getName() + ", "
                        + entry.getValue().getMastsCoordinates()); // TEMP ONLY *******************************
            }
        }
    }

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

    private void extractedMethodArea1(int column, int row, GridPane grid, int[][] board) {
        if (board[column][row-1] != 1) {grid.add(new Missed(), column, row-1);}
        if (board[column][row+1] != 1) {grid.add(new Missed(), column, row+1);}
    }

    private void extractedMethodArea2(int column, int row, GridPane grid, int[][] board) {
        if (board[column-1][row] != 1) {grid.add(new Missed(), column-1, row);}
        grid.add(new Missed(), column-1, row-1);
    }

    private void extractedMethodArea3(int column, int row, GridPane grid, int[][] board) {
        if (board[column-1][row] != 1) {grid.add(new Missed(), column-1, row);}
        if (board[column+1][row] != 1) {grid.add(new Missed(), column+1, row);}
    }

    public void shootOnComputerBoard() {
        ArrayList<ShipMast> playerShipMastsList = shipsContainer.getSetOfShipMasts();
        ArrayList<ShipMast> computerShipMastsList = shipsContainer.getSetOfComputerShipMasts();
        HashMap<String, Ship> playerShipsMap = shipsContainer.getSetOfShips();
        HashMap<String, Ship> computerShipsMap = shipsContainer.getSetOfComputerShips();
        for(Map.Entry<String, Ship> entry : playerShipsMap.entrySet()) {
            System.out.println(entry.getValue().getName() + ", " + entry.getValue().getMastsCoordinates());
        }
        Random random = new Random();
        ControlSquare controlSquare = new ControlSquare();
        ObservableList<Node> childrenOfControlSquares = gridComputer.getChildren();
        // set actions for every ControlSquare object in the grid on computer board
        for (Node node : childrenOfControlSquares) {
            if (node.getClass() == controlSquare.getClass()) {
                ControlSquare button = (ControlSquare) node;
                button.setOnAction(event -> {
                    int column = (int) ((button.getLocalToParentTransform().getTx()) / 27);
                    int row = (int) ((button.getLocalToParentTransform().getTy()) / 27);
                    ShipBattle.example(0, column, row); // CHECK POSITION ONLY ***********************************
                    if (computerBoard[column][row] == 1) {
                        hit(column, row, computerShipMastsList, computerShipsMap);
                        computerMove(random, playerShipMastsList, playerShipsMap);
                    } else {
                        missed(column, row);
                        computerMove(random, playerShipMastsList, playerShipsMap);
                    }
                });
            }

        }
    }

    public void hit(int column, int row, ArrayList<ShipMast> computerShipMastsList,
                    HashMap<String, Ship> computerShipsMap) {
        System.out.println("HIT on [" + column + "][" + row + "]!"); // ***************** TEMP ONLY ********************
        copyOfComputerBoard[column][row] = 1;
        gridComputer.add(new Hit(), column, row);
        ShipMast shipMast = identifyShipMast(column, row, computerShipMastsList);
        shipMast.setShipMastHit(true);
        Ship ship = identifyShip(column, row, computerShipsMap);
        if (isShipSunk(ship, computerShipMastsList)) {
            System.out.println("Statek komputera został zatopiony ********************************************");
            showShipProtectedArea(ship, gridComputer, copyOfComputerBoard);
        }

        // to będzie wspólne dla hit() i computerMove() --- jako osobna metoda
        // if all ships sunk ---> the end, print score, save score, ask for new game
    }

    public void missed(int column, int row) {
        System.out.println("Missed on [" + column + "][" + row + "]..."); // *************** TEMP ONLY *****************
        copyOfComputerBoard[column][row] = -2; // niepotrzebna instrukcja, tabela nie jest sprawdzana pod kątem tej wartości
//        grid.add(new Hit(), column, row);
//        try {
//            wait(time);
//        } catch (InterruptedException e) {
//            System.out.println();
//        }
        gridComputer.add(new Missed(), column, row);
    }

    public void computerMove(Random random, ArrayList<ShipMast> playerShipMastsList,
                             HashMap<String, Ship> playerShipsMap) {
        // dodać warunek, żeby współrzędne nie trafiały w już trafione i zabezpieczone pola
        int column = random.nextInt(9);
        int row = random.nextInt(9);
        ShipMast shipMast = identifyShipMast(column, row, playerShipMastsList);
        Ship ship = identifyShip(column, row, playerShipsMap);
        System.out.println("Computer shoots on [" + column + "][" + row + "]..."); // ********* TEMP ONLY **************
        if (playerBoard[column][row] == 1) {
            System.out.println("Hit on player board!");
            copyOfPlayerBoard[column][row] = 1;
            gridPlayer.add(new Hit(), column, row);
            shipMast.setShipMastHit(true);
            if (isShipSunk(ship, playerShipMastsList)) {
                System.out.println("ZATOPIONY !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); // TEST ********



                showShipProtectedArea(ship, gridPlayer, copyOfPlayerBoard);
            }
        }  else {
            copyOfPlayerBoard[column][row] = 2;
        }

        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                System.out.print(copyOfPlayerBoard[i][n] + ", ");
            }
            System.out.println();
        }

        ArrayList<Pair<Integer, Integer>> tempShipMastsCoordinates = ship.getMastsCoordinates();
        for (Pair<Integer, Integer> coordinates : tempShipMastsCoordinates){
            int tempColumn = coordinates.getKey();
            int tempRow = coordinates.getValue();
            ShipMast temporaryShipMast = identifyShipMast(tempColumn, tempRow, playerShipMastsList);
            System.out.println(coordinates.getKey() + ", " + coordinates.getValue() + ", "
                    + temporaryShipMast.getIsShipMastHit());
        }

        ArrayList<ShipMast> tempListOfShipMasts = shipsContainer.getSetOfShipMasts();
        for (ShipMast tempShipMast : tempListOfShipMasts) {
            System.out.println(tempShipMast.getVisibleShipMastCoordinates() + "; " + tempShipMast.getIsShipMastHit());
        }

        System.out.println("sunk-status of ship = " + ship.getStatus());
        System.out.println(ship.getName());
        System.out.println("hit-status of ship mast = " + shipMast.getIsShipMastHit());
        System.out.println(ship.getMastsCoordinates());




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

    public Ship identifyShip(int column, int row, HashMap<String, Ship> shipsMap) {
        Pair<Integer, Integer> checkedCoordinates = new Pair<>(column, row);
        System.out.println("************************ Method identifyShip - checked column & row: " + checkedCoordinates);
        Ship wantedShip = new Ship("Wanted Ship", new ArrayList<>());
        for (Map.Entry<String, Ship> entry : shipsMap.entrySet()) {
            System.out.println("Currently checked column & row: "+ entry.getValue().getMastsCoordinates());
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

    public void showShipProtectedArea(Ship ship, GridPane grid, int[][]board) {

        ArrayList<Pair<Integer, Integer>> list = ship.getMastsCoordinates();

        for (Pair<Integer, Integer> pair : list) {
            int column = pair.getKey();
            int row = pair.getValue();

            if (column > 0 && column < 9 && row > 0 && row < 9) {
                extractedMethodArea3(column, row, grid, board);
                extractedMethodArea1(column, row, grid, board);
                grid.add(new Missed(), column-1, row-1);
                grid.add(new Missed(), column+1, row-1);
                grid.add(new Missed(), column-1, row+1);
                grid.add(new Missed(), column+1, row+1);
            }

            if (column == 0 && row > 0 && row < 9) {
                extractedMethodArea1(column, row, grid, board);
                if (board[column+1][row] != 1) {grid.add(new Missed(), column+1, row);}
                grid.add(new Missed(), column+1, row-1);
                grid.add(new Missed(), column+1, row+1);
            }

            if (column == 9 && row > 0 && row < 9) {
                extractedMethodArea1(column, row, grid, board);
                extractedMethodArea2(column, row, grid, board);
                grid.add(new Missed(), column-1, row+1);
            }

            if (column > 0 && column < 9 && row == 0) {
                extractedMethodArea3(column, row, grid, board);
                if (board[column][row+1] != 1) {grid.add(new Missed(), column, row+1);}
                grid.add(new Missed(), column-1, row+1);
                grid.add(new Missed(), column+1, row+1);
            }

            if (column > 0 && column < 9 && row == 9) {
                extractedMethodArea3(column, row, grid, board);
                if (board[column][row-1] != 1) {grid.add(new Missed(), column, row-1);}
                grid.add(new Missed(), column-1, row-1);
                grid.add(new Missed(), column+1, row-1);
            }

            if (column == 0 && row == 0) {
                if (board[column][row+1] != 1) {grid.add(new Missed(), column, row+1);}
                if (board[column+1][row] != 1) {grid.add(new Missed(), column+1, row);}
                grid.add(new Missed(), column+1, row+1);
            }

            if (column == 9 && row == 0) {
                if (board[column][row+1] != 1) {grid.add(new Missed(), column, row+1);}
                if (board[column-1][row] != 1) {grid.add(new Missed(), column-1, row);}
                grid.add(new Missed(), column-1, row+1);
            }

            if (column == 0 && row == 9) {
                if (board[column][row-1] != 1) {grid.add(new Missed(), column, row-1);}
                if (board[column+1][row] != 1) {grid.add(new Missed(), column+1, row);}
                grid.add(new Missed(), column+1, row-1);
            }

            if (column == 9 && row == 9) {
                if (board[column][row-1] != 1) {grid.add(new Missed(), column, row-1);}
                extractedMethodArea2(column, row, grid, board);
            }

        }

    }

}
