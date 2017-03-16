package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 14/03/2017.
 */

public class Searches {

    private Integer size;
    private ArrayList<Search> searches;

    public Searches(Integer size, ArrayList<Search> searches) {
        this.size = size;
        searches = searches;
    }
    public Integer getSize(){return this.size;}
    public ArrayList<Search> getSearches(){ return this.searches;}

}
