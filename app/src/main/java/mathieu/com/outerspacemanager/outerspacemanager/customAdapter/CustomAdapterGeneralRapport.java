package mathieu.com.outerspacemanager.outerspacemanager.customAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.AttackResponse;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Fleet;

/**
 * Created by Piou on 21/03/2017.
 */

public class CustomAdapterGeneralRapport extends RecyclerView.Adapter<CustomAdapterGeneralRapport.GeneralRapportViewHolder> {

    private final ArrayList<AttackResponse> values;
    private final Context context;

    public CustomAdapterGeneralRapport(ArrayList<AttackResponse> values, Context context) {
        this.values = values;
        this.context = context;
    }

    //Créé les vues
    @Override
    public CustomAdapterGeneralRapport.GeneralRapportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_adaptergeneralrapport, parent, false);
        CustomAdapterGeneralRapport.GeneralRapportViewHolder viewHolder = new CustomAdapterGeneralRapport.GeneralRapportViewHolder(rowView);
        return viewHolder;
    }

    //Remplire les vues
    @Override
    public void onBindViewHolder(final CustomAdapterGeneralRapport.GeneralRapportViewHolder holder, final int position) {
        final AttackResponse attackResponse = values.get(position);
        final String shipsAttack = attackResponse.getFleetSend();
        Integer countShips = 0;
        String nameShips = "";

        Gson gson = new Gson();
        Fleet myObj = gson.fromJson(attackResponse.getFleetSend(), Fleet.class);


        Date dateReal = new Date(values.get(position).getAttackTime());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateFormatted = formatter.format(dateReal);


        for (int i =0 ; i < myObj.getShips().size(); i++){
                nameShips +=  myObj.getShips().get(i).getName() + " : " + myObj.getShips().get(i).getAmount()+"\n";

        }


        holder.txt_GeneralRapportTime.setText("Heure de l'attaque : " + dateFormatted );
        holder.txt_GeneralRapportShipName.setText("Vaisseau : " + nameShips);
        //holder.txt_GeneralRapportShip.setText("Nombre de vaisseau : " + countShips);
        holder.txt_GeneralRapportUser.setText("Cible : " + attackResponse.getUserVictime());


    }

    @Override
    public int getItemCount() {
        return values.size();

    }

    public class GeneralRapportViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_GeneralRapportTime;
        private TextView txt_GeneralRapportShip;
        private TextView txt_GeneralRapportShipName;
        private TextView txt_GeneralRapportUser;


        public GeneralRapportViewHolder(View itemView) {
            super(itemView);
            txt_GeneralRapportTime = (TextView) itemView.findViewById(R.id.txt_GeneraleReportTime);
            txt_GeneralRapportShip = (TextView) itemView.findViewById(R.id.txt_GeneraleReportShip);
            txt_GeneralRapportShipName = (TextView) itemView.findViewById(R.id.txt_GeneraleReportShipName);
            txt_GeneralRapportUser = (TextView) itemView.findViewById(R.id.txt_GeneraleReportUsername);
        }
    }

}
