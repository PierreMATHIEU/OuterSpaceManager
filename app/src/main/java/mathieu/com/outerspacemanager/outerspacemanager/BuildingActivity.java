package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Buildings;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 07/03/2017.
 */

public class BuildingActivity extends Activity {

    private ListView listV_Building;

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    private ArrayList<Building> myBuilding;
    private Building theBuilding;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        listV_Building = (ListView)findViewById(R.id.listV_Building);

        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        token = settings.getString(PREFS_TOKEN, new String());

        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Buildings> request = service.getBuildings(token);

        request.enqueue(new Callback<Buildings>(){

            //Réponce OK -> Peuple la liste view
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                if (response.isSuccessful()) {
                    myBuilding = response.body().getBuildings();
                    listV_Building.setAdapter(new CustomArrayAdapter(getApplicationContext(), response.body().getBuildings()));
                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération des buildings"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des buildings !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        listV_Building.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                theBuilding = myBuilding.get(position);
               buildingManager();

            }
        });
    }

    public void buildingManager(){
        new AlertDialog.Builder(this)
                .setTitle("Construire")
                .setMessage("Améliorer "+ theBuilding.getName() + " ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        retrofit = new Retrofit.Builder()
                                .baseUrl("https://outer-space-manager.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Service service = retrofit.create(Service.class);
                        Call<Building> request = service.constructBuilding(token, theBuilding.getBuildingId());

                        request.enqueue(new Callback<Building>(){

                            @Override
                            public void onResponse(Call<Building> call, Response<Building> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), String.format("Construction en cours"), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), String.format("Erreur lors de la construction"), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Building> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), String.format("Erreur lors de la construction !!fatal"), Toast.LENGTH_SHORT).show();

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
