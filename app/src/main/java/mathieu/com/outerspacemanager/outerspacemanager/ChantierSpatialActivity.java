package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Amount;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Fleet;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Search;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnItemClickListener;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnShipIncrementClick;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 14/03/2017.
 */

public class ChantierSpatialActivity extends AppCompatActivity implements OnShipIncrementClick {

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    public String token;
    private ArrayList<Ship> myShip;
    private Ship theShip;
    private Integer theAmount;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chantierspatial);

        //Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_ListShips);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        token = settings.getString(PREFS_TOKEN, new String());

        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Fleet> request = service.getShips(token);

        request.enqueue(new Callback<Fleet>(){

            //Réponce OK -> Peuple la ReciclerView
            @Override
            public void onResponse(Call<Fleet> call, Response<Fleet> response) {
                if (response.isSuccessful()) {
                    myShip = response.body().getShips();
                    if(response.body().getSize() != 0){

                        CustomAdapterChantier myCustomAdapterRecherche = new CustomAdapterChantier(response.body().getShips(), ChantierSpatialActivity.this);

                        //Envoie l'activité au listener
                        myCustomAdapterRecherche.setMyShipClicked(ChantierSpatialActivity.this);
                        mRecyclerView.setAdapter(myCustomAdapterRecherche);
                    }else{
                        Toast.makeText(getApplicationContext(), String.format("Il n'y a pas de vaisseaux pour le moment"), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération des vaisseaux"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fleet> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des vaisseaux !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

    }

    @Override
    public void onShipIncrementClick(int position, Integer amount) {
        theShip = myShip.get(position);
        theAmount = amount;
        createShipsManager();
    }

    public void createShipsManager(){
        new AlertDialog.Builder(this)
                .setTitle("Créer")
                .setMessage("Créer le vaisseau "+ theShip.getName() + " ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        retrofit = new Retrofit.Builder()
                                .baseUrl("https://outer-space-manager.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Service service = retrofit.create(Service.class);
                        Call<Ship> request = service.createShips(token, theShip.getShipId(), new Amount(theAmount));

                        request.enqueue(new Callback<Ship>(){

                            @Override
                            public void onResponse(Call<Ship> call, Response<Ship> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), String.format("Création des vaisseaux en cours"), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), String.format("Erreur lors de la création"), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Ship> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), String.format("Erreur lors de la création !!fatal"), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}