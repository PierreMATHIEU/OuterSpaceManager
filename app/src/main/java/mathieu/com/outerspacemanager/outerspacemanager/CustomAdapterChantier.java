package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnShipIncrementClick;

/**
 * Created by Piou on 14/03/2017.
 */

public class CustomAdapterChantier extends RecyclerView.Adapter<CustomAdapterChantier.ChantierViewHolder> {

    private final ArrayList<Ship> values;
    private final Context context;
    private Ship myShip;
    private OnShipIncrementClick myShipClicked;


    public void setMyShipClicked(OnShipIncrementClick myShipClicked) {
        this.myShipClicked = myShipClicked;
    }

    public CustomAdapterChantier(ArrayList<Ship> values, Context context) {
        this.values = values;
        this.context = context;
    }

    //Créé les vues
    @Override
    public CustomAdapterChantier.ChantierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_adapterchantierspatial, parent, false);
        CustomAdapterChantier.ChantierViewHolder viewHolder = new CustomAdapterChantier.ChantierViewHolder(rowView);
        return viewHolder;
    }

    //Remplire les vues
    @Override
    public void onBindViewHolder(final CustomAdapterChantier.ChantierViewHolder holder, final int position) {
        Ship ship = values.get(position);



        //Click sur la ReciclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myShipClicked.onShipIncrementClick(position, Integer.parseInt(holder.edit_ChantierAmount.getText().toString()));
            }
        });

        holder.txt_ChantierName.setText("Name : " + ship.getName());
        holder.txt_ChantierGasCost.setText("Gas Cost : " +ship.getGasCost());
        holder.txt_ChantierMineralCost.setText("Mineral Cost : " + ship.getMineralCost());
        holder.txt_ChantierMaxAtt.setText("Max Attack : " + ship.getMaxAttack());
        holder.txt_ChantierMinAtt.setText("Min Attack : " + ship.getMinAttack());
        holder.txt_ChantierLife.setText("Life : " + ship.getLife());
        holder.txt_ChantierShield.setText("Shield : " + ship.getShield());
        holder.txt_ChantierSpeed.setText("Speed : " + ship.getSpeed());
        holder.txt_ChantierStation.setText("Station need lvl : " + ship.getSpatioport());
        holder.txt_ChantierTimeToBuild.setText("Time Build : " + ship.getTimeBuild());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ChantierViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_ChantierName;
        private TextView txt_ChantierGasCost;
        private TextView txt_ChantierMineralCost;
        private TextView txt_ChantierMaxAtt;
        private TextView txt_ChantierMinAtt;
        private TextView txt_ChantierLife;
        private TextView txt_ChantierShield;
        private TextView txt_ChantierSpeed;
        private TextView txt_ChantierStation;
        private TextView txt_ChantierTimeToBuild;
        private EditText edit_ChantierAmount;

        public ChantierViewHolder(View itemView) {
            super(itemView);
            txt_ChantierName = (TextView) itemView.findViewById(R.id.txt_ChantierName);
            txt_ChantierGasCost = (TextView) itemView.findViewById(R.id.txt_ChantierGasCost);
            txt_ChantierMineralCost = (TextView) itemView.findViewById(R.id.txt_ChantierMineralCost);
            txt_ChantierMaxAtt = (TextView) itemView.findViewById(R.id.txt_ChantierMaxAttack);
            txt_ChantierMinAtt = (TextView) itemView.findViewById(R.id.txt_ChantierMinAttack);
            txt_ChantierLife = (TextView) itemView.findViewById(R.id.txt_ChantierLife);
            txt_ChantierShield = (TextView) itemView.findViewById(R.id.txt_ChantierShield);
            txt_ChantierSpeed = (TextView) itemView.findViewById(R.id.txt_ChantierSpeed);
            txt_ChantierStation = (TextView) itemView.findViewById(R.id.txt_ChantierSpatioportLevelNeeded);
            txt_ChantierTimeToBuild = (TextView) itemView.findViewById(R.id.txt_ChantierTimeToBuild);
            edit_ChantierAmount = (EditText) itemView.findViewById(R.id.edit_ChantierAmount);
        }
    }
}
