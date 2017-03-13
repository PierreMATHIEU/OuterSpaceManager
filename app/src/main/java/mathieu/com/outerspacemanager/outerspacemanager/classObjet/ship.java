package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

/**
 * Created by Piou on 13/03/2017.
 */

public class Ship {

    private String name;
    private Integer life;
    private Integer shield;
    private Integer maxAttack;
    private Integer minAttack;
    private Integer speed;
    private Integer gasCost;
    private Integer mineralCost;


    public Ship(String name) {
        this.name = name;
        this.mineralCost = mineralCost;
        this.life = life;
        this.shield = shield;
        this.maxAttack = maxAttack;
        this.minAttack = minAttack;
        this.speed = speed;
        this.gasCost = gasCost;
    }

    public String getName(){ return this.name;}
    public String getLife(){ return this.life.toString();}
    public String getShield(){ return this.shield.toString();}
}
