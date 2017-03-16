package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Search;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Searches;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnItemClickListener;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 14/03/2017.
 */

public class RechercheActivity extends AppCompatActivity implements OnItemClickListener {

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    public String token;
    private ArrayList<Search> mySearch;
    private Search theSearch;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        //Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_RechercheList);
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
        Call<Searches> request = service.getSearch(token);

        request.enqueue(new Callback<Searches>(){

            //Réponce OK -> Peuple la ReciclerView
            @Override
            public void onResponse(Call<Searches> call, Response<Searches> response) {
                if (response.isSuccessful()) {
                    mySearch = response.body().getSearches();
                    if(response.body().getSize() != 0){

                        CustomAdapterRecherche myCustomAdapterRecherche = new CustomAdapterRecherche(response.body().getSearches(), RechercheActivity.this);

                        //Envoie l'activité au listener
                        myCustomAdapterRecherche.setMyListener(RechercheActivity.this);
                        mRecyclerView.setAdapter(myCustomAdapterRecherche);
                    }else{
                        Toast.makeText(getApplicationContext(), String.format("Il n'y a pas de recherches"), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération des recherches"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Searches> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des recherches!!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
    //callback == évènement
    @Override
    public void onItemClick(int position) {
        theSearch = mySearch.get(position);
        searchManager();
    }

    public void searchManager(){
        new AlertDialog.Builder(this)
                .setTitle("Recherche")
                .setMessage("Améliorer "+ theSearch.getName() + " ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        retrofit = new Retrofit.Builder()
                                .baseUrl("https://outer-space-manager.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Service service = retrofit.create(Service.class);
                        Call<Search> request = service.doSearch(token, theSearch.getSearchId());

                        request.enqueue(new Callback<Search>(){

                            @Override
                            public void onResponse(Call<Search> call, Response<Search> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), String.format("Recherche en cours"), Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(getApplicationContext(), String.format("Erreur lors de la recherche"), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Search> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), String.format("Erreur lors de la recherche !!fatal"), Toast.LENGTH_SHORT).show();

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
