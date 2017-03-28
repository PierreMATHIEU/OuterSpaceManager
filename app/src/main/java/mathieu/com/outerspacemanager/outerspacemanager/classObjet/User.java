package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import android.widget.Button;

/**
 * Created by Piou on 06/03/2017.
 */

public class User {

    private String username;
    private String password;
    private String email;
    private String token;
    private Double points;
    private Double gas;
    private Double minerals;
    private Integer gasModifier;
    private Integer mineralsModifier;

    public User(String username, String password,String email, Double gas, Double minerals){
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
        this.gas = gas;
        this.minerals = minerals;
    }

    public String getUsername(){
        return this.username;
    }
    public String getToken(){ return this.token; }
    public String getPoints(){ return this.points.toString(); }
    public String getGas(){ return this.gas.toString(); }
    public String getMinerals(){ return this.minerals.toString(); }
    public String getGasMod(){ return this.gasModifier.toString(); }
    public String getMineralMod(){ return this.mineralsModifier.toString(); }


}
