package com.kodilla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ShipsContainer {

    private LinkedHashMap<String, Ship> setOfShips = new LinkedHashMap<>();
    private LinkedHashMap<String, Ship> setOfComputerShips = new LinkedHashMap<>();

    private ArrayList<ShipMast> setOfShipMasts = new ArrayList<>();
    private ArrayList<ShipMast> setOfComputerShipMasts = new ArrayList<>();

    private ArrayList<ControlSquare> setOfControlSquares = new ArrayList<>();
    private ArrayList<ControlSquare> setOfComputerControlSquares = new ArrayList<>();

    private ArrayList<Hit> setOfHits = new ArrayList<>();
    private ArrayList<Hit> setOfComputerHits = new ArrayList<>();

    private ArrayList<Missed> setOfMisseds = new ArrayList<>();
    private ArrayList<Missed> setOfComputerMisseds = new ArrayList<>();

    public void addShipsToContainer(ArrayList<Ship> shipCollection) {
        for(Ship ship : shipCollection) {
            setOfShips.put(ship.getName(), ship);
        }
    }

    public LinkedHashMap<String, Ship> getSetOfShips() {
        return setOfShips;
    }

    public void addComputerShipsToContainer(ArrayList<Ship> computerShipCollection) {
        for(Ship ship : computerShipCollection) {
            setOfComputerShips.put(ship.getName(), ship);
        }
    }

    public LinkedHashMap<String, Ship> getSetOfComputerShips() {
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
        setOfComputerShipMasts.add(shipMast);
    }

//    public void removeComputerShipMastFromContainer(ShipMast shipMast) {
//        setOfComputerShipMasts.remove(shipMast);
//    }

    public ArrayList<ShipMast> getSetOfComputerShipMasts() {
        return setOfComputerShipMasts;
    }

    public ArrayList<ControlSquare> getSetOfControlSquares() {
        return setOfControlSquares;
    }

    public ArrayList<ControlSquare> getSetOfComputerControlSquares() {
        return setOfComputerControlSquares;
    }

    public ArrayList<Hit> getSetOfHits() {
        return setOfHits;
    }

    public ArrayList<Hit> getSetOfComputerHits() {
        return setOfComputerHits;
    }

    public ArrayList<Missed> getSetOfMisseds() {
        return setOfMisseds;
    }

    public ArrayList<Missed> getSetOfComputerMisseds() {
        return setOfComputerMisseds;
    }
}
