package com.kodilla;

import javafx.util.Pair;

import java.util.*;

// all this class - for remember to not do it again :)

public class Computer {
    private ShipsContainer shipsContainer;
    private Player player;
    private int computerBoard[][] = new int[10][10];

    public Computer(ShipsContainer shipsContainer, Player player) {
        this.shipsContainer = shipsContainer;
        this.player = player;
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
            player.setComputerBoard(computerBoard);
            createAndAddComputerShipMasts();
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
            player.setComputerBoard(computerBoard);
            createAndAddComputerShipMasts();
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
            player.setComputerBoard(computerBoard);
            createAndAddComputerShipMasts();
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
            player.setComputerBoard(computerBoard);
            createAndAddComputerShipMasts();
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
            player.setComputerBoard(computerBoard);
            createAndAddComputerShipMasts();
        }

    }

    public void createAndAddComputerShipMasts() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                if (computerBoard[i][n] == 1) {
                    shipsContainer.addComputerShipMastToContainer(new ShipMast(new Pair<>(i, n)));
                }
            }
        }
    }

    public void createShipObjectsAndShipsCoordinates(int randomParameter){

        if (randomParameter == 0) {
            List<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(1, 1));
            ship_4_1_coordinates.add(new Pair<>(2, 1));
            ship_4_1_coordinates.add(new Pair<>(3, 1));
            ship_4_1_coordinates.add(new Pair<>(4, 1));
            List<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(8, 4));
            ship_3_1_coordinates.add(new Pair<>(8, 5));
            ship_3_1_coordinates.add(new Pair<>(8, 6));
            List<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(2, 8));
            ship_3_2_coordinates.add(new Pair<>(3, 8));
            ship_3_2_coordinates.add(new Pair<>(4, 8));
            List<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(2, 3));
            ship_2_1_coordinates.add(new Pair<>(2, 4));
            List<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(5, 5));
            ship_2_2_coordinates.add(new Pair<>(5, 6));
            List<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(8, 9));
            ship_2_3_coordinates.add(new Pair<>(9, 9));
            List<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(8, 0));
            List<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(8, 2));
            List<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(1, 6));
            List<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(6, 9));

            extractedCreateShipObjectsAndAddingToContainer(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 1) {
            List<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(2, 6));
            ship_4_1_coordinates.add(new Pair<>(2, 7));
            ship_4_1_coordinates.add(new Pair<>(2, 8));
            ship_4_1_coordinates.add(new Pair<>(2, 9));
            List<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(5, 1));
            ship_3_1_coordinates.add(new Pair<>(6, 1));
            ship_3_1_coordinates.add(new Pair<>(7, 1));
            List<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(6, 3));
            ship_3_2_coordinates.add(new Pair<>(7, 3));
            ship_3_2_coordinates.add(new Pair<>(8, 3));
            List<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(1, 2));
            ship_2_1_coordinates.add(new Pair<>(1, 3));
            List<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(3, 4));
            ship_2_2_coordinates.add(new Pair<>(4, 4));
            List<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(7, 9));
            ship_2_3_coordinates.add(new Pair<>(8, 9));
            List<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(8, 5));
            List<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(0, 7));
            List<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(9, 7));
            List<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(4, 9));

            extractedCreateShipObjectsAndAddingToContainer(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 2) {
            List<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(4, 6));
            ship_4_1_coordinates.add(new Pair<>(5, 6));
            ship_4_1_coordinates.add(new Pair<>(6, 6));
            ship_4_1_coordinates.add(new Pair<>(7, 6));
            List<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(0, 0));
            ship_3_1_coordinates.add(new Pair<>(1, 0));
            ship_3_1_coordinates.add(new Pair<>(2, 0));
            List<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(8, 1));
            ship_3_2_coordinates.add(new Pair<>(8, 2));
            ship_3_2_coordinates.add(new Pair<>(8, 3));
            List<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(4, 2));
            ship_2_1_coordinates.add(new Pair<>(5, 2));
            List<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(3, 4));
            ship_2_2_coordinates.add(new Pair<>(4, 4));
            List<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(1, 8));
            ship_2_3_coordinates.add(new Pair<>(2, 8));
            List<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(5, 0));
            List<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(0, 5));
            List<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(6, 8));
            List<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(8, 8));

            extractedCreateShipObjectsAndAddingToContainer(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 3) {
            List<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(0, 4));
            ship_4_1_coordinates.add(new Pair<>(0, 5));
            ship_4_1_coordinates.add(new Pair<>(0, 6));
            ship_4_1_coordinates.add(new Pair<>(0, 7));
            List<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(1, 1));
            ship_3_1_coordinates.add(new Pair<>(2, 1));
            ship_3_1_coordinates.add(new Pair<>(3, 1));
            List<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(6, 1));
            ship_3_2_coordinates.add(new Pair<>(6, 2));
            ship_3_2_coordinates.add(new Pair<>(6, 3));
            List<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(8, 0));
            ship_2_1_coordinates.add(new Pair<>(8, 1));
            List<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(6, 6));
            ship_2_2_coordinates.add(new Pair<>(7, 6));
            List<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(6, 9));
            ship_2_3_coordinates.add(new Pair<>(7, 9));
            List<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(2, 4));
            List<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(4, 5));
            List<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(3, 8));
            List<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(9, 9));

            extractedCreateShipObjectsAndAddingToContainer(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

        if (randomParameter == 4) {
            List<Pair<Integer, Integer>> ship_4_1_coordinates = new ArrayList<>();
            ship_4_1_coordinates.add(new Pair<>(4, 3));
            ship_4_1_coordinates.add(new Pair<>(5, 3));
            ship_4_1_coordinates.add(new Pair<>(6, 3));
            ship_4_1_coordinates.add(new Pair<>(7, 3));
            List<Pair<Integer, Integer>> ship_3_1_coordinates = new ArrayList<>();
            ship_3_1_coordinates.add(new Pair<>(1, 6));
            ship_3_1_coordinates.add(new Pair<>(1, 7));
            ship_3_1_coordinates.add(new Pair<>(1, 8));
            List<Pair<Integer, Integer>> ship_3_2_coordinates = new ArrayList<>();
            ship_3_2_coordinates.add(new Pair<>(3, 7));
            ship_3_2_coordinates.add(new Pair<>(4, 7));
            ship_3_2_coordinates.add(new Pair<>(5, 7));
            List<Pair<Integer, Integer>> ship_2_1_coordinates = new ArrayList<>();
            ship_2_1_coordinates.add(new Pair<>(6, 0));
            ship_2_1_coordinates.add(new Pair<>(6, 1));
            List<Pair<Integer, Integer>> ship_2_2_coordinates = new ArrayList<>();
            ship_2_2_coordinates.add(new Pair<>(5, 9));
            ship_2_2_coordinates.add(new Pair<>(6, 9));
            List<Pair<Integer, Integer>> ship_2_3_coordinates = new ArrayList<>();
            ship_2_3_coordinates.add(new Pair<>(8, 5));
            ship_2_3_coordinates.add(new Pair<>(8, 6));
            List<Pair<Integer, Integer>> ship_1_1_coordinates = new ArrayList<>();
            ship_1_1_coordinates.add(new Pair<>(1, 0));
            List<Pair<Integer, Integer>> ship_1_2_coordinates = new ArrayList<>();
            ship_1_2_coordinates.add(new Pair<>(3, 0));
            List<Pair<Integer, Integer>> ship_1_3_coordinates = new ArrayList<>();
            ship_1_3_coordinates.add(new Pair<>(8, 8));
            List<Pair<Integer, Integer>> ship_1_4_coordinates = new ArrayList<>();
            ship_1_4_coordinates.add(new Pair<>(9, 1));

            extractedCreateShipObjectsAndAddingToContainer(ship_4_1_coordinates, ship_3_1_coordinates, ship_3_2_coordinates,
                    ship_2_1_coordinates, ship_2_2_coordinates, ship_2_3_coordinates, ship_1_1_coordinates,
                    ship_1_2_coordinates, ship_1_3_coordinates, ship_1_4_coordinates);
        }

    }

    public void extractedCreateShipObjectsAndAddingToContainer
            (List<Pair<Integer, Integer>> ship_4_1_coordinates, List<Pair<Integer, Integer>> ship_3_1_coordinates,
             List<Pair<Integer, Integer>> ship_3_2_coordinates, List<Pair<Integer, Integer>> ship_2_1_coordinates,
             List<Pair<Integer, Integer>> ship_2_2_coordinates, List<Pair<Integer, Integer>> ship_2_3_coordinates,
             List<Pair<Integer, Integer>> ship_1_1_coordinates, List<Pair<Integer, Integer>> ship_1_2_coordinates,
             List<Pair<Integer, Integer>> ship_1_3_coordinates, List<Pair<Integer, Integer>> ship_1_4_coordinates) {

        Ship computerShip_4_1 = new Ship("Ship 4-masts (computer) (1)", ship_4_1_coordinates);
        Ship computerShip_3_1 = new Ship("Ship 3-masts (computer) (1)", ship_3_1_coordinates);
        Ship computerShip_3_2 = new Ship("Ship 3-masts (computer) (2)", ship_3_2_coordinates);
        Ship computerShip_2_1 = new Ship("Ship 2-masts (computer) (1)", ship_2_1_coordinates);
        Ship computerShip_2_2 = new Ship("Ship 2-masts (computer) (2)", ship_2_2_coordinates);
        Ship computerShip_2_3 = new Ship("Ship 2-masts (computer) (3)", ship_2_3_coordinates);
        Ship computerShip_1_1 = new Ship("Ship 1-masts (computer) (1)", ship_1_1_coordinates);
        Ship computerShip_1_2 = new Ship("Ship 1-masts (computer) (2)", ship_1_2_coordinates);
        Ship computerShip_1_3 = new Ship("Ship 1-masts (computer) (3)", ship_1_3_coordinates);
        Ship computerShip_1_4 = new Ship("Ship 1-masts (computer) (4)", ship_1_4_coordinates);
        List<Ship> shipCollection = new ArrayList<>(Arrays.asList(computerShip_4_1, computerShip_3_1,
                computerShip_3_2, computerShip_2_1, computerShip_2_2, computerShip_2_3,
                computerShip_1_1, computerShip_1_2, computerShip_1_3, computerShip_1_4));

        shipsContainer.addComputerShipsToContainer(shipCollection);

    }

    public void protectAllComputerShipsPositions() {
        Map<String, Ship> map = shipsContainer.getSetOfComputerShips();
        int board[][] = player.getComputerBoard();
        for (Map.Entry<String, Ship> entry : map.entrySet()) {
            List<Pair<Integer, Integer>> shipCoordinates = entry.getValue().getMastsCoordinates();
            player.protectShipPosition(board, shipCoordinates);
        }
    }

}
