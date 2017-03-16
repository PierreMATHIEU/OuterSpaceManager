package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Search;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnItemClickListener;

/**
 * Created by Piou on 14/03/2017.
 */

public class CustomAdapterRecherche extends RecyclerView.Adapter<CustomAdapterRecherche.RechercheViewHolder> {

    private final ArrayList<Search> values;
    private final Context context;
    //Interface
    private OnItemClickListener myListener;

    //set l'activité
    public void setMyListener(OnItemClickListener myListener) {
        this.myListener = myListener;
    }

    public CustomAdapterRecherche(ArrayList<Search> values, Context context) {
        this.values = values;
        this.context = context;
    }

    //Créé les vues
    @Override
    public CustomAdapterRecherche.RechercheViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_adapterrecherche, parent, false);
        CustomAdapterRecherche.RechercheViewHolder viewHolder = new CustomAdapterRecherche.RechercheViewHolder(rowView);
        return viewHolder;
    }

    //Remplire les vues
    @Override
    public void onBindViewHolder(CustomAdapterRecherche.RechercheViewHolder holder, final int position) {
        Search search = values.get(position);

        //Click sur la ReciclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myListener.onItemClick(position);
            }
        });

        holder.txt_RechercheName.setText("Name : " + search.getName());
        holder.txt_Recherchelvl.setText("Level : " + search.getLevel());
        holder.txt_RechercheEffect.setText("Effect : " + search.getEffect());
        holder.txt_RechercheAmountEffect.setText("Amount Effect : " + search.getAmountOfEffectByLevel());
        holder.txt_RechercheTime.setText("Time to build : " + search.getBuildingTime());
        holder.txt_RechercheGazCost.setText("Gas cost : " + search.getGazCost());
        holder.txt_RechercheMineralCost.setText("Minerals cost : " + search.getMineralCost());

        if( search.getBuilding() == true){
            holder.layout_Recherche.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class RechercheViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_RechercheName;
        private TextView txt_Recherchelvl;
        private TextView txt_RechercheEffect;
        private TextView txt_RechercheAmountEffect;
        private TextView txt_RechercheTime;
        private TextView txt_RechercheGazCost;
        private TextView txt_RechercheMineralCost;
        private LinearLayout layout_Recherche;

        public RechercheViewHolder(View itemView) {
            super(itemView);

            layout_Recherche = (LinearLayout)itemView.findViewById(R.id.layout_Recherche);
            txt_RechercheName = (TextView) itemView.findViewById(R.id.txt_RechercheName);
            txt_Recherchelvl = (TextView) itemView.findViewById(R.id.txt_RechercheLvl);
            txt_RechercheEffect = (TextView) itemView.findViewById(R.id.txt_RechercheEffect);
            txt_RechercheAmountEffect = (TextView) itemView.findViewById(R.id.txt_RechercheAmountEffect);
            txt_RechercheTime = (TextView) itemView.findViewById(R.id.txt_RechercheTime);
            txt_RechercheGazCost = (TextView) itemView.findViewById(R.id.txt_RechercheGazCost);
            txt_RechercheMineralCost = (TextView) itemView.findViewById(R.id.txt_RechercheMineralCost);
        }
    }
}
