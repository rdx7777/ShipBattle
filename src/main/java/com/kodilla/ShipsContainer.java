package com.kodilla;

import java.util.ArrayList;
import java.util.HashMap;

public class ShipsContainer {
    HashMap<String, Ship> setOfShips = new HashMap<>();
    HashMap<String, Ship> setOfComputerShips = new HashMap<>();

    public void addShipsToContainer(ArrayList<Ship> shipCollection) {
        for(Ship ship : shipCollection) {
            setOfShips.put(ship.getName(), ship);
        }
    }

    public HashMap<String, Ship> getSetOfShips() {
        return setOfShips;
    }

    public void addComputerShipsToContainer(ArrayList<Ship> computerShipCollection) {
        for(Ship ship : computerShipCollection) {
            setOfComputerShips.put(ship.getName(), ship);
        }
    }

    public HashMap<String, Ship> getSetOfComputerShips() {
        return setOfComputerShips;
    }

}
