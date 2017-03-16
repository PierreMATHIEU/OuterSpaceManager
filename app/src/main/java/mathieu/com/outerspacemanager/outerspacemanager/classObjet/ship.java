package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

/**
 * Created by Piou on 13/03/2017.
 */

public class Ship {

    private Integer shipId;
    private String name;
    private Integer amount;
    private Integer life;
    private Integer shield;
    private Integer maxAttack;
    private Integer minAttack;
    private Integer speed;
    private Integer gasCost;
    private Integer mineralCost;
    private Integer timeToBuild;
    private Integer spatioportLevelNeeded;


    public Ship(Integer shipId, String name) {
        this.shipId = shipId;
        this.name = name;
        this.mineralCost = mineralCost;
        this.life = life;
        this.shield = shield;
        this.maxAttack = maxAttack;
        this.minAttack = minAttack;
        this.speed = speed;
        this.gasCost = gasCost;
    }
    public Integer getShipId(){ return this.shipId;}
    public Integer getAmount(){ return this.amount;}
    public String getName(){ return this.name;}
    public String getLife(){ return this.life.toString();}
    public String getShield(){ return this.shield.toString();}
    public String getMaxAttack(){ return this.maxAttack.toString();}
    public String getMinAttack(){ return this.minAttack.toString();}
    public String getSpeed(){ return this.speed.toString();}
    public String getGasCost(){ return this.gasCost.toString();}
    public String getMineralCost(){ return this.mineralCost.toString();}
    public String getTimeBuild(){ return this.timeToBuild.toString();}
    public String getSpatioport(){ return this.spatioportLevelNeeded.toString();}
}

