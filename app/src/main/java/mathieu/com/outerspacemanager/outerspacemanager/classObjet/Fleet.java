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

    public Fleet() {
        this.ships = new ArrayList<Ship>();
    }

    public ArrayList<Ship> getShips(){ return this.ships;}
    public Integer getSize(){ return this.size;}

    public Integer getNumberOfShip(){
        Integer numberShip = 0;
        for (Integer i = 0; i<this.getSize(); i++){
            if(this.ships.get(i).getAmount() > 0){
                numberShip += this.ships.get(i).getAmount();
            }
        }
        return numberShip;
    }
    public void addShip(Ship ship){
        this.ships.add(ship);
    }
}
