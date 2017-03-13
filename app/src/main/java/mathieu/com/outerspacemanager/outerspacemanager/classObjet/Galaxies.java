package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 13/03/2017.
 */

public class Galaxies {

    private ArrayList<User> users;

    public Galaxies(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers(){ return this.users;}
}
