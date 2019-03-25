package com.kodilla;

import javafx.util.Pair;

import java.util.ArrayList;

public class Ship {
    String shipName;
    private ArrayList<Pair<Integer, Integer>> mastsCoordinates;

    public Ship(String name, ArrayList<Pair<Integer, Integer>> mastsCoordinates) {
        this.shipName = name;
        this.mastsCoordinates = mastsCoordinates;
    }

    public String getName() {
        return shipName;
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

    public ArrayList<Pair<Integer, Integer>> getMastsCoordinates() {
        return mastsCoordinates;
    }

    public void setCoordinates(ArrayList<Pair<Integer, Integer>> coordinates) {
        mastsCoordinates = coordinates;
    }

}
