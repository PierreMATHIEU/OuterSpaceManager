package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import android.widget.Button;

/**
 * Created by Piou on 06/03/2017.
 */

public class User {

    private String username;
    private String password;
    private String token;
    private Integer points;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUsername(){
        return this.username;
    }
    public String getToken(){ return this.token; }
    public String getPoints(){ return this.points.toString(); }
}
