package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;

/**
 * Created by Piou on 07/03/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<Building> {

    private final Context context;
    private final ArrayList<Building> values;
    private Building myBuilding;

    public CustomArrayAdapter(Context context, ArrayList<Building> values) {
        super(context, R.layout.custom_arrayadapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_arrayadapter, parent, false);

        myBuilding = values.get(position);

        TextView building_name = (TextView) rowView.findViewById(R.id.txt_BuildingName);
        TextView textViewLvl = (TextView) rowView.findViewById(R.id.txt_BuildingLvl);
        TextView textViewEffect = (TextView) rowView.findViewById(R.id.txt_BuildingEffect);
        TextView textViewAmountEffect = (TextView)rowView.findViewById(R.id.txt_BuildingAmountEffect);

        TextView textViewBuildingTime = (TextView)rowView.findViewById(R.id.txt_BuildingTime);
        TextView textViewGazCost= (TextView)rowView.findViewById(R.id.txt_BuildingGazCost);
        TextView textViewMineralCost = (TextView)rowView.findViewById(R.id.txt_Building_MineralCost);

        building_name.setText("Name : " + myBuilding.getName());
        textViewLvl.setText("Level : " + myBuilding.getLevel().toString());
        textViewEffect.setText("Effect : " + myBuilding.getEffect());
        textViewAmountEffect.setText("Amount effect : " + myBuilding.getAmountEffect().toString());
        textViewBuildingTime.setText("Building time : " + myBuilding.getBuildingTime().toString());
        textViewGazCost.setText("Gas cost : " + myBuilding.getGazCost().toString());
        textViewMineralCost.setText("Mineral cost : " + myBuilding.getMineralCost().toString());

        return rowView;
    }



}
