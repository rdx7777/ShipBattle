package com.kodilla;

import java.util.ArrayList;
import java.util.HashMap;

public class ShipsContainer {
    HashMap<Ship, Integer> setOfShips = new HashMap<>();

    public void addShipsToContainer(ArrayList<Ship> shipCollection) {
        for(Ship ship : shipCollection) {
            setOfShips.put(ship, 0);
        }
    }

    public HashMap<Ship, Integer> getSetOfShips() {
        return setOfShips;
    }
}