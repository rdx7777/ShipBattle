package com.kodilla;

import java.util.ArrayList;
import java.util.HashMap;

public class ShipsContainer {
    private HashMap<String, Ship> setOfShips = new HashMap<>();
    private HashMap<String, Ship> setOfComputerShips = new HashMap<>();
    private ArrayList<ShipMast> setOfShipMasts = new ArrayList<>();
    private ArrayList<ShipMast> setOfComputerShipmasts = new ArrayList<>();

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

    public void addShipMastToContainer(ShipMast shipMast) {
        setOfShipMasts.add(shipMast);
    }

    public void removeShipMastFromContainer(ShipMast shipMast) {
        setOfShipMasts.remove(shipMast);
    }

    public ArrayList<ShipMast> getSetOfShipMasts() {
        return setOfShipMasts;
    }

    public void addComputerShipMastToContainer(ShipMast shipMast) {
        setOfComputerShipmasts.add(shipMast);
    }

    public void removeComputerShipMastFromContainer(ShipMast shipMast) {
        setOfComputerShipmasts.remove(shipMast);
    }

    public ArrayList<ShipMast> getSetOfComputerShipmasts() {
        return setOfComputerShipmasts;
    }

}
