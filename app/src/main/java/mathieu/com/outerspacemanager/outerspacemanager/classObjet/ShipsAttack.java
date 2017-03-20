package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 20/03/2017.
 */

public class ShipsAttack {
    private ArrayList<Ship> Ship;
    private Integer shipId;
    private Integer amount;


    public ShipsAttack(Integer shipId, Integer amount) {
        this.shipId = shipId;
        this.amount = amount;
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amout) {
        this.amount = amout;
    }

    public void addShip(Ship ship){
        this.Ship.add(ship);
    }
}
