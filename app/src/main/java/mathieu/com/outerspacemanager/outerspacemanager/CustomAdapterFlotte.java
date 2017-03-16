package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;

/**
 * Created by Piou on 13/03/2017.
 */

public class CustomAdapterFlotte extends RecyclerView.Adapter<CustomAdapterFlotte.FlotteViewHolder> {

    private final ArrayList<Ship> values;
    private final Context context;

    public CustomAdapterFlotte(ArrayList<Ship> values, Context context) {
        this.values = values;
        this.context = context;
    }

    //Créé les vues
    @Override
    public CustomAdapterFlotte.FlotteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_adapterflotte, parent, false);
        FlotteViewHolder viewHolder = new FlotteViewHolder(rowView);
        return viewHolder;
    }

    //Remplire les vues
    @Override
    public void onBindViewHolder(CustomAdapterFlotte.FlotteViewHolder holder, int position) {
        Ship ship = values.get(position);
        holder.txt_FlotteName.setText(ship.getName());
        holder.txt_FlotteAmount.setText("Nombre : " + ship.getAmount().toString());
        holder.txt_FlotteMaxAtt.setText("Max attack : " + ship.getMaxAttack());
        holder.txt_FlotteMinAtt.setText("Min attack : " + ship.getMinAttack());
        holder.txt_FlotteLife.setText("Life : " + ship.getLife());
        holder.txt_FlotteShield.setText("Shield : " + ship.getShield());
        holder.txt_FlotteSpeed.setText("Speed : " + ship.getSpeed());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class FlotteViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_FlotteName;
        private TextView txt_FlotteAmount;
        private TextView txt_FlotteGasCost;
        private TextView txt_FlotteMineralCost;
        private TextView txt_FlotteMaxAtt;
        private TextView txt_FlotteMinAtt;
        private TextView txt_FlotteLife;
        private TextView txt_FlotteShield;
        private TextView txt_FlotteSpeed;
        private TextView txt_FlotteStation;
        private TextView txt_FlotteTimeToBuild;

        public FlotteViewHolder(View itemView) {
            super(itemView);
            txt_FlotteName = (TextView) itemView.findViewById(R.id.txt_FlotteName);
            txt_FlotteAmount = (TextView) itemView.findViewById(R.id.txt_FlotteAmount);
            txt_FlotteMaxAtt = (TextView) itemView.findViewById(R.id.txt_FlotteMaxAttack);
            txt_FlotteMinAtt = (TextView) itemView.findViewById(R.id.txt_FlotteMinAttack);
            txt_FlotteLife = (TextView) itemView.findViewById(R.id.txt_FlotteLife);
            txt_FlotteShield = (TextView) itemView.findViewById(R.id.txt_FlotteShield);
            txt_FlotteSpeed = (TextView) itemView.findViewById(R.id.txt_FlotteSpeed);
        }
    }
}
