package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

/**
 * Created by Piou on 14/03/2017.
 */

public class Search {
    private Integer searchId;
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

    public Integer getSearchId(){ return this.searchId;}


    public Integer getAmountOfEffectByLevel() {

        int res = amountOfEffectByLevel  * level;
        return res;
    }

    public Integer getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }
    public String getEffect(){return this.effect;}
    public Boolean getBuilding() {
        return this.building;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
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

}
