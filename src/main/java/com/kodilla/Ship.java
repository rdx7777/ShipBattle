package com.kodilla;

import javafx.util.Pair;
import java.util.ArrayList;

public class Ship {
    private String shipName;
    private ArrayList<Pair<Integer, Integer>> mastsCoordinates;
    private Integer status = 0; // 0 - ship doesn't exist; 1 - ship exists; -1 - ship's sunk

    public Ship(String name, ArrayList<Pair<Integer, Integer>> mastsCoordinates) {
        this.shipName = name;
        this.mastsCoordinates = mastsCoordinates;
    }

    public String getName() {
        return shipName;
    }

    public ArrayList<Pair<Integer, Integer>> getMastsCoordinates() {
        return mastsCoordinates;
    }

    public void setCoordinates(ArrayList<Pair<Integer, Integer>> coordinates) {
        mastsCoordinates = coordinates;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int newStatus) {
        status = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;

        Ship ship = (Ship) o;

        if (!shipName.equals(ship.shipName)) return false;
        return mastsCoordinates.equals(ship.mastsCoordinates);
    }

    @Override
    public int hashCode() {
        int result = shipName.hashCode();
        result = 31 * result + mastsCoordinates.hashCode();
        return result;
    }

}
