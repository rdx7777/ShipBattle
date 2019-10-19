package com.kodilla;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShipsContainer {

    private Map<String, Ship> setOfPlayerShips = new LinkedHashMap<>();
    private Map<String, Ship> setOfComputerShips = new LinkedHashMap<>();

    private List<ShipMast> setOfPlayerShipMasts = new ArrayList<>();
    private List<ShipMast> setOfComputerShipMasts = new ArrayList<>();

    private List<ControlSquare> setOfPlayerControlSquares = new ArrayList<>();
    private List<ControlSquare> setOfComputerControlSquares = new ArrayList<>();

    private List<Hit> setOfPlayerHits = new ArrayList<>();
    private List<Hit> setOfComputerHits = new ArrayList<>();

    private List<Missed> setOfPlayerMissed = new ArrayList<>();
    private List<Missed> setOfComputerMissed = new ArrayList<>();

    public void addPlayerShipsToContainer(List<Ship> shipCollection) {
        for(Ship ship : shipCollection) {
            setOfPlayerShips.put(ship.getName(), ship);
        }
    }

    public Map<String, Ship> getSetOfPlayerShips() {
        return setOfPlayerShips;
    }

    public void addComputerShipsToContainer(List<Ship> computerShipCollection) {
        for(Ship ship : computerShipCollection) {
            setOfComputerShips.put(ship.getName(), ship);
        }
    }

    public Map<String, Ship> getSetOfComputerShips() {
        return setOfComputerShips;
    }

    public void addPlayerShipMastToContainer(ShipMast shipMast) {
        setOfPlayerShipMasts.add(shipMast);
    }

    public void removePlayerShipMastFromContainer(ShipMast shipMast) {
        setOfPlayerShipMasts.remove(shipMast);
    }

    public List<ShipMast> getSetOfPlayerShipMasts() {
        return setOfPlayerShipMasts;
    }

    public void addComputerShipMastToContainer(ShipMast shipMast) {
        setOfComputerShipMasts.add(shipMast);
    }

    public List<ShipMast> getSetOfComputerShipMasts() {
        return setOfComputerShipMasts;
    }

    public List<ControlSquare> getSetOfPlayerControlSquares() {
        return setOfPlayerControlSquares;
    }

    public List<ControlSquare> getSetOfComputerControlSquares() {
        return setOfComputerControlSquares;
    }

    public List<Hit> getSetOfPlayerHits() {
        return setOfPlayerHits;
    }

    public List<Hit> getSetOfComputerHits() {
        return setOfComputerHits;
    }

    public List<Missed> getSetOfPlayerMissed() {
        return setOfPlayerMissed;
    }

    public List<Missed> getSetOfComputerMissed() {
        return setOfComputerMissed;
    }
}
