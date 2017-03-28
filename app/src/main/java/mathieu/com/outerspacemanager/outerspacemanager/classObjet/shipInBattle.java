package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 28/03/2017.
 */

public class ShipInBattle {

    private Integer capacity;

    private ArrayList<Ship> fleet;

    private Integer survivingShips;

    public ShipInBattle(Integer capacity, Integer survivingShips) {
        this.capacity = capacity;
        this.survivingShips = survivingShips;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public Integer getSurvivingShips() {
        return survivingShips;
    }
}

