package mathieu.com.outerspacemanager.outerspacemanager.customAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Report;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.ShipInBattle;

/**
 * Created by Piou on 20/03/2017.
 */

public class CustomAdapterReports extends ArrayAdapter<Report> {

    private final Context context;
    private final ArrayList<Report> values;
    private Report myReport;
    private ShipInBattle myBattleShip;

    public CustomAdapterReports(Context context, ArrayList<Report> values) {
        super(context, R.layout.custom_adaptergenerale, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_adaptergenerale, parent, false);

        myReport = values.get(position);

        Date dateReal = new Date(values.get(position).getDate());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateFormatted = formatter.format(dateReal);


        TextView txt_ReportTarget = (TextView) rowView.findViewById(R.id.txt_ReportTarget);
        TextView txt_ReportFrom = (TextView) rowView.findViewById(R.id.txt_ReportFrom);
        TextView txt_ReportGas = (TextView) rowView.findViewById(R.id.txt_ReportGas);
        TextView txt_ReportMineral = (TextView) rowView.findViewById(R.id.txt_ReportMineral);
        TextView txt_ReportDateAttack = (TextView)rowView.findViewById(R.id.txt_ReportDateAttack);
        TextView txt_ReportsVaisseauLose = (TextView)rowView.findViewById(R.id.txt_ReportsVaisseauLose);
        TextView txt_ReportVaisseauTarget = (TextView)rowView.findViewById(R.id.txt_ReportVaisseauTarget);

        txt_ReportTarget.setText("Défenseur : " + myReport.getTo());
        txt_ReportsVaisseauLose.setText("  -> Vaisseau survivant : " + myReport.getDefenderFleetAfterBattle().getSurvivingShips());
        txt_ReportFrom.setText("Attaquant : " + myReport.getFrom());
        txt_ReportVaisseauTarget.setText("  -> Vaisseau survivant : " + myReport.getAttackerFleetAfterBattle().getSurvivingShips().toString());
        txt_ReportGas.setText("Gas volé : " + String.valueOf(myReport.getGasWon()));
        txt_ReportMineral.setText("Minerals volé : " + String.valueOf(myReport.getMineralsWon()));
        txt_ReportDateAttack.setText("Date : " + dateFormatted.toString());


        return rowView;
    }



}
