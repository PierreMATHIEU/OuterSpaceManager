package mathieu.com.outerspacemanager.outerspacemanager.classObjet;


import java.io.Serializable;

/**
 * Created by Piou on 07/03/2017.
 */

public class Building implements Serializable {

    private Integer buildingId;
    private Integer amountOfEffectByLevel;
    private Integer amountOfEffectLevel0;
    private Boolean building;
    private String effect;
    private Integer gasCostByLevel;
    private Integer gasCostLevel0;
    private Integer level;
    private Integer mineralCostByLevel;
    private Integer mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;

    public Building(Integer buildingId,Integer amountOfEffectByLevel, Integer timeToBuildLevel0, Integer timeToBuildByLevel, String name, Integer mineralCostByLevel, Integer level, Integer gasCostLevel0, Integer gasCostByLevel, String effect, Integer amountOfEffectLevel0, Boolean building, Integer mineralCostLevel0) {
        this.buildingId = buildingId;
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.name = name;
        this.mineralCostByLevel = mineralCostByLevel;
        this.level = level;
        this.gasCostLevel0 = gasCostLevel0;
        this.gasCostByLevel = gasCostByLevel;
        this.effect = effect;
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
        this.building = building;
        this.mineralCostLevel0 = mineralCostLevel0;
    }

    public Integer getBuildingId(){ return buildingId; }
    public String getEffect() {
        return effect;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public Integer getAmountEffect(){
        int res = amountOfEffectByLevel  * level;
        return res;
    }
    public Integer getBuildingTime(){
        int res = timeToBuildLevel0 + (level * timeToBuildByLevel);
        return res;
    }
    public Integer getGazCost(){
        int res = gasCostLevel0 + (level * gasCostByLevel);
        return res;
    }
    public Integer getMineralCost(){
        int res = mineralCostLevel0 + (level * mineralCostByLevel);
        return res;
    }
    public boolean getIsBuilding(){return this.building;}
}
