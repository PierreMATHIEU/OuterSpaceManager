package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.AttackResponse;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.ShipsAttack;
import mathieu.com.outerspacemanager.outerspacemanager.database.AttackDAO;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnAttackClickListener;

/**
 * Created by Piou on 21/03/2017.
 */

public class CustomAdapterGeneralRapport extends RecyclerView.Adapter<CustomAdapterGeneralRapport.GeneralRapportViewHolder> {

    private final ArrayList<AttackResponse> values;
    private final Context context;;

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


        Gson gson = new Gson();
        AttackResponse myObj = gson.fromJson(attackResponse.getFleetSend(), AttackResponse.class);

        Date dateReal = new Date(values.get(position).getAttackTime());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateFormatted = formatter.format(dateReal);

        holder.txt_GeneralRapportTime.setText("Heure de l'attaque : " + dateFormatted );
        //holder.txt_GeneralRapportShip.setText("Rapport Ship : " + attackResponse.getFleetSend());
        holder.txt_GeneralRapportUser.setText("Cible : " + attackResponse.getUserVictime());


    }

    @Override
    public int getItemCount() {
        return values.size();

    }

    public class GeneralRapportViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_GeneralRapportTime;
        private TextView txt_GeneralRapportShip;
        private TextView txt_GeneralRapportUser;


        public GeneralRapportViewHolder(View itemView) {
            super(itemView);
            txt_GeneralRapportTime = (TextView) itemView.findViewById(R.id.txt_GeneraleReportTime);
            txt_GeneralRapportShip = (TextView) itemView.findViewById(R.id.txt_GeneraleReportShip);
            txt_GeneralRapportUser = (TextView) itemView.findViewById(R.id.txt_GeneraleReportUsername);
        }
    }

}
