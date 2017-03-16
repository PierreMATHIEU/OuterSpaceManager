package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import android.app.Activity;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;
import java.util.ArrayList;

/**
 * Created by Piou on 13/03/2017.
 */

public class Fleet {

    private Integer size;
    private ArrayList<Ship> ships;

    public Fleet(Integer size, ArrayList<Ship> fleet) {
        this.size = size;
        this.ships = fleet;
    }

    public ArrayList<Ship> getShips(){ return this.ships;}
    public Integer getSize(){ return this.size;}
}
