package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;

/**
 * Created by Piou on 07/03/2017.
 */

public class Buildings {

    private Integer size;
    private ArrayList<Building> buildings;

    public Buildings(Integer size, ArrayList<Building> buildings) {
        this.size = size;
        buildings = buildings;
    }

    public ArrayList<Building> getBuildings(){ return this.buildings;}
}
