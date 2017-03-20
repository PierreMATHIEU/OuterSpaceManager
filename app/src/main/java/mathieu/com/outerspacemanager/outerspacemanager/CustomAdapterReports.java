package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Report;

/**
 * Created by Piou on 20/03/2017.
 */

public class CustomAdapterReports extends ArrayAdapter<Report> {

    private final Context context;
    private final ArrayList<Report> values;
    private Report myReport;

    public CustomAdapterReports(Context context, ArrayList<Report> values) {
        super(context, R.layout.custom_arrayadapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_arrayadapter, parent, false);

        myReport = values.get(position);



        TextView txt_ReportTarget = (TextView) rowView.findViewById(R.id.txt_ReportTarget);
        TextView txt_ReportGas = (TextView) rowView.findViewById(R.id.txt_ReportGas);
        TextView txt_ReportMineral = (TextView) rowView.findViewById(R.id.txt_ReportMineral);
        TextView txt_ReportDateAttack = (TextView)rowView.findViewById(R.id.txt_ReportDateAttack);
        TextView txt_ReportsVaisseauLose = (TextView)rowView.findViewById(R.id.txt_ReportsVaisseauLose);
        TextView txt_ReportVaisseauTarget = (TextView)rowView.findViewById(R.id.txt_ReportVaisseauTarget);

        txt_ReportTarget.setText("Name : " + myReport.getTo());
        txt_ReportGas.setText("Level : " + myReport.getGasWon().toString());
        txt_ReportMineral.setText("Effect : " + myReport.getMineralsWon().toString());
        txt_ReportDateAttack.setText("Amount effect : " + myReport.getDate());
        //txt_ReportsVaisseauLose.setText("Building time : " + myReport.get);


        return rowView;
    }



}
