package com.kodilla;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShipsContainer {

    private Map<String, Ship> setOfShips = new LinkedHashMap<>();
    private Map<String, Ship> setOfComputerShips = new LinkedHashMap<>();

    private List<ShipMast> setOfShipMasts = new ArrayList<>();
    private List<ShipMast> setOfComputerShipMasts = new ArrayList<>();

    private List<ControlSquare> setOfControlSquares = new ArrayList<>();
    private List<ControlSquare> setOfComputerControlSquares = new ArrayList<>();

    private List<Hit> setOfHits = new ArrayList<>();
    private List<Hit> setOfComputerHits = new ArrayList<>();

    private List<Missed> setOfMissed = new ArrayList<>();
    private List<Missed> setOfComputerMissed = new ArrayList<>();

    public void addShipsToContainer(List<Ship> shipCollection) {
        for(Ship ship : shipCollection) {
            setOfShips.put(ship.getName(), ship);
        }
    }

    public Map<String, Ship> getSetOfShips() {
        return setOfShips;
    }

    public void addComputerShipsToContainer(List<Ship> computerShipCollection) {
        for(Ship ship : computerShipCollection) {
            setOfComputerShips.put(ship.getName(), ship);
        }
    }

    public Map<String, Ship> getSetOfComputerShips() {
        return setOfComputerShips;
    }

    public void addShipMastToContainer(ShipMast shipMast) {
        setOfShipMasts.add(shipMast);
    }

    public void removeShipMastFromContainer(ShipMast shipMast) {
        setOfShipMasts.remove(shipMast);
    }

    public List<ShipMast> getSetOfShipMasts() {
        return setOfShipMasts;
    }

    public void addComputerShipMastToContainer(ShipMast shipMast) {
        setOfComputerShipMasts.add(shipMast);
    }

    public List<ShipMast> getSetOfComputerShipMasts() {
        return setOfComputerShipMasts;
    }

    public List<ControlSquare> getSetOfControlSquares() {
        return setOfControlSquares;
    }

    public List<ControlSquare> getSetOfComputerControlSquares() {
        return setOfComputerControlSquares;
    }

    public List<Hit> getSetOfHits() {
        return setOfHits;
    }

    public List<Hit> getSetOfComputerHits() {
        return setOfComputerHits;
    }

    public List<Missed> getSetOfMissed() {
        return setOfMissed;
    }

    public List<Missed> getSetOfComputerMissed() {
        return setOfComputerMissed;
    }
}
