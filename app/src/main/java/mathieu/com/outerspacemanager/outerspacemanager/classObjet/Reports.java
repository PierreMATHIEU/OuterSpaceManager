package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

import java.util.ArrayList;

/**
 * Created by Piou on 20/03/2017.
 */

public class Reports {
    private ArrayList<Report> reports;

    public Reports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}
