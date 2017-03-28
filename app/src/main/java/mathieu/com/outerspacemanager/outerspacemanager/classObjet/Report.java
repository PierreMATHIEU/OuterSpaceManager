package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 20/03/2017.
 */

public class Report {

    private long date;
    private long dateInv;
    private String from;
    private double gasWon;
    private double mineralsWon;
    private String to;
    private String type;
    private ShipInBattle attackerFleetAfterBattle;
    private ShipInBattle defenderFleetAfterBattle;

    public ShipInBattle getAttackerFleetAfterBattle() {
        return attackerFleetAfterBattle;
    }

    public void setAttackerFleetAfterBattle(ShipInBattle attackerFleetAfterBattle) {
        this.attackerFleetAfterBattle = attackerFleetAfterBattle;
    }

    public ShipInBattle getDefenderFleetAfterBattle() {
        return defenderFleetAfterBattle;
    }

    public void setDefenderFleetAfterBattle(ShipInBattle defenderFleetAfterBattle) {
        this.defenderFleetAfterBattle = defenderFleetAfterBattle;
    }

    public Report() {

    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDateInv() {
        return dateInv;
    }

    public void setDateInv(long dateInv) {
        this.dateInv = dateInv;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getGasWon() {
        return gasWon;
    }

    public void setGasWon(Integer gasWon) {
        this.gasWon = gasWon;
    }

    public double getMineralsWon() {
        return mineralsWon;
    }

    public void setMineralsWon(Integer mineralsWon) {
        this.mineralsWon = mineralsWon;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
