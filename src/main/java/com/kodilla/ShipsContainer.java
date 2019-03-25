package com.kodilla;

import java.util.ArrayList;
import java.util.HashMap;

public class ShipsContainer {
    HashMap<Ship, Integer> setOfShips = new HashMap<>();
    HashMap<Ship, Integer> setOfComputerShips = new HashMap<>();

    public void addShipsToContainer(ArrayList<Ship> shipCollection) {
        for(Ship ship : shipCollection) {
            setOfShips.put(ship, 0);
        }
    }

    public HashMap<Ship, Integer> getSetOfShips() {
        return setOfShips;
    }

    public void addComputerShipsToContainer(ArrayList<Ship> computerShipCollection) {
        for(Ship ship : computerShipCollection) {
            setOfComputerShips.put(ship, 0);
        }
    }

    public HashMap<Ship, Integer> getSetOfComputerShips() {
        return setOfComputerShips;
    }

}
