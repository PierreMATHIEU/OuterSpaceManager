package mathieu.com.outerspacemanager.outerspacemanager;

import android.widget.Button;

/**
 * Created by Piou on 06/03/2017.
 */

public class User {

    private String username;
    private String password;
    private String token;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getIdentifiant(){
        return this.username;
    }
    public String getToken(){
        return this.token;
    }
}
