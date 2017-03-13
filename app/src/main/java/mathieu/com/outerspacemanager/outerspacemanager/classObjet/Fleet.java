package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Piou on 13/03/2017.
 */

public class Fleet {

    private Integer size;
    private ArrayList<Ship> fleet;

    public Fleet(Integer size, ArrayList<Ship> fleet) {
        this.size = size;
        this.fleet = fleet;
    }

    public ArrayList<Ship> getBuildings(){ return this.fleet;}
}
