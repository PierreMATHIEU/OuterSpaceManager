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


    public Ship(int id, String name, int amount){
        this.shipId = id;
        this.name = name;
        this.amount = amount;
    }

    public Integer getShipId(){ return this.shipId;}
    public Integer getAmount(){ return this.amount;}
    public String getName(){ return this.name;}
    public Integer getLife(){ return this.life;}
    public Integer getShield(){ return this.shield;}
    public Integer getMaxAttack(){ return this.maxAttack;}
    public Integer getMinAttack(){ return this.minAttack;}
    public Integer getSpeed(){ return this.speed;}
    public Integer getGasCost(){ return this.gasCost;}
    public Integer getMineralCost(){ return this.mineralCost;}
    public Integer getTimeBuild(){ return this.timeToBuild;}
    public Integer getSpatioport(){ return this.spatioportLevelNeeded;}
}

