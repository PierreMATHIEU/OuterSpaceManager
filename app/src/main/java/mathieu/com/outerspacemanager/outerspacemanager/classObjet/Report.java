package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 20/03/2017.
 */

public class Report {

    private long date;
    private long dateInv;
    private String from;
    private Integer gasWon;
    private Integer mineralsWon;
    private String to;
    private String type;

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

    public Integer getGasWon() {
        return gasWon;
    }

    public void setGasWon(Integer gasWon) {
        this.gasWon = gasWon;
    }

    public Integer getMineralsWon() {
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
